package com.gtf.dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartidoDTO {
    private Integer id;
    @Pattern(regexp = "^\\d+-\\d+$",  message = "El resultado debe tener el formato 'X-Y', donde X e Y son números")
    private String resultado;
    private String equipo_local_nombre;
    private String equipo_visitante_nombre;
    private String arbitro_dni;
    private int  fecha_numero;
    private boolean finalizado;
    private List<EventoPartidoDTO> eventoPartido;
}
