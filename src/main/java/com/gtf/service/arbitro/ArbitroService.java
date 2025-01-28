package com.gtf.service.arbitro;

import com.gtf.dto.ArbitroDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.model.Arbitro;

import java.util.List;

public interface ArbitroService {
    Arbitro getArbitroById(Integer id);
    Arbitro getArbitroByDni(String dni);
    List<Arbitro> getArbitroByEstado(EstadoEnum estado);
    void agregarArbitro(ArbitroDTO arbitroDTO);
    ArbitroDTO convertirArbitroADTO(Arbitro arbitro);
    void cambiarEstadoArbitro(String dni);
}
