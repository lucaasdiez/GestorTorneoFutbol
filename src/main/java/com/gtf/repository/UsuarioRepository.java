package com.gtf.repository;

import com.gtf.model.Torneo;
import com.gtf.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByUsername(String usuario);

    Optional<Usuario> findByUsername(String usuario);

    Optional<Usuario> findByDni(String usuarioDni);

}
