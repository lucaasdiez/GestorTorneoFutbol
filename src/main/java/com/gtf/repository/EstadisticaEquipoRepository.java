package com.gtf.repository;

import com.gtf.model.EstadisticaEquipo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadisticaEquipoRepository extends JpaRepository<EstadisticaEquipo, Integer> {
    Optional<EstadisticaEquipo> findEstadisticaEquipoByEquipoNombre(String nombre);
}
