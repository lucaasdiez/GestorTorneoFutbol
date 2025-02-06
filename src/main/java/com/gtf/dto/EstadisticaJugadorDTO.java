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
public class EstadisticaJugadorDTO {
    private Integer id;
    private int goles;
    private int asistencias;
    private int tarjetaRoja;
    private int tarjetaAmarilla;
    private int minJugados;

}
