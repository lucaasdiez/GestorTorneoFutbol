package com.gtf.dto;

import com.gtf.dto.equipo.SimpleEquipoDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JugadorDTO {
    private Integer id;

    @NotBlank(message = "El DNI no puede estar vacío.")
    @Size(max = 10, message = "El DNI no puede superar los 20 caracteres.")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe ser un número de 7 u 8 dígitos.")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres.")
    private String apellido;

    @NotBlank(message = "La posición es obligatoria.")
    @Size(max = 50, message = "La posición no puede superar los 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "La posición solo puede contener letras y espacios.")
    private String posicion;


    private SimpleEquipoDTO equipo;
    private EstadisticaJugadorDTO estadisticaJugador;

}
