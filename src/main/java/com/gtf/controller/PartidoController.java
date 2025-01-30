package com.gtf.controller;

import com.gtf.dto.EventoPartidoDTO;
import com.gtf.dto.PartidoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.EventoPartido;
import com.gtf.model.Partido;
import com.gtf.response.ApiResponse;
import com.gtf.service.eventoPartido.EventoPartidoService;
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
    private final EventoPartidoService eventoPartidoService;

    @PostMapping("/partido/agregar")
    public ResponseEntity<ApiResponse> agregarPartido(@RequestBody PartidoDTO partido) {
        try {
            partidoService.agregarPartido(partido);
            return ResponseEntity.ok(new ApiResponse("Partido Agregado", null));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/partido/eventoPartido/agregar")
    public ResponseEntity<ApiResponse> agregarEventoPartido(@RequestBody EventoPartidoDTO eventoPartido,
                                                            @RequestParam Integer idEquipo,
                                                            @RequestParam Integer idPartido,
                                                            @RequestParam String dniJugador) {
        try{
            eventoPartidoService.agregarEvento(eventoPartido, idEquipo, dniJugador,idPartido);
            return ResponseEntity.ok(new ApiResponse("Evento del Partido Agregado", null));
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

    @GetMapping("/partido/{idPartido}/eventos")
    public ResponseEntity<ApiResponse> eventosPartido(@PathVariable Integer idPartido){
        try {
            List<EventoPartido> eventoPartido = eventoPartidoService.getEventoPartidosByPartidoId(idPartido);
            List<EventoPartidoDTO> eventoPartidoDTOS = eventoPartidoService.convertirAEventosPartidosDTO(eventoPartido);
            return ResponseEntity.ok(new ApiResponse("Eventos", eventoPartidoDTOS));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/partido/eventos/busqueda")
    public ResponseEntity<ApiResponse> getEventosPartidos(@RequestParam(required = false) String equipoNombre,
                                                          @RequestParam(required = false) String jugadorNombre){
        try {
            List<EventoPartido> eventoPartidos= eventoPartidoService.getEventoByEquipoYJugador(equipoNombre, jugadorNombre);
            List<EventoPartidoDTO> eventoPartidoDTOS = eventoPartidoService.convertirAEventosPartidosDTO(eventoPartidos);
            return ResponseEntity.ok(new ApiResponse("Eventos", eventoPartidoDTOS));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }




    @PutMapping("/partido/actualizar")
    public ResponseEntity<ApiResponse> actualizarPartido(@RequestBody PartidoDTO partido) {
        try {
            partidoService.actualizarPartido(partido);
            return ResponseEntity.ok(new ApiResponse("Partido Actualizado", null));
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
