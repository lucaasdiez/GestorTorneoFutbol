package com.gtf.dto;

import com.gtf.model.EstadisticaEquipo;
import com.gtf.model.Jugador;
import com.gtf.model.Partido;
import com.gtf.model.Torneo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {
    private Integer id;
    private String nombre;
    private int partidosJugados;
    private int golesFavor;
    private int golesContra;
    private List<JugadorDTO> jugadores;
    private TorneoDTO torneo;
    private List<Partido> partidosLocal;
    private List<Partido> partidosVisitante;
    private EstadisticaEquipo estadisticaEquipo;

}
