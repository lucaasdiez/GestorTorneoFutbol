package com.gtf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
    private Integer id;
    private String resultado;
    private String equipo_local_nombre;
    private String equipo_visitante_nombre;
    private String arbitro_dni;
    private int  fecha_numero;
    private EventoPartidoDTO eventoPartido;
}
