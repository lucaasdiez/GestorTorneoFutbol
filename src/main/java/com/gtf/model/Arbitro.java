package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Arbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private EstadoEnum estado;

    @OneToMany(mappedBy = "arbitro")
    private List<Partido> partidos;



}
