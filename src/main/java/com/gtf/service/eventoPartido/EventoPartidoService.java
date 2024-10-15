package com.gtf.service.eventoPartido;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.model.Equipo;
import com.gtf.model.EventoPartido;

import java.util.List;

public interface EventoPartidoService {
    EventoPartido agregarEvento(EventoPartidoDTO eventoPartidoDTO);
    EventoPartido getEventoPartidoById(Integer id);
    List<EventoPartido> getEventoPartidoByEquipo(String  equipo);
    List<EventoPartido> getEventoPartidoByJugador(String jugador);
    EventoPartidoDTO convertirEventoPartidoaDTO(EventoPartido eventoPartido);
}
