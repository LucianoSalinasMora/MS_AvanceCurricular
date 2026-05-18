package SigueTuCarrera.AvanceCurricular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import SigueTuCarrera.AvanceCurricular.dto.CalificacionDTO;
import SigueTuCarrera.AvanceCurricular.model.Avance;
import SigueTuCarrera.AvanceCurricular.repository.AvanceRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AvanceService {

    @Autowired
    private AvanceRepository avanceRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    

    public Avance calcularYCrearAvance(Avance avance) {
        List<CalificacionDTO> notas = webClientBuilder.baseUrl("http://localhost:8004").build()
                .get()
                .uri("/api/v1/Calificacioness/student/{rut}", avance.getEstudianteId())
                .retrieve()
                .bodyToFlux(CalificacionDTO.class)
                .collectList()
                .block();

        if (notas != null && !notas.isEmpty()) {
            long aprobadas = notas.stream()
                    .filter(n -> Boolean.TRUE.equals(n.getEsNotaFinal()) && n.getValorNota() >= 4.0)
                    .count();
            avance.setCreditosAprobados((int) aprobadas * 6);
        }

        return avanceRepository.save(avance);
    }

    

    public List<Avance> obtenerAvances() {
        return avanceRepository.findAll();
    }

    public Optional<Avance> obtenerAvance(Long progresoId) {
        return avanceRepository.findById(progresoId);
    }

    public Optional<Avance> actualizarAvance(Long progresoId, Avance avanceActualizado) {
        return avanceRepository.findById(progresoId).map(existente -> {
            avanceActualizado.setProgresoId(progresoId);
            return avanceRepository.save(avanceActualizado);
        });
    }

    public boolean eliminarAvance(Long progresoId) {
        if (avanceRepository.existsById(progresoId)) {
            avanceRepository.deleteById(progresoId);
            return true;
        }
        return false;
    }
}