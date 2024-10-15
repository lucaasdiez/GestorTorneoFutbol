package com.gtf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String usuario;
    private String password;
    private EquipoDTO equipo;
    private List<TorneoDTO> torneos;
}
