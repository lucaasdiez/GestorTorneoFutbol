package com.gtf.service.arbitro;

import com.gtf.dto.ArbitroDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Arbitro;
import com.gtf.repository.ArbitroRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArbitroServiceImp implements ArbitroService {
    private final ArbitroRepository arbitroRepository;
    private final ModelMapper modelMapper;

    @Override
    public Arbitro getArbitroById(Integer id) {
        return arbitroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
    }

    @Override
    public Arbitro getArbitroByDni(String dni) {
        return arbitroRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
    }

    @Override
    public List<Arbitro> getArbitroByEstado(EstadoEnum estado) {
        return arbitroRepository.getArbitrosByEstado(estado);
    }

    @Override
    public void agregarArbitro(ArbitroDTO arbitroDTO) {
        Arbitro arbitro = Arbitro.builder()
                .apellido(arbitroDTO.getApellido())
                .nombre(arbitroDTO.getNombre())
                .dni(arbitroDTO.getDni())
                .build();
        arbitroRepository.save(arbitro);
    }

    @Override
    public ArbitroDTO convertirArbitroADTO(Arbitro arbitro) {
        return modelMapper.map(arbitro, ArbitroDTO.class);
    }

    @Override
    public List<ArbitroDTO> convertirArbitrosDTO(List<Arbitro> arbitros) {
        return arbitros.stream()
                .map(arbitro -> modelMapper.map(arbitro, ArbitroDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void cambiarEstadoArbitro(String dni) {
        Arbitro arbitro = arbitroRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Arbitro no encontrado"));
        Arbitro.builder()
                .estado(EstadoEnum.Desactivado)
                .build();
        arbitroRepository.save(arbitro);
    }

}
