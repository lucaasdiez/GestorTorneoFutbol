package com.gtf.service.fecha;


import com.gtf.dto.FechaDTO;
import com.gtf.model.Fecha;

import java.time.LocalDate;
import java.util.List;

public interface FechaService {
    Fecha getFechaById(Integer id);
    Fecha getFechaByNumero(int numero);
    Fecha agregarFecha(FechaDTO fechaDTO);
    FechaDTO convertirFechaADTO(Fecha fecha);
    List<FechaDTO> convertirAFechasDTO(List<Fecha> fechas);
    List<Fecha> getAllFechasByDiaOrTorneo(LocalDate dia, String torneo);
}
