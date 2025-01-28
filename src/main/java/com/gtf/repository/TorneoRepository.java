package com.gtf.repository;

import com.gtf.enums.TorneoEstado;
import com.gtf.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
    List<Torneo> findTorneoByEstado(TorneoEstado estado);

    Optional<Torneo> findTorneoByNombreIgnoreCase(String nombre);

    /*List<Torneo> findAllByUsuarioDni(String dni);*/
}
