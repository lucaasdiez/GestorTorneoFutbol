package com.gtf.model;

import com.gtf.enums.EstadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Arbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio.")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres.")
    private String apellido;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El DNI no puede estar vacío.")
    @Size(max = 10, message = "El DNI no puede superar los 20 caracteres.")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe ser un número de 7 u 8 dígitos.")
    private String dni;

    private EstadoEnum estado;

    @OneToMany(mappedBy = "arbitro")
    private List<Partido> partidos;



}
