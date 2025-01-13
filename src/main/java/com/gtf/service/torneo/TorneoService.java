package com.gtf.service.torneo;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.TorneoDTO;
import com.gtf.enums.TorneoEstado;
import com.gtf.model.Torneo;

import java.util.List;

public interface TorneoService {
    Torneo getTorneoById(Integer id);
    Torneo crearTorneo(String torneoNombre);
    Torneo getTorneoByNombre(String nombre);
    List<Torneo> getTorneosPorEstado(TorneoEstado estado);
    Torneo agregarEquipoATorneo(Integer idTorneo, Integer idEquipo);
    TorneoDTO convertirATorneoDTO(Torneo torneo);
    List<TorneoDTO> convertirATorneosDTO(List<Torneo> torneos);

}
