package com.gtf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String resultado;


    @ManyToOne
    @JoinColumn(name = "local_id")
    private Equipo equipo_local;
    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Equipo equipo_visitante;
    @ManyToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro arbitro;
    @ManyToOne
    @JoinColumn(name = "fecha_id")
    private Fecha fecha;
}
