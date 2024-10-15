package com.gtf.service.equipo;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.dto.UsuarioDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.Jugador;
import com.gtf.model.Torneo;
import com.gtf.model.Usuario;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImp implements EquipoService{
    private final EquipoRepository equipoRepository;
    private final ModelMapper modelMapper;
    private final JugadorRepository jugadorRepository;

    @Override
    public Equipo getEquipoById(int id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }

    @Override
    public void eliminarEquipo(int id) {
        equipoRepository.deleteById(id);
    }

    @Override
    public Equipo updateEquipo(EquipoDTO equipoDTO, Integer id) {
        return equipoRepository.findById(id)
                .map(equipoExistente -> updateEquipoExistente(equipoExistente, equipoDTO))
                .map(equipoRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }

    private Equipo updateEquipoExistente(Equipo equipoExistente, EquipoDTO dto) {
            equipoExistente.setNombre(dto.getNombre());
            List<Jugador> jugadores = dto.getJugadores()
                .stream()
                .map(jugadorDTO -> modelMapper.map(jugadorDTO, Jugador.class))
                .toList();
            equipoExistente.setJugadores(jugadores);
        Torneo torneo= modelMapper.map(dto.getTorneo(), Torneo.class);
        equipoExistente.setTorneo(torneo);
            return equipoExistente;
    }

    @Override
    public Equipo agregarEquipo(EquipoDTO equipoDTO) {
        Equipo newEquipo = new Equipo();
        newEquipo.setNombre(equipoDTO.getNombre());
        List<Jugador> jugadores = equipoDTO.getJugadores()
                .stream()
                .map(jugadorDTO -> modelMapper.map(jugadorDTO, Jugador.class))
                .toList();
        newEquipo.setJugadores(jugadores);
        Torneo torneo= modelMapper.map(equipoDTO.getTorneo(), Torneo.class);
        newEquipo.setTorneo(torneo);
        Usuario usuario = modelMapper.map(equipoDTO.getUsuario(), Usuario.class);
        newEquipo.setUsuario(usuario);
        equipoRepository.save(newEquipo);
        return newEquipo;
    }

    public EquipoDTO convertirEquipoADto(Equipo equipo) {
        EquipoDTO equipoDTO = modelMapper.map(equipo, EquipoDTO.class);
        List<Jugador> jugadores = jugadorRepository.findByEquipoId(equipo.getId());
        List<JugadorDTO> jugadoresDTO =jugadores.stream()
                .map(jugador -> modelMapper.map(jugador, JugadorDTO.class))
                .toList();
        UsuarioDTO usuarioDTO = modelMapper.map(equipo.getUsuario(), UsuarioDTO.class);
        equipoDTO.setUsuario(usuarioDTO);
        equipoDTO.setJugadores(jugadoresDTO);
        return equipoDTO;
    }
}
