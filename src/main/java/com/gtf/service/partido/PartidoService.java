package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.model.Partido;

public interface PartidoService {
    Partido getPartidoById(Integer id);
    Partido agregarPartido(PartidoDTO partidoDTO);
    PartidoDTO convertirPartidoAPartidoDTO(Partido partido);
    Partido actualizarResultadoPartido(PartidoDTO partidoDTO);
    Partido actualizarFechaPartido(PartidoDTO partidoDTO);
}
