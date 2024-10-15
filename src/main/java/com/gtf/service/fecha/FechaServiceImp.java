package com.gtf.service.fecha;

import com.gtf.dto.FechaDTO;
import com.gtf.model.Fecha;
import com.gtf.model.Partido;
import com.gtf.model.Torneo;
import com.gtf.repository.FechaRepository;
import com.gtf.service.partido.PartidoService;
import com.gtf.service.torneo.TorneoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FechaServiceImp implements FechaService {
    private final FechaRepository fechaRepository;
    private final TorneoService torneoService;
    private final PartidoService partidoService;
    private final ModelMapper modelMapper;

    @Override
    public Fecha getFechaById(Integer id) {
        return fechaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
    }

    //Funciona?
    @Override
    public Fecha agregarFecha( FechaDTO fechaDTO) {
        Torneo torneo= torneoService.getTorneoById(fechaDTO.getTorneo().getId());
        List<Partido> partidos = new ArrayList<>();
        int cantidad = fechaDTO.getPartidos().size();
        for(int i=0; i< cantidad; i++){
            Partido partido = partidoService.getPartidoById(fechaDTO.getPartidos().get(i).getId());
            partidos.add(partido);
        }
        Fecha fecha = new Fecha();
        fecha.setNumero(fechaDTO.getNumero());
        fecha.setFechaDia(fechaDTO.getFechaDia());
        fecha.setTorneo(torneo);
        fecha.setPartidos(partidos);
        return  fechaRepository.save(fecha);
    }

    @Override
    public FechaDTO convertirFechaADTO(Fecha fecha) {
        return modelMapper.map(fecha, FechaDTO.class);
    }
}
