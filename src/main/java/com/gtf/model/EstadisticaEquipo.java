package com.gtf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int partidosJugados;
    private int victorias;
    private int derrotas;
    private int golesFavor;
    private int golesContra;
    private int puntos;

    @OneToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
}
