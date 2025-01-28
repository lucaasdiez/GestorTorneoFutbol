package com.gtf.controller;

import com.gtf.dto.UsuarioDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Usuario;
import com.gtf.response.ApiResponse;
import com.gtf.service.usuario.UsuarioService;
import com.gtf.service.usuario.UsuarioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<ApiResponse> buscarUsuarioPorNombre(@PathVariable String nombreUsuario) {
        try {
            Usuario usuario = usuarioService.getUsuarioByNombreUsuario(nombreUsuario);
            UsuarioDTO usuarioDTO = usuarioService.convertirUsuarioADTO(usuario);
            return ResponseEntity.ok(new ApiResponse("Usuario", usuarioDTO));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse> getTodosUsuarios(){
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            List<UsuarioDTO> usuarioDTOS = usuarioService.convertirUsuarioDTO(usuarios);
            return ResponseEntity.ok(new ApiResponse("Usuarios", usuarioDTOS));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.registrarUsuario(usuarioDTO);
            return ResponseEntity.ok(new ApiResponse("Usuario Guardado", null));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/usuario/{nombreUsuario}/eliminar")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable String nombreUsuario) {
        try {
            usuarioService.eliminarUsuario(nombreUsuario);
            return ResponseEntity.ok(new ApiResponse("Usuario Eliminado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
