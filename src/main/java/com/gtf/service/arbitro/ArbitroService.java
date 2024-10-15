package com.gtf.service.arbitro;

import com.gtf.dto.ArbitroDTO;
import com.gtf.model.Arbitro;

public interface ArbitroService {
    Arbitro getArbitroById(Integer id);
    Arbitro agregarArbitro(ArbitroDTO arbitroDTO);
    ArbitroDTO convertirArbitroADTO(Arbitro arbitro);
    void eliminarArbitro(Integer id);
}
