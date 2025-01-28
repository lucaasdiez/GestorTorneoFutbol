package com.gtf.service.equipo;

import com.gtf.dto.equipo.SimpleEquipoDTO;
import com.gtf.dto.equipo.FullEquipoDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.model.Equipo;

import java.util.List;

public interface EquipoService {
    Equipo getEquipoById(Integer id);
    Equipo getEquipoByNombre(String nombre);
    List<Equipo> getEquiposByTorneo(String torneo);
    List<Equipo> getEquiposByEstado(EstadoEnum estado);
    void eliminarEquipo(Integer id);
    void updateEquipo(FullEquipoDTO fullEquipoDTO, Integer id);
    void agregarEquipo(SimpleEquipoDTO equipoResponseDTO);
    FullEquipoDTO convertirEquipoADto(Equipo equipo);
    List<SimpleEquipoDTO> convertirAEquiposDTO(List<Equipo> equipos);
}
