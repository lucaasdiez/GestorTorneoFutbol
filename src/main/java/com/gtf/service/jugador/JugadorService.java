package com.gtf.service.jugador;

import com.gtf.dto.JugadorDTO;
import com.gtf.model.Equipo;
import com.gtf.model.Jugador;

import java.util.List;

public interface JugadorService {
    Jugador getJugadorById(Integer id);
    Jugador getJugadorByDni(String dni);
    List<Jugador> getJugadores();
    void agregarJugador(JugadorDTO jugadorDTO);
    void eliminarJugador(Integer id);
    List<Jugador> getJugadoresByEquipoNombre(String equipo);
    JugadorDTO convertirAJugadorDTO(Jugador jugador);
    List<JugadorDTO> convertirAJugadoresDTO(List<Jugador> jugadores);
}
