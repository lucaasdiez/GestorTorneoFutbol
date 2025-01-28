package com.gtf.repository;

import com.gtf.model.EstadisticaJugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadisticaJugadorRepository extends JpaRepository<EstadisticaJugador, Integer> {
    EstadisticaJugador findEstadisticaJugadorByJugadorId(Integer id );
    EstadisticaJugador findEstadisticaJugadorByJugadorNombreIgnoreCase(String nombre);

    EstadisticaJugador findEstadisticaJugadorByJugadorDni(String dni);
}
