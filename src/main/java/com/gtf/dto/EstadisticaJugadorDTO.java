package com.gtf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaJugadorDTO {
    private Integer id;
    private int goles;
    private int asistencias;
    private int tarjetaRoja;
    private int tarjetaAmarilla;
    private int minJugados;
    private JugadorDTO jugador;
}
