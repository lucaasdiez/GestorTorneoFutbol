package com.gtf.service.usuario;

import com.gtf.auth.AuthResponse;
import com.gtf.config.JwtService;
import com.gtf.dto.UsuarioDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.enums.RoleEnum;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Usuario;
import com.gtf.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = Optional.of(usuarioDTO)
                .filter(usuarioReq -> !usuarioRepository.existsUsuarioByUsername(usuarioReq.getUsername()))
                .map(usuarioDTOReq -> {
                    Usuario nuevoUsuario = Usuario.builder()
                            .username(usuarioDTOReq.getUsername())
                            .password(passwordEncoder.encode(usuarioDTOReq.getPassword()))
                            .dni(usuarioDTOReq.getDni())
                            .estadoCuenta(EstadoEnum.Activado)
                            .role(RoleEnum.USER)
                            .build();
                    return usuarioRepository.save(nuevoUsuario); // Guardamos y retornamos el usuario
                })
                .orElseThrow(() -> new ResourceNotFoundException("Usuario ya existente"));
        var jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
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
