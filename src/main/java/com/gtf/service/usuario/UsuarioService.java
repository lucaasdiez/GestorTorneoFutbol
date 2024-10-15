package com.gtf.service.usuario;

import com.gtf.dto.UsuarioDTO;
import com.gtf.model.Usuario;

public interface UsuarioService {
    Usuario registrarUsuario(UsuarioDTO usuarioDTO);
    void eliminarUsuario(Integer id);
    Usuario getusuarioByID(Integer id);
    UsuarioDTO convertirUsuarioADTO(Usuario usuario);
}
