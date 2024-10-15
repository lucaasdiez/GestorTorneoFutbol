package com.gtf.service.usuario;

import com.gtf.dto.UsuarioDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Usuario;
import com.gtf.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        return Optional.of(usuarioDTO)
                .filter(usuario -> !usuarioRepository.existsUsuarioByUsuario(usuario.getUsuario()))
                .map(usuarioDTOReq ->{
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(usuarioDTOReq.getUsuario());
                    usuario.setPassword(usuarioDTOReq.getPassword());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(()-> new ResourceNotFoundException("Usuario ya existente"));
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario getusuarioByID(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO convertirUsuarioADTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
