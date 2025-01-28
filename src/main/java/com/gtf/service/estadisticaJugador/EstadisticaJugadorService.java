package com.gtf.service.estadisticaJugador;

import com.gtf.dto.EstadisticaJugadorDTO;
import com.gtf.model.EstadisticaJugador;


public interface EstadisticaJugadorService {
    void agregarEstadisticaJugador(EstadisticaJugadorDTO estadisticaJugadorDTO, String dni);
    EstadisticaJugador getEstadisticaJugadorById(Integer id);
    EstadisticaJugador getEstadisticaJugadorByJugadorDni(String dni);
    EstadisticaJugador getEstadisticaJugadorByJugadorNombre(String nombre);
    EstadisticaJugadorDTO convertirEstadisticaJugadorADTO(EstadisticaJugador estadisticaJugador);
}
