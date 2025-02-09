package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

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

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El DNI no puede estar vacío.")
    @Size(max = 10, message = "El DNI no puede superar los 20 caracteres.")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe ser un número de 7 u 8 dígitos.")
    private String dni;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio.")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres.")
    private String apellido;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "La posición es obligatoria.")
    @Size(max = 50, message = "La posición no puede superar los 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "La posición solo puede contener letras y espacios.")
    private String posicion;


    private EstadoEnum estado;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
    //cascade = CascadeType.ALL asegura que cualquier operación realizada en Jugador (como REMOVE) se propague a las estadísticas asociadas.
    //orphanRemoval = true asegura que si un Jugador es eliminado, todas las estadísticas asociadas también se eliminen.
    @OneToOne(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private EstadisticaJugador estadisticaJugador;
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoPartido> eventoPartido;
}
