package com.gtf.repository;

import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadisticaJugadorRepository extends JpaRepository<EstadisticaJugador, Integer> {
    Optional<EstadisticaJugador> findEstadisticaJugadorByJugadorId(Integer id );
    EstadisticaJugador findEstadisticaJugadorByJugadorNombre(String nombre);
}
