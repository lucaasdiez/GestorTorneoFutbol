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
    private Equipo equipo_local;
    private Equipo equipo_visitante;
    private Arbitro arbitro;
    private Fecha fecha;
}
