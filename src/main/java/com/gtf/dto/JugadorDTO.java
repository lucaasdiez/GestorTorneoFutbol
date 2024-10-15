package com.gtf.dto;

import com.gtf.model.EstadisticaJugador;
import com.gtf.model.EventoPartido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String posicion;
    private int goles;
    private int tarjetaAmarilla;
    private int tarjetaRoja;
    private EquipoDTO equipo;
    private EstadisticaJugador estadisticaJugador;
    private EventoPartido eventoPartido;

}
