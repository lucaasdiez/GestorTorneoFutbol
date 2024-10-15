package com.gtf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaEquipoDTO {
    private Integer id;
    private int partidosJugados;
    private int victorias;
    private int derrotas;
    private int golesFavor;
    private int golesContra;
    private int puntos;
    private EquipoDTO equipo;
}
