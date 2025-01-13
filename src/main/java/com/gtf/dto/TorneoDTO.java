package com.gtf.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TorneoDTO {
    private Integer id;
    private String nombre;
    private String estado;
    @JsonManagedReference
    private List<FechaDTO> fechas;
    @JsonManagedReference
    private List<EquipoDTO> equipos;
    @JsonManagedReference
    private List<UsuarioDTO> usuarios;
}
