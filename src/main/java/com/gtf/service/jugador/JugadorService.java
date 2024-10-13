package com.gtf.service.jugador;

import com.gtf.dto.JugadorDTO;
import com.gtf.model.Equipo;
import com.gtf.model.Jugador;

import java.util.List;

public interface JugadorService {
    Jugador getJugadorById(Integer id);
    List<Jugador> getJugadores();
    Jugador agregarJugador(JugadorDTO jugadorDTO);
    void eliminarJugador(Integer id);
    List<Jugador> getJugadoresPorEquipo(Equipo equipo);
    JugadorDTO convertirAJugadorDTO(Jugador jugador);
}
