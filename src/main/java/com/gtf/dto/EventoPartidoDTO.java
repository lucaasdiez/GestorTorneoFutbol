package com.gtf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoPartidoDTO {

    private Integer id;
    private String evento; //amarrilla, roja, gol
    private int minuto;
    private PartidoDTO partido;
    private JugadorDTO jugador;
    private EquipoDTO equipo;
}
