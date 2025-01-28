package com.gtf.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadisticaEquipoDTO {
    private int partidosJugados;
    private int victorias;
    private int derrotas;
    private int golesFavor;
    private int golesContra;
    private int puntos;
}
