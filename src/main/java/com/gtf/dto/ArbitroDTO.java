package com.gtf.dto;

import com.gtf.model.Partido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArbitroDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private List<PartidoDTO> partidos;
}
