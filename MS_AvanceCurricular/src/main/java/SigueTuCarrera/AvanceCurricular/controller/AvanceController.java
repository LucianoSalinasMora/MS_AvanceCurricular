package SigueTuCarrera.AvanceCurricular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SigueTuCarrera.AvanceCurricular.model.Avance;
import SigueTuCarrera.AvanceCurricular.service.AvanceService;

@RestController
@RequestMapping("/api/v1/avance-curricular")
public class AvanceController {

    @Autowired
    private AvanceService avanceService;

    
    @GetMapping
    public ResponseEntity<List<Avance>> obtenerAvances() {
        List<Avance> avances = avanceService.obtenerAvances();
        if (avances.isEmpty()) {
            return ResponseEntity.noContent().build();                               // 204
        }
        return ResponseEntity.ok(avances);                     // 200
    }

    
    @PostMapping
    public ResponseEntity<Avance> crearAvance(@RequestBody Avance avance) {
        try {
            Avance nuevo = avanceService.calcularYCrearAvance(avance);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo); //         201
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //  500
        }
    }

    
    @GetMapping("/{progresoId}")
    public ResponseEntity<Avance> obtenerAvance(@PathVariable Long progresoId) {
        return avanceService.obtenerAvance(progresoId)
                .map(avance -> ResponseEntity.ok(avance))       //         200
                .orElse(ResponseEntity.notFound().build());     //  404
    }

    
    @PutMapping("/{progresoId}")
    public ResponseEntity<Avance> actualizarAvance(
            @PathVariable Long progresoId,
            @RequestBody Avance avance) {
        return avanceService.actualizarAvance(progresoId, avance)
                .map(actualizado -> ResponseEntity.ok(actualizado)) //      200
                .orElse(ResponseEntity.notFound().build());         //404
    }

    
    @DeleteMapping("/{progresoId}")
    public ResponseEntity<Void> eliminarAvance(@PathVariable Long progresoId) {
        if (avanceService.eliminarAvance(progresoId)) {
            return ResponseEntity.noContent().build(); //204
        }
        return ResponseEntity.notFound().build();      //404
    }
}