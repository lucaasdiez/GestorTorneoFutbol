package com.gtf.repository;

import com.gtf.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByUsuario(String usuario);

    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);
}
