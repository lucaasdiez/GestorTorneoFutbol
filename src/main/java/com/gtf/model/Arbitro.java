package com.gtf.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Arbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "arbitro")
    private List<Partido> partidos;



}
