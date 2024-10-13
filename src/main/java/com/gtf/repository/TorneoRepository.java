package com.gtf.repository;

import com.gtf.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
    List<Torneo> findTorneoByEstado(String estado);
}
