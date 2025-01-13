package com.gtf.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gtf.model.Torneo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FechaDTO {
    private Integer id;
    private int numero;
    private LocalDate fechaDia;
    @JsonBackReference
    private TorneoDTO torneo;
    private List<PartidoDTO> partidos;
}
