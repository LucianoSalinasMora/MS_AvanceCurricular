package SigueTuCarrera.AvanceCurricular.dto;

import lombok.Data;

@Data
public class CalificacionDTO {
    private Double valorNota;
    private Boolean esNotaFinal;
    private String asignaturaCodigo;
}