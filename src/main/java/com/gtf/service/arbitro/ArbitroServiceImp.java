package com.gtf.service.arbitro;

import com.gtf.dto.ArbitroDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Arbitro;
import com.gtf.repository.ArbitroRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public Arbitro agregarArbitro(ArbitroDTO arbitroDTO) {
        Arbitro arbitro = new Arbitro();
        arbitro.setNombre(arbitroDTO.getNombre());
        arbitro.setApellido(arbitroDTO.getApellido());
        return arbitroRepository.save(arbitro);
    }

    @Override
    public ArbitroDTO convertirArbitroADTO(Arbitro arbitro) {
        return modelMapper.map(arbitro, ArbitroDTO.class);
    }

    @Override
    public void eliminarArbitro(Integer id) {
        arbitroRepository.deleteById(id);
    }

}
