package com.gtf.repository;

import com.gtf.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    Integer id(Integer id);
}
