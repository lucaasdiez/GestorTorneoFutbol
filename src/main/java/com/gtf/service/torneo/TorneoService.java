package com.gtf.service.torneo;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.TorneoDTO;
import com.gtf.model.Torneo;

import java.util.List;

public interface TorneoService {
    Torneo getTorneoById(Integer id);
    Torneo crearTorneo(TorneoDTO torneoDTO);
    List<Torneo> getTorneosPorEstado(String estado);
    Torneo agregarEquipoATorneo(Integer idTorneo, Integer idEquipo);

}
