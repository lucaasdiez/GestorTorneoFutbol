package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
    private String nombre;

    private EstadoEnum estadoEquipo;

    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;
    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;
    @OneToMany(mappedBy = "equipo_local")
    private List<Partido> partidosLocal;
    @OneToMany(mappedBy = "equipo_visitante")
    private List<Partido> partidosVisitante;
    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private EstadisticaEquipo estadisticaEquipo;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<EventoPartido> eventoPartidos;


}
