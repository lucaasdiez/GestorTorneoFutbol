package com.gtf.dto;

import com.gtf.model.Arbitro;
import com.gtf.model.Equipo;
import com.gtf.model.Fecha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
    private Integer id;
    private String resultado;
    private EquipoDTO equipo_local;
    private EquipoDTO equipo_visitante;
    private ArbitroDTO arbitro;
    private FechaDTO fecha;
}
