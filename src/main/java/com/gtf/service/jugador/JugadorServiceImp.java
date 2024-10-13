package com.gtf.service.jugador;

import com.gtf.dto.JugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.Jugador;
import com.gtf.repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorServiceImp implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Jugador getJugadorById(Integer id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encotrado"));
    }

    @Override
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public Jugador agregarJugador(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setApellido(jugadorDTO.getApellido());
        jugador.setPosicion(jugadorDTO.getPosicion());
        jugador.setEquipo(jugadorDTO.getEquipo());
        return jugadorRepository.save(jugador);
    }

    @Override
    public void eliminarJugador(Integer id ) {
        jugadorRepository.deleteById(id);
    }

    @Override
    public List<Jugador> getJugadoresPorEquipo(Equipo equipo) {
        return jugadorRepository.findByEquipo(equipo);
    }

    @Override
    public JugadorDTO convertirAJugadorDTO(Jugador jugador) {
        return modelMapper.map(jugador, JugadorDTO.class);

    }
}
