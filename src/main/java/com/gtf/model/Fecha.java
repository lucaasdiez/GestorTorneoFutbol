package com.gtf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Fecha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numero;
    private LocalDate fechaDia;

    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    @OneToMany(mappedBy = "fecha")
    private List<Partido> partidos;


}
