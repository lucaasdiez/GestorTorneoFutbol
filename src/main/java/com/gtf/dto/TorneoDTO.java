package com.gtf.dto;

import com.gtf.model.Equipo;
import com.gtf.model.Fecha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneoDTO {
    private Integer id;
    private int nombre;
    private String estado;
    private List<Fecha> fechas;
    private List<EquipoDTO> equipos;
}
