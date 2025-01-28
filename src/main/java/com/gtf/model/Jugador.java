package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private String posicion;
    private EstadoEnum estado;

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
