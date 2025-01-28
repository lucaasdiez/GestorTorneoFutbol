package com.gtf.service.usuario;

import com.gtf.dto.UsuarioDTO;
import com.gtf.model.Usuario;

import java.util.List;

public interface UsuarioService {
    void registrarUsuario(UsuarioDTO usuarioDTO);
    void eliminarUsuario(String nombreUsuario);
    Usuario getUsuarioByNombreUsuario(String usuario);
    UsuarioDTO convertirUsuarioADTO(Usuario usuario);
    List<UsuarioDTO> convertirUsuarioDTO(List<Usuario> usuarios);
    List<Usuario> getAllUsuarios();
}
