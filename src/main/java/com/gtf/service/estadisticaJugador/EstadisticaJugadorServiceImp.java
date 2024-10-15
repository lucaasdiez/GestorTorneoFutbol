package com.gtf.service.estadisticaJugador;

import com.gtf.dto.EstadisticaJugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import com.gtf.repository.EstadisticaJugadorRepository;
import com.gtf.service.jugador.JugadorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticaJugadorServiceImp implements EstadisticaJugadorService {
    private final EstadisticaJugadorRepository estadisticaJugadorRepository;
    private final JugadorService jugadorService;
    private final ModelMapper modelMapper;


    @Override
    public EstadisticaJugador agregarEstadisticaJugador(EstadisticaJugadorDTO estadisticaJugadorDTO) {
        Jugador jugador = jugadorService.getJugadorById(estadisticaJugadorDTO.getJugador().getId());
        Optional<EstadisticaJugador> estadisticaJugadorExistente = estadisticaJugadorRepository.findEstadisticaJugadorByJugadorId(estadisticaJugadorDTO.getJugador().getId());
        if(estadisticaJugadorExistente.isPresent()){
            EstadisticaJugador estadisticaJugador = estadisticaJugadorExistente.get();
            estadisticaJugador.setJugador(jugador);
            estadisticaJugador.setAsistencias( estadisticaJugador.getAsistencias() + estadisticaJugadorDTO.getAsistencias());
            estadisticaJugador.setGoles( estadisticaJugador.getGoles()  + estadisticaJugadorDTO.getGoles());
            estadisticaJugador.setTarjetaRoja(estadisticaJugador.getTarjetaRoja() + estadisticaJugadorDTO.getTarjetaRoja());
            estadisticaJugador.setMinJugados(estadisticaJugador.getMinJugados() + estadisticaJugadorDTO.getMinJugados());
            estadisticaJugador.setTarjetaAmarilla(estadisticaJugador.getTarjetaAmarilla()+ estadisticaJugadorDTO.getTarjetaAmarilla());
            return estadisticaJugadorRepository.save(estadisticaJugador);

        }
        else {
            EstadisticaJugador estadisticaJugador = new EstadisticaJugador();
            estadisticaJugador.setJugador(jugador);
            estadisticaJugador.setAsistencias(estadisticaJugadorDTO.getAsistencias());
            estadisticaJugador.setGoles(estadisticaJugadorDTO.getGoles());
            estadisticaJugador.setTarjetaRoja(estadisticaJugadorDTO.getTarjetaRoja());
            estadisticaJugador.setTarjetaAmarilla(estadisticaJugadorDTO.getTarjetaAmarilla());
            estadisticaJugador.setMinJugados(estadisticaJugadorDTO.getMinJugados());
            return estadisticaJugadorRepository.save(estadisticaJugador);
        }
    }

    @Override
    public EstadisticaJugador getEstadisticaJugadorById(Integer id) {
        return estadisticaJugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estadistica del Jugador"+ id +" no encontrada"));
    }

    @Override
    public EstadisticaJugador getEstadisticaJugadorByNombreJugador(String nombre) {
        return estadisticaJugadorRepository.findEstadisticaJugadorByJugadorNombre(nombre);
    }

    @Override
    public EstadisticaJugadorDTO convertirEstadisticaJugadorADTO(EstadisticaJugador estadisticaJugador) {
        return modelMapper.map(estadisticaJugador, EstadisticaJugadorDTO.class);
    }
}
