package com.gtf.repository;

import com.gtf.model.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FechaRepository extends JpaRepository<Fecha, Integer> {

    Optional<Fecha> findByNumero(int numero);
}
