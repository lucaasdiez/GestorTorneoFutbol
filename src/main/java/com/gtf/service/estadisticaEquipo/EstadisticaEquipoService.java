package com.gtf.service.estadisticaEquipo;

import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.model.EstadisticaEquipo;

public interface EstadisticaEquipoService {
    EstadisticaEquipo agregarEstditicaEquipo(EstadisticaEquipoDTO estadisticaEquipoDTO, Integer idEquipo);
    EstadisticaEquipo getEstadisticaEquipoById(Integer id);
    EstadisticaEquipo getEstadisticaEquipoByNombreEquipo(String nombre);
    EstadisticaEquipoDTO convertirEstadicticaEquipoADTO(EstadisticaEquipo estadisticaEquipo);
}
