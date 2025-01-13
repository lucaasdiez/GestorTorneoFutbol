package com.gtf.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gtf.enums.UsuarioEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String password;
    private UsuarioEstado estado;
    @JsonBackReference
    private EquipoDTO equipo;
    @JsonBackReference
    private List<TorneoDTO> torneos;
}
