package com.gtf.dto;

import com.gtf.model.EstadisticaEquipo;
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
    private List<PartidoDTO> partidosLocal;
    private List<PartidoDTO> partidosVisitante;
    private EstadisticaEquipo estadisticaEquipo;
    private UsuarioDTO usuario;

}
