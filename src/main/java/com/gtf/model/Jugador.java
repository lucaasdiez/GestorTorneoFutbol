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
    //cascade = CascadeType.ALL asegura que cualquier operación realizada en Jugador (como REMOVE) se propague a las estadísticas asociadas.
    //orphanRemoval = true asegura que si un Jugador es eliminado, todas las estadísticas asociadas también se eliminen.
    @OneToOne(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private EstadisticaJugador estadisticaJugador;
    @OneToOne(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private EventoPartido eventoPartido;
}
