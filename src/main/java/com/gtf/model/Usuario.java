package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dni;
    private String username;
    private String password;

    private EstadoEnum estadoCuenta;

    @OneToOne(mappedBy = "usuario")
    private Equipo equipo;

    @ManyToMany
    @JoinTable(
            name = "usuario_torneo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "torneo_id")
    )
    private Set<Torneo> torneos;
}
