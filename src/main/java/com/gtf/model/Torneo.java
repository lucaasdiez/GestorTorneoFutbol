package com.gtf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int nombre;
    private String estado;


    @OneToMany(mappedBy = "torneo")
    private List<Fecha> fechas;
    @OneToMany(mappedBy = "torneo")
    private List<Equipo> equipos;
}
