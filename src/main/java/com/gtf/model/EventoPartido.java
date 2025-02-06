package com.gtf.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoPartido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String evento; //amarrilla, roja, gol
    private int minuto;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partido_id")
    private Partido partido;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
}
