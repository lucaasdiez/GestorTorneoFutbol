package com.gtf.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gtf.enums.TorneoEstado;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private TorneoEstado estado;


    @OneToMany(mappedBy = "torneo")
    private List<Fecha> fechas;
    @OneToMany(mappedBy = "torneo")
    private List<Equipo> equipos;
    @ManyToMany(mappedBy = "torneos")
    private Set<Usuario> usuarios;
}
