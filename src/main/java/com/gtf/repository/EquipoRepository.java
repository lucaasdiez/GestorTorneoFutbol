package com.gtf.repository;

import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    Optional<Equipo> findByNombreIgnoreCase(String nombre);

    List<Equipo> findByTorneoNombreIgnoreCase(String torneoNombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
