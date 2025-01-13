package com.gtf.service.eventoPartido;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.model.Equipo;
import com.gtf.model.EventoPartido;

import java.util.List;

public interface EventoPartidoService {
    EventoPartido agregarEvento(EventoPartidoDTO eventoPartidoDTO, Integer idEquipo, Integer idJugador, Integer idPartido);
    EventoPartido getEventoPartidoById(Integer id);
    List<EventoPartido> getEventoPartidosByPartidoId(Integer id);
    List<EventoPartido> getEventoByEquipoYJugador(String equipo, String jugador);
    EventoPartidoDTO convertirEventoPartidoaDTO(EventoPartido eventoPartido);
    List<EventoPartidoDTO> convertirAEventosPartidosDTO(List<EventoPartido> eventoPartidos);
}
