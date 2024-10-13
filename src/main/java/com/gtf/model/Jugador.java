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
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String posicion;
    private int goles;
    private int tarjetaAmarilla;
    private int tarjetaRoja;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
    @OneToOne(mappedBy = "jugador")
    private EstadisticaJugador estadisticaJugador;
    @OneToOne(mappedBy = "jugador")
    private EventoPartido eventoPartido;
}
