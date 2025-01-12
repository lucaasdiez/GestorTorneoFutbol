package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.*;
import com.gtf.repository.ArbitroRepository;
import com.gtf.repository.FechaRepository;
import com.gtf.repository.PartidoRepository;
import com.gtf.service.arbitro.ArbitroService;
import com.gtf.service.equipo.EquipoService;
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

@Service
@RequiredArgsConstructor
public class PartidoServiceImp implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final ModelMapper modelMapper;
    private final ArbitroService arbitroService;
    private final EquipoService equipoService;
    private final FechaRepository fechaRepository;
    private final ArbitroRepository arbitroRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Partido getPartidoById(Integer id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
    }

    @Override
    public Partido agregarPartido( PartidoDTO partidoDTO) {
        Arbitro arbitro = arbitroService.getArbitroById(partidoDTO.getArbitro().getId());
        Equipo equipoLocal = equipoService.getEquipoById(partidoDTO.getEquipo_local().getId());
        Equipo equipoVisitante = equipoService.getEquipoById(partidoDTO.getEquipo_visitante().getId());
        Fecha fecha = fechaRepository.findById(partidoDTO.getFecha().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Fecha no encontrada"));
        Partido partido = new Partido();
        partido.setArbitro(arbitro);
        partido.setEquipo_local(equipoLocal);
        partido.setEquipo_visitante(equipoVisitante);
        partido.setFecha(fecha);
        return partidoRepository.save(partido);
    }

    @Override
    public Partido actualizarPartido(PartidoDTO partidoDTO) {
        Partido partido = partidoRepository.findById(partidoDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
        Arbitro arbitro = arbitroRepository.findById(partidoDTO.getArbitro().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
        partido.setResultado(partidoDTO.getResultado());
        partido.setFecha(partido.getFecha());
        partido.setArbitro(arbitro);
        return partidoRepository.save(partido);
    }

    @Override
    public List<Partido> getPartidosByFechaOrEquipoLocalOrEquipoVisitante(int fecha, String equipoLocal, String equipoVisitante) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Partido> criteriaQuery = criteriaBuilder.createQuery(Partido.class);
        Root<Partido> root = criteriaQuery.from(Partido.class);

        List<Predicate> predicates = new ArrayList<>();
        if(fecha != 0){
            predicates.add(criteriaBuilder.equal(root.get("fecha"), fecha));
        }
        if(equipoLocal != null){
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("equipo_local")), equipoLocal.toLowerCase()));
        }
        if(equipoVisitante != null){
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("equipo_visitante")), equipoVisitante.toLowerCase()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Partido> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


    @Override
    public PartidoDTO convertirPartidoAPartidoDTO(Partido partido){
        return modelMapper.map(partido, PartidoDTO.class);
    }

    @Override
    public List<PartidoDTO> convertirAPartidosDTO(List<Partido> partidos) {
        return partidos.stream()
                .map(partido -> modelMapper.map(partido, PartidoDTO.class))
                .toList();
    }
}
