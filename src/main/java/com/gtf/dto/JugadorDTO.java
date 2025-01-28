package com.gtf.dto;

import com.gtf.dto.equipo.SimpleEquipoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JugadorDTO {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private String posicion;
    private SimpleEquipoDTO equipo;
    private EstadisticaJugadorDTO estadisticaJugador;

}
