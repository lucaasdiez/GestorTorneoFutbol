package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.*;
import com.gtf.repository.*;
import com.gtf.service.arbitro.ArbitroService;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.estadisticaEquipo.EstadisticaEquipoService;
import com.gtf.service.eventoPartido.EventoPartidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartidoServiceImp implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final ModelMapper modelMapper;
    private final FechaRepository fechaRepository;
    private final ArbitroRepository arbitroRepository;
    private final EquipoRepository equipoRepository;
    private final EventoPartidoService eventoPartidoService;
    private final EstadisticaEquipoService estadisticaEquipoService;
    private final EstadisticaEquipoRepository estadisticaEquipoRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Partido getPartidoById(Integer id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
    }

    @Override
    public void agregarPartido(PartidoDTO partidoDTO) {
        Arbitro arbitro = arbitroRepository.findByDni(partidoDTO.getArbitro_dni())
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
        Equipo equipoLocal = equipoRepository.getEquipoByNombre(partidoDTO.getEquipo_local_nombre());
        Equipo equipoVisitante = equipoRepository.getEquipoByNombre(partidoDTO.getEquipo_visitante_nombre());
        Fecha fecha = fechaRepository.findByNumero(partidoDTO.getFecha_numero())
                .orElseThrow(() -> new ResourceNotFoundException("Fecha no encontrada"));
        Partido partido = Partido.builder()
                .arbitro(arbitro)
                .resultado(partidoDTO.getResultado())
                .equipo_local(equipoLocal)
                .equipo_visitante(equipoVisitante)
                .fecha(fecha)
                .build();
        partidoRepository.save(partido);
    }

    @Override
    public void actualizarPartido(PartidoDTO partidoDTO) {
        Partido partido = partidoRepository.findById(partidoDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
        partido.setResultado(partidoDTO.getResultado());
        partido.setFecha(partido.getFecha());
        if(partidoDTO.getArbitro_dni() != null) {
            Arbitro arbitro = arbitroRepository.findByDni(partidoDTO.getArbitro_dni())
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
            partido.setArbitro(arbitro);
        }
        if (partidoDTO.isFinalizado()){
            partido.setFinalizado(true);
            actualizarEstadisticaPartido(partido);
        }
        partidoRepository.save(partido);
    }



    @Override
    public List<Partido> getPartidosByFechaOrEquipoLocalOrEquipoVisitante(Integer fecha, String equipoLocal, String equipoVisitante) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Partido> criteriaQuery = criteriaBuilder.createQuery(Partido.class);
        Root<Partido> root = criteriaQuery.from(Partido.class);

        List<Predicate> predicates = new ArrayList<>();
        if (fecha != null) {
            predicates.add(criteriaBuilder.equal(root.get("fecha").get("numero"), fecha));
        }
        if (equipoLocal != null && !equipoLocal.isEmpty()) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("equipo_local").get("nombre")), equipoLocal.toLowerCase()));
        }
        if (equipoVisitante != null && !equipoVisitante.isEmpty()) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("equipo_visitante").get("nombre")), equipoVisitante.toLowerCase()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Partido> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }


    @Override
    public PartidoDTO convertirPartidoAPartidoDTO(Partido partido){
        return PartidoDTO.builder()
                .arbitro_dni(partido.getArbitro().getNombre() + " " + partido.getArbitro().getApellido())
                .resultado(partido.getResultado())
                .equipo_local_nombre(partido.getEquipo_local().getNombre())
                .equipo_visitante_nombre(partido.getEquipo_visitante().getNombre())
                .eventoPartido(eventoPartidoService.convertirAEventosPartidosDTO(partido.getEventoPartido()))
                .build();
    }

    @Override
    public List<PartidoDTO> convertirAPartidosDTO(List<Partido> partidos) {
        return partidos.stream()
                .map(this::convertirPartidoAPartidoDTO)
                .toList();
    }

    private void actualizarEstadisticaPartido(Partido partido) {
        Equipo equipoLocal = partido.getEquipo_local();
        Equipo equipoVisitante = partido.getEquipo_visitante();

        EstadisticaEquipo estadisticaEquipoLocal = estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombreIgnoreCase(equipoLocal.getNombre());
        EstadisticaEquipo estadisticaEquipoVisitante = estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombreIgnoreCase(equipoVisitante.getNombre());

        estadisticaEquipoLocal.setPartidosJugados(estadisticaEquipoLocal.getPartidosJugados() + 1);
        estadisticaEquipoVisitante.setPartidosJugados(estadisticaEquipoVisitante.getPartidosJugados() + 1);

        String[] resultado = partido.getResultado().split("-");
        int golesLocal = Integer.parseInt(resultado[0]);
        int golesVisitante = Integer.parseInt(resultado[1]);

        estadisticaEquipoLocal.setGolesFavor(estadisticaEquipoLocal.getGolesFavor() + golesLocal);
        estadisticaEquipoLocal.setGolesContra(estadisticaEquipoLocal.getGolesContra() + golesVisitante);
        estadisticaEquipoVisitante.setGolesFavor(estadisticaEquipoVisitante.getGolesFavor() + golesVisitante);
        estadisticaEquipoVisitante.setGolesContra(estadisticaEquipoVisitante.getGolesContra() + golesLocal);

        if(golesLocal > golesVisitante){
            estadisticaEquipoLocal.setPuntos(estadisticaEquipoLocal.getPuntos() + 3);
            estadisticaEquipoLocal.setVictorias(estadisticaEquipoLocal.getVictorias() + 1);
            estadisticaEquipoVisitante.setDerrotas(estadisticaEquipoVisitante.getDerrotas() + 1);
        }else if(golesLocal < golesVisitante){
            estadisticaEquipoVisitante.setPuntos(estadisticaEquipoVisitante.getPuntos() + 3);
            estadisticaEquipoVisitante.setVictorias(estadisticaEquipoVisitante.getVictorias() + 1);
            estadisticaEquipoLocal.setDerrotas(estadisticaEquipoLocal.getDerrotas() + 1);
        }else {
            estadisticaEquipoVisitante.setPuntos(estadisticaEquipoVisitante.getPuntos() + 1);
            estadisticaEquipoLocal.setPuntos(estadisticaEquipoLocal.getPuntos() + 1);
        }
        estadisticaEquipoRepository.save(estadisticaEquipoLocal);
        estadisticaEquipoRepository.save(estadisticaEquipoVisitante);

    }
}
