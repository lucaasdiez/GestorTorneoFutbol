package com.gtf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventoPartido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String evento; //amarrilla, roja, gol
    private int minuto;


    @OneToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;
    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
    @OneToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
}
