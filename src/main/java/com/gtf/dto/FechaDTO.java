package com.gtf.dto;

import com.gtf.model.Partido;
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
    private Torneo torneo;
    private List<Partido> partidos;
}
