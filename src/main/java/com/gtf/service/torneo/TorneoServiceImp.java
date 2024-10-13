package com.gtf.service.torneo;

import com.gtf.dto.TorneoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.Torneo;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.TorneoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorneoServiceImp implements TorneoService {
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;

    @Override
    public Torneo getTorneoById(Integer id) {
        return torneoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Torneo no encotrado"));
    }

    @Override
    public Torneo crearTorneo(TorneoDTO torneoDTO) {
        Torneo torneo = new Torneo();
        torneo.setNombre(torneoDTO.getNombre());
        torneo.setEstado("Comenazado");
        return torneoRepository.save(torneo);
    }

    @Override
    public List<Torneo> getTorneosPorEstado(String estado) {
        return torneoRepository.findTorneoByEstado(estado);
    }

    @Override
    public Torneo agregarEquipoATorneo(Integer idTorneo, Integer idEquipo) {
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(()-> new ResourceNotFoundException("Equipo no encotrado"));
        Torneo torneo = torneoRepository.findById(idTorneo)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        if(torneo.getEstado().equals("Comenazado")){
            torneo.getEquipos().add(equipo);
            equipo.setTorneo(torneo);
            equipoRepository.save(equipo);
            torneoRepository.save(torneo);
        }
        return torneo;
    }
}
