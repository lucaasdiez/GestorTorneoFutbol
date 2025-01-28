package com.gtf.service.fecha;

import com.gtf.dto.FechaDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Fecha;
import com.gtf.model.Partido;
import com.gtf.model.Torneo;
import com.gtf.repository.FechaRepository;
import com.gtf.repository.TorneoRepository;
import com.gtf.service.partido.PartidoService;
import com.gtf.service.torneo.TorneoService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FechaServiceImp implements FechaService {
    private final FechaRepository fechaRepository;
    private final PartidoService partidoService;
    private final ModelMapper modelMapper;
    private final TorneoRepository torneoRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Fecha getFechaById(Integer id) {
        return fechaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
    }

    @Override
    public Fecha getFechaByNumero(int numero) {
        return fechaRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
    }

    @Override
    public void agregarFecha( FechaDTO fechaDTO) {
        Torneo torneo= torneoRepository.findById(fechaDTO.getTorneoId())
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        Fecha fecha = Fecha.builder()
                .numero(fechaDTO.getNumero())
                .fechaDia(fechaDTO.getFechaDia())
                .torneo(torneo)
                .build();
        fechaRepository.save(fecha);
    }

    @Override
    public FechaDTO convertirFechaADTO(Fecha fecha) {
        return modelMapper.map(fecha, FechaDTO.class);
    }

    @Override
    public List<FechaDTO> convertirAFechasDTO(List<Fecha> fechas) {
        return fechas.stream()
                .map(fecha -> modelMapper.map(fecha, FechaDTO.class))
                .toList();
    }

    @Override
    public List<Fecha> getAllFechasByDiaOrTorneo(LocalDate dia, String torneo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fecha> criteriaQuery = criteriaBuilder.createQuery(Fecha.class);
        Root<Fecha> root = criteriaQuery.from(Fecha.class);

        List<Predicate> predicates = new ArrayList<>();
        if(dia != null){
            predicates.add(criteriaBuilder.equal(root.get("fechaDia"), dia));
        }
        if(torneo != null){
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("torneo").get("nombre")), torneo.toLowerCase()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Fecha> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
