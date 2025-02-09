package com.gtf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^\\d+-\\d+$",  message = "El resultado debe tener el formato 'X-Y', donde X e Y son números")
    private String resultado;
    private boolean finalizado;


    @ManyToOne
    @JoinColumn(name = "local_id")
    private Equipo equipo_local;
    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Equipo equipo_visitante;
    @ManyToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro arbitro;
    @ManyToOne
    @JoinColumn(name = "fecha_id")
    private Fecha fecha;
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoPartido> eventoPartido;
}
