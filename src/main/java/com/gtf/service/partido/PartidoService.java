package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.model.Partido;

import java.util.List;

public interface PartidoService {
    Partido getPartidoById(Integer id);
    Partido agregarPartido(PartidoDTO partidoDTO);
    PartidoDTO convertirPartidoAPartidoDTO(Partido partido);
    List<PartidoDTO> convertirAPartidosDTO(List<Partido> partidos);
    Partido actualizarPartido(PartidoDTO partidoDTO);
    List<Partido> getPartidosByFechaOrEquipoLocalOrEquipoVisitante(int fecha, String equipoLocal, String equipoVisitante);
}
