package com.gtf.service.jugador;

import com.gtf.dto.JugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.EstadisticaJugadorRepository;
import com.gtf.repository.JugadorRepository;
import com.gtf.service.equipo.EquipoService;
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

    @Override
    public Jugador getJugadorById(Integer id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
    }

    @Override
    public Jugador getJugadorByNombre(String nombre) {
        return jugadorRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
    }

    @Override
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public Jugador agregarJugador(JugadorDTO jugadorDTO) {
        Equipo equipo = equipoRepository.findById(jugadorDTO.getEquipo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encotrado"));
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setApellido(jugadorDTO.getApellido());
        jugador.setPosicion(jugadorDTO.getPosicion());
        jugador.setEquipo(equipo);
        return jugadorRepository.save(jugador);
    }

    @Override
    public void eliminarJugador(Integer id ) {
        jugadorRepository.deleteById(id);
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
                .map(jugador -> modelMapper.map(jugador, JugadorDTO.class))
                .toList();
    }
}
