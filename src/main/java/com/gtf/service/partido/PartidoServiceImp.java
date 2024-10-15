package com.gtf.service.partido;

import com.gtf.dto.PartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.*;
import com.gtf.repository.PartidoRepository;
import com.gtf.service.arbitro.ArbitroService;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.fecha.FechaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartidoServiceImp implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final ModelMapper modelMapper;
    private final ArbitroService arbitroService;
    private final EquipoService equipoService;
    private final FechaService fechaService;

    @Override
    public Partido getPartidoById(Integer id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partido no encontrado"));
    }

    @Override
    public Partido agregarPartido(PartidoDTO partidoDTO) {
        Arbitro arbitro = arbitroService.getArbitroById(partidoDTO.getArbitro().getId());
        Equipo equipoLocal = equipoService.getEquipoById(partidoDTO.getEquipo_local().getId());
        Equipo equipoVisitante = equipoService.getEquipoById(partidoDTO.getEquipo_visitante().getId());
        Fecha fecha = fechaService.getFechaById(partidoDTO.getFecha().getId());
        Partido partido = new Partido();
        partido.setArbitro(arbitro);
        partido.setEquipo_local(equipoLocal);
        partido.setEquipo_visitante(equipoVisitante);
        partido.setFecha(fecha);
        return partidoRepository.save(partido);
    }

    @Override
    public Partido actualizarResultadoPartido(PartidoDTO partidoDTO) {
        Partido partido = getPartidoById(partidoDTO.getId());
        partido.setResultado(partidoDTO.getResultado());
        return partidoRepository.save(partido);
    }

    @Override
    public Partido actualizarFechaPartido(PartidoDTO partidoDTO) {
        Partido partido = getPartidoById(partidoDTO.getId());
        partido.setFecha(partidoDTO.getFecha());
        return partidoRepository.save(partido);
    }

    @Override
    public PartidoDTO convertirPartidoAPartidoDTO(Partido partido){
        return modelMapper.map(partido, PartidoDTO.class);
    }
}
