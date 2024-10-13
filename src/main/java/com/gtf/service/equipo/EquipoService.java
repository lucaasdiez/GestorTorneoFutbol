package com.gtf.service.equipo;

import com.gtf.dto.EquipoDTO;
import com.gtf.model.Equipo;

public interface EquipoService {
    Equipo getEquipoById(int id);
    void eliminarEquipo(int id);
    Equipo updateEquipo(EquipoDTO equipoDTO, Integer id);
    Equipo agregarEquipo(EquipoDTO equipoDTO);
    EquipoDTO convertirEquipoADto(Equipo equipo);
}
