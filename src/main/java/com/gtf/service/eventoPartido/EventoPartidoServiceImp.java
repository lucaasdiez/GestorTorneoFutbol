package com.gtf.service.eventoPartido;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EventoPartido;
import com.gtf.model.Jugador;
import com.gtf.model.Partido;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.EventoPartidoRepository;
import com.gtf.repository.JugadorRepository;
import com.gtf.repository.PartidoRepository;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.jugador.JugadorService;
import com.gtf.service.partido.PartidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoPartidoServiceImp implements EventoPartidoService {
    private final EventoPartidoRepository eventoPartidoRepository;
    private final ModelMapper modelMapper;
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void agregarEvento(EventoPartidoDTO eventoPartidoDTO, Integer equipoId, Integer jugadorId, Integer idPartido) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        Partido partido = partidoRepository.findById(idPartido)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
        EventoPartido eventoPartido = EventoPartido.builder()
                .evento(eventoPartidoDTO.getEvento())
                .minuto(eventoPartidoDTO.getMinuto())
                .jugador(jugador)
                .partido(partido)
                .equipo(equipo)
                .build();
        eventoPartidoRepository.save(eventoPartido);
    }

    @Override
    public EventoPartido getEventoPartidoById(Integer id) {
       return eventoPartidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));
    }

    @Override
    public List<EventoPartido> getEventoPartidosByPartidoId(Integer id) {
        return eventoPartidoRepository.findByPartidoId(id);
    }

    @Override
    public EventoPartidoDTO convertirEventoPartidoaDTO(EventoPartido eventoPartido) {
        return modelMapper.map(eventoPartido, EventoPartidoDTO.class);
    }

    @Override
    public List<EventoPartidoDTO> convertirAEventosPartidosDTO(List<EventoPartido> eventoPartidos) {
        return eventoPartidos.stream()
                .map(eventoPartido -> modelMapper.map(eventoPartido, EventoPartidoDTO.class))
                .toList();
    }

    @Override
    public List<EventoPartido> getEventoByEquipoYJugador(String equipo, String jugador) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EventoPartido> criteriaQuery =criteriaBuilder.createQuery(EventoPartido.class);
        Root<EventoPartido> root= criteriaQuery.from(EventoPartido.class);
        List<Predicate> predicados =new ArrayList<>();
        if (equipo != null){
            predicados.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("nombre")),
                    equipo.toLowerCase()));
        }
        if (jugador != null){
            predicados.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("nombre")),
                    jugador.toLowerCase()));
        }
        criteriaQuery.select(root).where(criteriaBuilder.and(predicados.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }




}
