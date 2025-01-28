package com.gtf.service.torneo;

import com.gtf.dto.TorneoDTO;
import com.gtf.enums.TorneoEstado;
import com.gtf.model.Torneo;

import java.util.List;

public interface TorneoService {
    Torneo getTorneoById(Integer id);
    void crearTorneo(String torneoNombre);
    Torneo getTorneoByNombre(String nombre);
    List<Torneo> getTorneosPorEstado(TorneoEstado estado);
    TorneoDTO convertirATorneoDTO(Torneo torneo);
    List<TorneoDTO> convertirATorneosDTO(List<Torneo> torneos);
    List<Torneo> getAllTorneosByUsuarioDni(String dni);

}
