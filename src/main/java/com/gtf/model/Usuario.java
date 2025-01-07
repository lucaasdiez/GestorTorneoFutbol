package com.gtf.model;

import com.gtf.enums.UsuarioEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

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

    @OneToMany(mappedBy = "usuario")
    private List<Torneo> torneos;
}
