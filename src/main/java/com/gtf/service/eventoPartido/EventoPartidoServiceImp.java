package com.gtf.service.eventoPartido;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EventoPartido;
import com.gtf.model.Jugador;
import com.gtf.model.Partido;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.EventoPartidoRepository;
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
    private final JugadorService jugadorService;
    private final PartidoService partidoService;
    private final EventoPartidoRepository eventoPartidoRepository;
    private final ModelMapper modelMapper;
    private final EquipoService equipoService;
    @PersistenceContext
    private EntityManager entityManager;

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
