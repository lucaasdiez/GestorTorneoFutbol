package com.gtf.controller;

import com.gtf.dto.JugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Jugador;
import com.gtf.response.ApiResponse;
import com.gtf.service.jugador.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    @GetMapping("/jugador/{nombre}")
    public ResponseEntity<ApiResponse> getJugador(@PathVariable String nombre) {
        try {
            Jugador jugador = jugadorService.getJugadorByNombre(nombre);
            JugadorDTO jugadorDTO = jugadorService.convertirAJugadorDTO(jugador);
            return ResponseEntity.ok(new ApiResponse("Jugador", jugadorDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/equipo/{equipoNombre}")
    public ResponseEntity<ApiResponse> getJugadorEquipo(@PathVariable String equipoNombre) {
        try {
            List<Jugador> jugadores = jugadorService.getJugadoresByEquipoNombre(equipoNombre);
            List<JugadorDTO> jugadorDTOS = jugadorService.convertirAJugadoresDTO(jugadores);
            return ResponseEntity.ok(new ApiResponse("Jugador", jugadorDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/jugador/agregar")
    public ResponseEntity<ApiResponse> agregarJugador(@RequestBody JugadorDTO jugador){
        try {
            Jugador jugadorAgregado = jugadorService.agregarJugador(jugador);
            JugadorDTO jugadorDTO = jugadorService.convertirAJugadorDTO(jugadorAgregado);
            return ResponseEntity.ok(new ApiResponse("Jugador", jugadorDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/jugador/{idJugador}/eliminar")
    public ResponseEntity<ApiResponse> eliminarJugador(@PathVariable Integer idJugador){
        try {
            jugadorService.eliminarJugador(idJugador);
            return ResponseEntity.ok(new ApiResponse("Jugador", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
