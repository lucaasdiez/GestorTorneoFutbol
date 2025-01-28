package com.gtf.service.torneo;

import com.gtf.dto.TorneoDTO;
import com.gtf.enums.TorneoEstado;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.Torneo;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.TorneoRepository;
import com.gtf.repository.UsuarioRepository;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.fecha.FechaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TorneoServiceImp implements TorneoService {
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;
    private final EquipoService equipoService;
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;
    private final FechaService fechaService;

    @Override
    public Torneo getTorneoById(Integer id) {
        return torneoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Torneo no encotrado"));
    }

    @Override
    public void crearTorneo(String torneoNombre) {
        Torneo torneo = Torneo.builder()
                .nombre(torneoNombre)
                .estado(TorneoEstado.Comenzado)
                .build();
        torneoRepository.save(torneo);
    }

    @Override
    public Torneo getTorneoByNombre(String nombre) {
        return torneoRepository.findTorneoByNombreIgnoreCase(nombre)
                .orElseThrow(()-> new ResourceNotFoundException("Torneo no encotrado"));
    }

    @Override
    public List<Torneo> getTorneosPorEstado(TorneoEstado estado) {
        return torneoRepository.findTorneoByEstado(estado);
    }


    @Override
    public TorneoDTO convertirATorneoDTO(Torneo torneo) {
        return TorneoDTO.builder()
                .nombre(torneo.getNombre())
                .equipos(equipoService.convertirAEquiposDTO(torneo.getEquipos()))
                .fechas(fechaService.convertirAFechasDTO(torneo.getFechas()))
                .build();
    }

    @Override
    public List<TorneoDTO> convertirATorneosDTO(List<Torneo> torneos) {
        return torneos.stream()
                .map(torneo -> modelMapper.map(torneo,  TorneoDTO.class))
                .toList();
    }

    @Override
    public List<Torneo> getAllTorneosByUsuarioDni(String dni) {
        /*return torneoRepository.findAllByUsuarioDni(dni);*/
        return null;
    }
}
