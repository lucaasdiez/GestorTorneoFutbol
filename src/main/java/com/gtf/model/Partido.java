package com.gtf.model;

import jakarta.persistence.*;
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
    private String resultado;


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
