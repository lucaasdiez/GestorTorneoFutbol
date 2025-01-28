package com.gtf.service.estadisticaJugador;

import com.gtf.dto.EstadisticaJugadorDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import com.gtf.repository.EstadisticaJugadorRepository;
import com.gtf.repository.JugadorRepository;
import com.gtf.service.jugador.JugadorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticaJugadorServiceImp implements EstadisticaJugadorService {
    private final EstadisticaJugadorRepository estadisticaJugadorRepository;
    private final ModelMapper modelMapper;
    private final JugadorRepository jugadorRepository;

    @Override
    public void agregarEstadisticaJugador(EstadisticaJugadorDTO estadisticaJugadorDTO, String dni) {
        Jugador jugador = jugadorRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado"));
        EstadisticaJugador estadisticaJugadorExistente = estadisticaJugadorRepository.findEstadisticaJugadorByJugadorNombreIgnoreCase(estadisticaJugadorDTO.getNombre_jugador());
       EstadisticaJugador estadisticaJugador;
        if(estadisticaJugadorExistente.getJugador().getDni().equals(dni)){
             estadisticaJugador = estadisticaJugadorToEntity(estadisticaJugadorExistente, estadisticaJugadorDTO);
        }
        else {
             estadisticaJugador = EstadisticaJugador.builder()
                     .jugador(jugador)
                     .asistencias(estadisticaJugadorDTO.getAsistencias())
                     .goles(estadisticaJugadorDTO.getGoles())
                     .tarjetaRoja(estadisticaJugadorDTO.getTarjetaRoja())
                     .minJugados(estadisticaJugadorDTO.getMinJugados())
                     .tarjetaAmarilla(estadisticaJugadorDTO.getTarjetaRoja())
                     .build();

        }
        estadisticaJugadorRepository.save(estadisticaJugador);
    }

    @Override
    public EstadisticaJugador getEstadisticaJugadorById(Integer id) {
        return estadisticaJugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadistica del Jugador"+ id +" no encontrada"));
    }

    @Override
    public EstadisticaJugador getEstadisticaJugadorByJugadorDni(String dni) {
        return estadisticaJugadorRepository.findEstadisticaJugadorByJugadorDni(dni);
    }

    @Override
    public EstadisticaJugador getEstadisticaJugadorByJugadorNombre(String nombre) {
        return estadisticaJugadorRepository.findEstadisticaJugadorByJugadorNombreIgnoreCase(nombre);
    }

    @Override
    public EstadisticaJugadorDTO convertirEstadisticaJugadorADTO(EstadisticaJugador estadisticaJugador) {
        return modelMapper.map(estadisticaJugador, EstadisticaJugadorDTO.class);
    }

    private EstadisticaJugador estadisticaJugadorToEntity(EstadisticaJugador estadisticaJugadorExistente, EstadisticaJugadorDTO estadisticaJugadorDTO) {
        estadisticaJugadorExistente.setAsistencias(estadisticaJugadorExistente.getAsistencias() + estadisticaJugadorDTO.getAsistencias());
        estadisticaJugadorExistente.setGoles(estadisticaJugadorExistente.getGoles() + estadisticaJugadorDTO.getGoles());
        estadisticaJugadorExistente.setTarjetaRoja(estadisticaJugadorDTO.getTarjetaRoja() + estadisticaJugadorDTO.getTarjetaRoja());
        estadisticaJugadorExistente.setTarjetaAmarilla(estadisticaJugadorDTO.getTarjetaAmarilla() + estadisticaJugadorDTO.getTarjetaAmarilla());
        estadisticaJugadorExistente.setMinJugados(estadisticaJugadorDTO.getMinJugados() + estadisticaJugadorDTO.getMinJugados());

        return estadisticaJugadorExistente;
    }
}
