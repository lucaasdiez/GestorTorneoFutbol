package com.gtf.repository;

import com.gtf.model.EventoPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoPartidoRepository extends JpaRepository<EventoPartido, Integer> {
    List<EventoPartido> findEventoPartidoByEquipoNombre(String equipoNombre);
    List<EventoPartido> findEventoPartidoByJugadorNombre(String jugadorNombre);
}
