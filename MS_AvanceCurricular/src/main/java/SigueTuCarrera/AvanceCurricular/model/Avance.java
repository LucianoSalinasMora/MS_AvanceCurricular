package SigueTuCarrera.AvanceCurricular.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Avance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progresoId;
    
    private String estudianteId;
    private Long carreraId;
    
    private Double porcentajeAprobacion;
    private Integer creditosAprobados;
    
    @ElementCollection
    private List<String> asignaturasPendientes;
    
    private LocalDate fechaEstimadaEgreso;
    
    @Enumerated(EnumType.STRING)
    private EstadoAcademico estadoAcademico;

    public enum EstadoAcademico {
        REGULAR, RIESGO_ACADEMICO, CAUSAL_DE_ELIMINACION, EGRESADO
    }
}
