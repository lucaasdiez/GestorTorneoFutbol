package com.gtf.service.usuario;

import com.gtf.dto.UsuarioDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Usuario;
import com.gtf.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        Optional.of(usuarioDTO)
                .filter(usuario -> !usuarioRepository.existsUsuarioByUsername(usuario.getUsername()))
                .map(usuarioDTOReq ->{
                    Usuario usuario = Usuario.builder()
                            .username(usuarioDTOReq.getUsername())
                            .password(usuarioDTOReq.getPassword())
                            .dni(usuarioDTOReq.getDni())
                            .estadoCuenta(EstadoEnum.Activado)
                            .build();
                    return usuarioRepository.save(usuario);
                }).orElseThrow(()-> new ResourceNotFoundException("Usuario ya existente"));
    }

    @Override
    public void eliminarUsuario(String nombreUsuario) {
       Usuario usuario = usuarioRepository.findByUsername(nombreUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
       usuario.setEstadoCuenta(EstadoEnum.Desactivado);
       usuarioRepository.save(usuario);
    }


    @Override
    public Usuario getUsuarioByNombreUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO convertirUsuarioADTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> convertirUsuarioDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .toList();
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
