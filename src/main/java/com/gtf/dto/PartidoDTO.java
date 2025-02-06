package com.gtf.dto;


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
    private String resultado;
    private String equipo_local_nombre;
    private String equipo_visitante_nombre;
    private String arbitro_dni;
    private int  fecha_numero;
    private List<EventoPartidoDTO> eventoPartido;
}
