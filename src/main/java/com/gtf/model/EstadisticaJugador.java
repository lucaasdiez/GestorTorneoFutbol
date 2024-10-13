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
public class EstadisticaJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int goles;
    private int asistencias;
    private int tarjetaRoja;
    private int tarjetaAmarilla;
    private int minJugados;

    @OneToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
}
