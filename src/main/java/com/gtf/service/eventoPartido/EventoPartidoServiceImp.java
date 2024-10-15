package com.gtf.service.eventoPartido;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EventoPartido;
import com.gtf.model.Jugador;
import com.gtf.model.Partido;
import com.gtf.repository.EventoPartidoRepository;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.jugador.JugadorService;
import com.gtf.service.partido.PartidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoPartidoServiceImp implements EventoPartidoService {
    private final JugadorService jugadorService;
    private final EquipoService equipoService;
    private final PartidoService partidoService;
    private final EventoPartidoRepository eventoPartidoRepository;
    private final ModelMapper modelMapper;

    @Override
    public EventoPartido agregarEvento(EventoPartidoDTO eventoPartidoDTO){
        Equipo equipo = equipoService.getEquipoById(eventoPartidoDTO.getEquipoDTO().getId());
        Jugador jugador = jugadorService.getJugadorById(eventoPartidoDTO.getJugadorDTO().getId());
        Partido partido = partidoService.getPartidoById(eventoPartidoDTO.getPartidoDTO().getId());
        EventoPartido eventoPartido = new EventoPartido();
        eventoPartido.setEvento(eventoPartidoDTO.getEvento());
        eventoPartido.setMinuto(eventoPartidoDTO.getMinuto());
        eventoPartido.setJugador(jugador);
        eventoPartido.setEquipo(equipo);
        eventoPartido.setPartido(partido);
        eventoPartidoRepository.save(eventoPartido);
        return eventoPartido;
    }

    @Override
    public EventoPartido getEventoPartidoById(Integer id) {
       return eventoPartidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));
    }

    @Override
    public EventoPartidoDTO convertirEventoPartidoaDTO(EventoPartido eventoPartido) {
        return modelMapper.map(eventoPartido, EventoPartidoDTO.class);
    }

    //Verificar Si funcionan
    @Override
    public List<EventoPartido> getEventoPartidoByEquipo(String equipo) {
        return eventoPartidoRepository.findEventoPartidoByEquipoNombre(equipo);
    }
    public List<EventoPartido> getEventoPartidoByJugador(String jugador) {
        return eventoPartidoRepository.findEventoPartidoByJugadorNombre(jugador);
    }


}
