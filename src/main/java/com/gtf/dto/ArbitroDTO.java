package com.gtf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArbitroDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private List<PartidoDTO> partidos;
}
