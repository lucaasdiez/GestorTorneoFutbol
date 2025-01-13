package com.gtf.repository;

import com.gtf.model.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FechaRepository extends JpaRepository<Fecha, Integer> {

    Optional<Fecha> findByNumero(int numero);

    List<Fecha> findAllByTorneoNombreIgnoreCase(String torneo);

    List<Fecha> findAllByFechaDia(LocalDate dia);
}
