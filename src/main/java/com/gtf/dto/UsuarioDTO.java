package com.gtf.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gtf.dto.equipo.FullEquipoDTO;
import com.gtf.enums.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String dni;
    private String username;
    private String password;
    private EstadoEnum estado;
    private FullEquipoDTO equipo;
    @JsonIgnore
    private List<TorneoDTO> torneos;
}
