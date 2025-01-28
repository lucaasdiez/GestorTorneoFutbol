package com.gtf.controller;

import com.gtf.dto.EstadisticaJugadorDTO;
import com.gtf.dto.JugadorDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.EstadisticaJugador;
import com.gtf.model.Jugador;
import com.gtf.response.ApiResponse;
import com.gtf.service.estadisticaJugador.EstadisticaJugadorService;
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
    private final EstadisticaJugadorService estadisticaJugadorService;

    @GetMapping("/jugador/{dni}")
    public ResponseEntity<ApiResponse> getJugador(@PathVariable String dni) {
        try {
            Jugador jugador = jugadorService.getJugadorByDni(dni);
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

    @GetMapping("/jugador/{dni}/estadistica")
    public ResponseEntity<ApiResponse> getJugadorEstadistica(@PathVariable String dni) {
        try {
            EstadisticaJugador estadisticaJugador = estadisticaJugadorService.getEstadisticaJugadorByJugadorDni(dni);
            EstadisticaJugadorDTO estadisticaJugadorDTO = estadisticaJugadorService.convertirEstadisticaJugadorADTO(estadisticaJugador);
            return ResponseEntity.ok(new ApiResponse("Estadistica", estadisticaJugadorDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/jugador/agregar")
    public ResponseEntity<ApiResponse> agregarJugador(@RequestBody JugadorDTO jugador){
        try {
           jugadorService.agregarJugador(jugador);
            return ResponseEntity.ok(new ApiResponse("Jugador agregado correctamente", null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/jugador/{dni}/estadisticas/agregar")
    public ResponseEntity<ApiResponse> agregarEstadisticaJugador(@PathVariable String dni,
                                                                 @RequestBody EstadisticaJugadorDTO estadisticaJugador){
        try {
              estadisticaJugadorService.agregarEstadisticaJugador(estadisticaJugador, dni);
            return ResponseEntity.ok(new ApiResponse("Estadistica Agregada", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
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
