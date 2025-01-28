package com.gtf.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;
}
