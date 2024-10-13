package com.gtf.model;

import com.gtf.dto.JugadorDTO;
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
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private int partidosJugados;
    private int golesFavor;
    private int golesContra;

    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;
    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;
    @OneToMany(mappedBy = "equipo_local")
    private List<Partido> partidosLocal;
    @OneToMany(mappedBy = "equipo_visitante")
    private List<Partido> partidosVisitante;
    @OneToOne(mappedBy = "equipo")
    private EstadisticaEquipo estadisticaEquipo;


}
