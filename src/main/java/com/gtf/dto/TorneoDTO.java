package com.gtf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneoDTO {
    private Integer id;
    private String nombre;
    private String estado;
    private List<FechaDTO> fechas;
    private List<EquipoDTO> equipos;
}
