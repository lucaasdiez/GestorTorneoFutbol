package com.gtf.service.fecha;


import com.gtf.dto.FechaDTO;
import com.gtf.model.Fecha;

public interface FechaService {
    Fecha getFechaById(Integer id);
    Fecha agregarFecha(FechaDTO fechaDTO);
}
