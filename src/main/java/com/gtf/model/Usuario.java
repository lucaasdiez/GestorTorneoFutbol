package com.gtf.model;

import com.gtf.enums.UsuarioEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String usuario;
    private String password;

    private UsuarioEstado estadoCuenta;

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
