package com.gtf.service.estadisticaEquipo;

import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaEquipo;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.EstadisticaEquipoRepository;
import com.gtf.service.equipo.EquipoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticaEquipoServiceImp implements EstadisticaEquipoService {
    private final EstadisticaEquipoRepository estadisticaEquipoRepository;
    private final EquipoRepository equipoRepository;

    @Override
    public void  agregarEstditicaEquipo(EstadisticaEquipoDTO estadisticaEquipoDTO, Integer idEquipo) {
        Equipo equipo = equipoRepository.findById(idEquipo)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        EstadisticaEquipo estadVieja = estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombreIgnoreCase(equipo.getNombre());
        EstadisticaEquipo estadisticaEquipo;
        if (estadVieja != null) {
           estadisticaEquipo = estadisticaEquipoToEntity(estadVieja, estadisticaEquipoDTO);

        }else {
            estadisticaEquipo = EstadisticaEquipo.builder()
                    .equipo(equipo)
                    .derrotas(estadisticaEquipoDTO.getDerrotas())
                    .puntos(estadisticaEquipoDTO.getPuntos())
                    .victorias(estadisticaEquipoDTO.getVictorias())
                    .golesContra(estadisticaEquipoDTO.getGolesContra())
                    .golesFavor(estadisticaEquipoDTO.getGolesFavor())
                    .partidosJugados(estadisticaEquipoDTO.getPartidosJugados())
                    .build();
        }
        estadisticaEquipoRepository.save(estadisticaEquipo);
    }


    @Override
    public EstadisticaEquipo getEstadisticaEquipoById(Integer id) {
        return estadisticaEquipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadistica del Equipo no encontrada"));
    }

    @Override
    public EstadisticaEquipo getEstadisticaEquipoByNombreEquipo(String nombre) {
        return estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombreIgnoreCase(nombre);
    }

    @Override
    public EstadisticaEquipoDTO convertirEstadicticaEquipoADTO(EstadisticaEquipo estadisticaEquipo) {
        return EstadisticaEquipoDTO.builder()
                .partidosJugados(estadisticaEquipo.getPartidosJugados())
                .victorias(estadisticaEquipo.getVictorias())
                .derrotas(estadisticaEquipo.getDerrotas())
                .puntos(estadisticaEquipo.getPuntos())
                .golesContra(estadisticaEquipo.getGolesContra())
                .golesFavor(estadisticaEquipo.getGolesFavor())
                .build();
    }

    private EstadisticaEquipo estadisticaEquipoToEntity(EstadisticaEquipo estVieja, EstadisticaEquipoDTO estadisticaEquipoDTO) {
        estVieja.setDerrotas(estVieja.getDerrotas() + estadisticaEquipoDTO.getDerrotas());
        estVieja.setPartidosJugados(estVieja.getPartidosJugados() + estadisticaEquipoDTO.getPartidosJugados());
        estVieja.setPuntos(estVieja.getPuntos() + estadisticaEquipoDTO.getPuntos());
        estVieja.setVictorias(estVieja.getVictorias() + estadisticaEquipoDTO.getVictorias());
        estVieja.setGolesFavor(estVieja.getGolesFavor() + estadisticaEquipoDTO.getGolesFavor());
        estVieja.setGolesContra(estVieja.getGolesContra() + estadisticaEquipoDTO.getGolesContra());
        return estVieja;
    }

}
