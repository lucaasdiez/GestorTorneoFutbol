package com.gtf.service.estadisticaEquipo;

import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaEquipo;
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
    private final EquipoService equipoService;
    private final ModelMapper modelMapper;

    @Override
    public EstadisticaEquipo agregarEstditicaEquipo(EstadisticaEquipoDTO estadisticaEquipoDTO) {
        Equipo equipo = equipoService.getEquipoById(estadisticaEquipoDTO.getEquipo().getId());
        Optional<EstadisticaEquipo> estadisticaEquipoExistente = estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombre(equipo.getNombre());
        if (estadisticaEquipoExistente.isPresent()) {
            EstadisticaEquipo estadisticaEquipo = estadisticaEquipoExistente.get();
            estadisticaEquipo.setEquipo(equipo);
            estadisticaEquipo.setDerrotas(estadisticaEquipo.getDerrotas() + estadisticaEquipoDTO.getDerrotas());
            estadisticaEquipo.setPuntos(estadisticaEquipo.getPuntos() + estadisticaEquipoDTO.getPuntos());
            estadisticaEquipo.setVictorias(estadisticaEquipo.getVictorias() + estadisticaEquipoDTO.getVictorias());
            estadisticaEquipo.setGolesContra(estadisticaEquipo.getGolesContra() + estadisticaEquipoDTO.getGolesContra());
            estadisticaEquipo.setPartidosJugados(estadisticaEquipo.getPartidosJugados() + estadisticaEquipoDTO.getPartidosJugados());
            estadisticaEquipo.setGolesFavor(estadisticaEquipo.getGolesFavor() + estadisticaEquipoDTO.getGolesFavor());
           return  estadisticaEquipoRepository.save(estadisticaEquipo);
        }else {
            EstadisticaEquipo estadisticaEquipo = new EstadisticaEquipo();
            estadisticaEquipo.setEquipo(equipo);
            estadisticaEquipo.setDerrotas(estadisticaEquipoDTO.getDerrotas());
            estadisticaEquipo.setPuntos(estadisticaEquipoDTO.getPuntos());
            estadisticaEquipo.setVictorias(estadisticaEquipoDTO.getVictorias());
            estadisticaEquipo.setGolesContra(estadisticaEquipoDTO.getGolesContra());
            estadisticaEquipo.setPartidosJugados(estadisticaEquipoDTO.getPartidosJugados());
            estadisticaEquipo.setGolesFavor(estadisticaEquipoDTO.getGolesFavor());
           return  estadisticaEquipoRepository.save(estadisticaEquipo);
        }
    }

    @Override
    public EstadisticaEquipo getEstadisticaEquipoById(Integer id) {
        return estadisticaEquipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadistica del Equipo no encontrada"));
    }

    @Override
    public EstadisticaEquipo getEstadisticaEquipoByNombreEquipo(String nombre) {
        return estadisticaEquipoRepository.findEstadisticaEquipoByEquipoNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Estadistica del Equipo no encontrada"));
    }

    @Override
    public EstadisticaEquipoDTO convertirEstadicticaEquipoADTO(EstadisticaEquipo estadisticaEquipo) {
        return modelMapper.map(estadisticaEquipo, EstadisticaEquipoDTO.class);
    }
}
