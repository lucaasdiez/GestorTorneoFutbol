package com.gtf.repository;

import com.gtf.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByUsuario(String usuario);
}
