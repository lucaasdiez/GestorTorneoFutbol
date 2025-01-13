package com.gtf.service.equipo;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.dto.UsuarioDTO;
import com.gtf.enums.EquipoEstado;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.*;
import com.gtf.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoServiceImp implements EquipoService{
    private final EquipoRepository equipoRepository;
    private final ModelMapper modelMapper;
    private final JugadorRepository jugadorRepository;
    private final TorneoRepository torneoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadisticaEquipoRepository estadisticaEquipoRepository;

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
        List<Jugador> jugadores = dto.getJugadores().stream()
                        .map(jugador -> jugadorRepository.findById(jugador.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado")))
                                .toList();
        EstadisticaEquipo estadisticaEquipo = estadisticaEquipoRepository.findById(equipoExistente.getEstadisticaEquipo().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Estadistica Equipo no encontrado"));
        estadisticaEquipo.setPuntos(dto.getEstadisticaEquipo().getPuntos());
        estadisticaEquipo.setVictorias(dto.getEstadisticaEquipo().getVictorias());
        estadisticaEquipo.setDerrotas(dto.getEstadisticaEquipo().getDerrotas());
        estadisticaEquipo.setGolesContra(dto.getEstadisticaEquipo().getGolesContra());
        estadisticaEquipo.setGolesFavor(dto.getEstadisticaEquipo().getGolesFavor());
        estadisticaEquipo.setPartidosJugados(dto.getEstadisticaEquipo().getPartidosJugados());
        estadisticaEquipo.setEquipo(equipoExistente);
        estadisticaEquipoRepository.save(estadisticaEquipo);
        equipoExistente.setEstadisticaEquipo(estadisticaEquipo);
        equipoExistente.setJugadores(jugadores);
        equipoExistente.setNombre(dto.getNombre());

        return equipoRepository.save(equipoExistente);
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
        EstadisticaEquipo estadisticaEquipo = new EstadisticaEquipo();
        estadisticaEquipo.setEquipo(newEquipo);
        estadisticaEquipo.setDerrotas(equipoDTO.getEstadisticaEquipo().getDerrotas());
        estadisticaEquipo.setPuntos(equipoDTO.getEstadisticaEquipo().getPuntos());
        estadisticaEquipo.setVictorias(equipoDTO.getEstadisticaEquipo().getVictorias());
        estadisticaEquipo.setDerrotas(equipoDTO.getEstadisticaEquipo().getDerrotas());
        estadisticaEquipo.setGolesContra(equipoDTO.getEstadisticaEquipo().getGolesContra());
        estadisticaEquipo.setGolesFavor(equipoDTO.getEstadisticaEquipo().getGolesFavor());
        estadisticaEquipo.setPartidosJugados(equipoDTO.getEstadisticaEquipo().getPartidosJugados());
        estadisticaEquipoRepository.save(estadisticaEquipo);
        newEquipo.setEstadisticaEquipo(estadisticaEquipo);

        return equipoRepository.save(newEquipo);
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
