package com.gtf.service.jugador;

import com.gtf.dto.JugadorDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.EstadisticaJugadorRepository;
import com.gtf.repository.JugadorRepository;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.estadisticaJugador.EstadisticaJugadorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorServiceImp implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final ModelMapper modelMapper;
    private final EquipoRepository equipoRepository;
    private final EstadisticaJugadorService estadisticaJugadorService;

    @Override
    public Jugador getJugadorById(Integer id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
    }

    @Override
    public Jugador getJugadorByDni(String dni) {
        return jugadorRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
    }

    @Override
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public void agregarJugador(JugadorDTO jugadorDTO) {
        Equipo equipo = equipoRepository.findByNombreIgnoreCase(jugadorDTO.getEquipo().getNombre())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encotrado"));
        Jugador jugador = Jugador.builder()
                .dni(jugadorDTO.getDni())
                .nombre(jugadorDTO.getNombre())
                .apellido(jugadorDTO.getApellido())
                .posicion(jugadorDTO.getPosicion())
                .equipo(equipo)
                .estado(EstadoEnum.Activado)
                .build();
        EstadisticaJugador estadisticaJugador = new EstadisticaJugador();
        estadisticaJugador.setJugador(jugador);
        jugador.setEstadisticaJugador(estadisticaJugador);
        jugadorRepository.save(jugador);
    }

    @Override
    public void eliminarJugador(Integer id ) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
        jugador.setEstado(EstadoEnum.Desactivado);
        jugadorRepository.save(jugador);
    }

    @Override
    public List<Jugador> getJugadoresByEquipoNombre(String equipo) {
        return jugadorRepository.findByEquipoNombreIgnoreCase(equipo);
    }

    @Override
    public JugadorDTO convertirAJugadorDTO(Jugador jugador) {
        return modelMapper.map(jugador, JugadorDTO.class);

    }

    @Override
    public List<JugadorDTO> convertirAJugadoresDTO(List<Jugador> jugadores) {
        return jugadores.stream()
                .map(jugador -> JugadorDTO.builder()
                        .dni(jugador.getDni())
                        .nombre(jugador.getNombre())
                        .apellido(jugador.getApellido())
                        .posicion(jugador.getPosicion())
                        .estadisticaJugador(estadisticaJugadorService.convertirEstadisticaJugadorADTO(jugador.getEstadisticaJugador()))
                        .build())
                .toList();
    }
}
