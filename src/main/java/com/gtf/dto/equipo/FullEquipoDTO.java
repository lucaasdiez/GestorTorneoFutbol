package com.gtf.dto.equipo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.dto.PartidoDTO;
import com.gtf.model.EstadisticaEquipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullEquipoDTO {
    private String nombre;
    private List<JugadorDTO> jugadores;
    @JsonIgnore
    private List<PartidoDTO> partidosLocal;
    @JsonIgnore
    private List<PartidoDTO> partidosVisitante;
    private EstadisticaEquipoDTO estadisticaEquipo;
    private String usuarioDni;
    private String torneoNombre;

}
