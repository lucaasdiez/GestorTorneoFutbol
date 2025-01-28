package com.gtf.service.equipo;

import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.dto.equipo.SimpleEquipoDTO;
import com.gtf.dto.equipo.FullEquipoDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.*;
import com.gtf.repository.*;
import com.gtf.service.estadisticaEquipo.EstadisticaEquipoService;
import com.gtf.service.jugador.JugadorService;
import com.gtf.service.partido.PartidoService;
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
    private final EstadisticaEquipoService estadisticaEquipoService;
    private final JugadorService jugadorService;
    private final PartidoService partidoService;

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
    public List<Equipo> getEquiposByEstado(EstadoEnum estado) {
        return equipoRepository.findAllByEstadoEquipo(estado);
    }

    @Override
    public void eliminarEquipo(Integer id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
        equipo.setEstadoEquipo(EstadoEnum.Desactivado);
        equipoRepository.save(equipo);
    }

    @Override
    public void updateEquipo(FullEquipoDTO fullEquipoDTO, Integer id) {
         equipoRepository.findById(id)
                .map(equipoExistente -> updateEquipoExistente(equipoExistente, fullEquipoDTO))
                .map(equipoRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado"));
    }

    private Equipo updateEquipoExistente(Equipo equipoExistente, FullEquipoDTO dto) {
        List<Jugador> jugadores = dto.getJugadores().stream()
                        .map(jugador -> jugadorRepository.findById(jugador.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado")))
                                .toList();
        equipoExistente.setJugadores(jugadores);
        equipoExistente.setNombre(dto.getNombre());

        return equipoRepository.save(equipoExistente);
    }

    @Override
    public void agregarEquipo(SimpleEquipoDTO equipoResponseDTO) {
        Torneo torneo = torneoRepository.findTorneoByNombreIgnoreCase(equipoResponseDTO.getTorneoNombre())
                .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));
        Usuario usuario = usuarioRepository.findByDni(equipoResponseDTO.getUsuarioDni())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if(equipoRepository.existsByNombreIgnoreCase(equipoResponseDTO.getNombre())){
            throw new IllegalArgumentException("Equipo ya existe");
        }
        Equipo newEquipo = Equipo.builder()
                .nombre(equipoResponseDTO.getNombre())
                .torneo(torneo)
                .usuario(usuario)
                .estadoEquipo(EstadoEnum.Activado)
                .build();
        EstadisticaEquipo estadisticaEquipo = EstadisticaEquipo.builder()
                .partidosJugados(0)
                .golesFavor(0)
                .golesContra(0)
                .puntos(0)
                .victorias(0)
                .derrotas(0)
                .equipo(newEquipo)
                .build();
        newEquipo.setEstadisticaEquipo(estadisticaEquipo);
        equipoRepository.save(newEquipo);
    }

    public FullEquipoDTO convertirEquipoADto(Equipo equipo) {
       return  FullEquipoDTO.builder()
                .nombre(equipo.getNombre())
                .torneoNombre(equipo.getTorneo().getNombre())
                .estadisticaEquipo(estadisticaEquipoService.convertirEstadicticaEquipoADTO(equipo.getEstadisticaEquipo()))
                .jugadores(jugadorService.convertirAJugadoresDTO(equipo.getJugadores()))
                .partidosLocal(partidoService.convertirAPartidosDTO(equipo.getPartidosLocal()))
                .partidosVisitante(partidoService.convertirAPartidosDTO(equipo.getPartidosVisitante()))
                .build();
    }

    @Override
    public List<SimpleEquipoDTO> convertirAEquiposDTO(List<Equipo> equipos) {
        return equipos.stream()
                .map(equipo -> SimpleEquipoDTO.builder()
                        .nombre(equipo.getNombre())
                        .build())
                .toList();
    }
}
