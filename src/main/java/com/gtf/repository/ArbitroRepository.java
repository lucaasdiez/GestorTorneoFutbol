package com.gtf.repository;

import com.gtf.enums.EstadoEnum;
import com.gtf.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ArbitroRepository extends JpaRepository<Arbitro, Integer> {
    Optional<Arbitro> findByNombre(String nombre);

    List<Arbitro> getArbitrosByEstado(EstadoEnum estado);

    Optional<Arbitro> findByDni(String dni);
}
