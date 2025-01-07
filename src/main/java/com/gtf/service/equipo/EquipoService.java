package com.gtf.service.equipo;

import com.gtf.dto.EquipoDTO;
import com.gtf.model.Equipo;

import java.util.List;

public interface EquipoService {
    Equipo getEquipoById(Integer id);
    Equipo getEquipoByNombre(String nombre);
    List<Equipo> getEquiposByTorneo(String torneo);
    void eliminarEquipo(Integer id);
    Equipo updateEquipo(EquipoDTO equipoDTO, Integer id);
    Equipo agregarEquipo(EquipoDTO equipoDTO);
    EquipoDTO convertirEquipoADto(Equipo equipo);
    List<EquipoDTO> convertirAEquiposDTO(List<Equipo> equipos);
}
