package com.gtf.dto;

import com.gtf.enums.EventoPartidoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoPartidoDTO {

    private Integer id;
    private EventoPartidoEnum evento; //amarrilla, roja, gol
    private int minuto;
    private String jugador;
    private String equipo;
}
