package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.model.Partido;

import java.util.List;

public interface PartidoService {
    Partido getPartidoById(Integer id);
    void agregarPartido(PartidoDTO partidoDTO);
    PartidoDTO convertirPartidoAPartidoDTO(Partido partido);
    List<PartidoDTO> convertirAPartidosDTO(List<Partido> partidos);
    void actualizarPartido(PartidoDTO partidoDTO);
    List<Partido> getPartidosByFechaOrEquipoLocalOrEquipoVisitante(Integer fecha, String equipoLocal, String equipoVisitante);
}
