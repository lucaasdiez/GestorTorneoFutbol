package com.gtf.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gtf.model.EstadisticaEquipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {
    private Integer id;
    private String nombre;
    private List<JugadorDTO> jugadores;
    @JsonBackReference
    private TorneoDTO torneo;
    private List<PartidoDTO> partidosLocal;
    private List<PartidoDTO> partidosVisitante;
    private EstadisticaEquipo estadisticaEquipo;
    @JsonManagedReference
    private UsuarioDTO usuario;

}
