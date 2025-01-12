package com.gtf.controller;

import com.gtf.dto.PartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Partido;
import com.gtf.response.ApiResponse;
import com.gtf.service.partido.PartidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
    private final PartidoService partidoService;

    @PostMapping("/partido/agregar")
    public ResponseEntity<ApiResponse> agregarPartido(@RequestBody PartidoDTO partido) {
        try {
            Partido partidoAgregado = partidoService.agregarPartido(partido);
            PartidoDTO partidoDTO= partidoService.convertirPartidoAPartidoDTO(partidoAgregado);
            return ResponseEntity.ok(new ApiResponse("Partido Agregado", partidoDTO));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/busqueda")
    public ResponseEntity<ApiResponse> busquedarPartidos(@RequestParam(required = false) int fecha,
                                                         @RequestParam(required = false) String equipoLocal,
                                                         @RequestParam(required = false) String equipoVisitante){
        try {
            List<Partido> partidos = partidoService.getPartidosByFechaOrEquipoLocalOrEquipoVisitante(fecha, equipoLocal, equipoVisitante);
            List<PartidoDTO> partidoDTOS= partidoService.convertirAPartidosDTO(partidos);
            return ResponseEntity.ok(new ApiResponse("Partidos", partidoDTOS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/partido/actualizar")
    public ResponseEntity<ApiResponse> actualizarPartido(@RequestBody PartidoDTO partido) {
        try {
            Partido partidoActualizado = partidoService.actualizarPartido(partido);
            PartidoDTO partidoDTO = partidoService.convertirPartidoAPartidoDTO(partidoActualizado);
            return ResponseEntity.ok(new ApiResponse("Partido Actualizado", partidoDTO));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/partido/{id}")
    public ResponseEntity<ApiResponse> obtenerPartido(@PathVariable Integer id) {
        try {
            Partido partido = partidoService.getPartidoById(id);
            PartidoDTO partidoDTO = partidoService.convertirPartidoAPartidoDTO(partido);
            return ResponseEntity.ok(new ApiResponse("Partido", partidoDTO));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
