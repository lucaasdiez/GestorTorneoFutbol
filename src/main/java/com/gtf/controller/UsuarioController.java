package com.gtf.controller;

import com.gtf.dto.UsuarioDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Usuario;
import com.gtf.response.ApiResponse;
import com.gtf.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse> buscarUsuarioPorId(@PathVariable Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.getusuarioByID(idUsuario);
            UsuarioDTO usuarioDTO = usuarioService.convertirUsuarioADTO(usuario);
            return ResponseEntity.ok(new ApiResponse("Usuario", usuarioDTO));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/usuario/registrar")
    public ResponseEntity<ApiResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.registrarUsuario(usuarioDTO);
            UsuarioDTO usuarioDTO2 = usuarioService.convertirUsuarioADTO(usuario);
            return ResponseEntity.ok(new ApiResponse("Usuario Guardado", usuarioDTO2));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/usuario/{idUsuario}/eliminar")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable Integer idUsuario) {
        try {
            usuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.ok(new ApiResponse("Usuario Eliminado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
