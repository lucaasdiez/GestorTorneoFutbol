package com.gtf.repository;

import com.gtf.model.Equipo;
import com.gtf.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    List<Jugador> findByEquipoId(Integer equipoId);
    List<Jugador> findByEquipo(Equipo equipo);

}
