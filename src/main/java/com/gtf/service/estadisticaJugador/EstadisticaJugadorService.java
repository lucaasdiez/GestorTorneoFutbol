package com.gtf.service.estadisticaJugador;

import com.gtf.dto.EstadisticaJugadorDTO;
import com.gtf.model.EstadisticaJugador;


public interface EstadisticaJugadorService {
    EstadisticaJugador agregarEstadisticaJugador(EstadisticaJugadorDTO estadisticaJugadorDTO);
    EstadisticaJugador getEstadisticaJugadorById(Integer id);
    EstadisticaJugador getEstadisticaJugadorByNombreJugador(String nombre);
    EstadisticaJugadorDTO convertirEstadisticaJugadorADTO(EstadisticaJugador estadisticaJugador);
}
