package com.gtf.dto.equipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleEquipoDTO {
    private String nombre;
    private String usuarioDni;
    private String torneoNombre;
}
