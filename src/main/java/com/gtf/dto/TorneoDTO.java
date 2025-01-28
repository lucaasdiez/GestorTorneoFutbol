package com.gtf.dto;


import com.gtf.dto.equipo.FullEquipoDTO;
import com.gtf.dto.equipo.SimpleEquipoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TorneoDTO {
    private Integer id;
    private String nombre;
    private String estado;
    private List<FechaDTO> fechas;
    private List<SimpleEquipoDTO> equipos;
    private List<UsuarioDTO> usuarios;
}
