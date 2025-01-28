package com.gtf.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
