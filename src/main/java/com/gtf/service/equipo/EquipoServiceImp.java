package com.gtf.service.equipo;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.dto.UsuarioDTO;
import com.gtf.enums.EquipoEstado;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.Jugador;
import com.gtf.model.Torneo;
import com.gtf.model.Usuario;
import com.gtf.repository.EquipoRepository;
import com.gtf.repository.JugadorRepository;
import com.gtf.repository.TorneoRepository;
import com.gtf.repository.UsuarioRepository;
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
    private final TorneoRepository torneoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Equipo getEquipoById(Integer id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }

    @Override
    public Equipo getEquipoByNombre(String nombre) {
        return equipoRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }

    @Override
    public List<Equipo> getEquiposByTorneo(String torneo) {
        return equipoRepository.findByTorneoNombreIgnoreCase(torneo);
    }

    @Override
    public void eliminarEquipo(Integer id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        equipo.setEstadoEquipo(EquipoEstado.Desactivado);
        equipoRepository.save(equipo);
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
        Torneo torneo = torneoRepository.findById(equipoDTO.getTorneo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        Usuario usuario = usuarioRepository.findById(equipoDTO.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Equipo newEquipo = new Equipo();
        newEquipo.setNombre(equipoDTO.getNombre());
        List<Jugador> jugadores = equipoDTO.getJugadores()
                .stream()
                .map(jugadorDTO -> jugadorRepository.findById(jugadorDTO.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado")))
                .toList();
        newEquipo.setJugadores(jugadores);
        newEquipo.setTorneo(torneo);
        newEquipo.setUsuario(usuario);
        newEquipo.setEstadoEquipo(EquipoEstado.Activado);
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

    @Override
    public List<EquipoDTO> convertirAEquiposDTO(List<Equipo> equipos) {
        return equipos.stream()
                .map(equipo -> modelMapper.map(equipo, EquipoDTO.class))
                .toList();
    }
}
