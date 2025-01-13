package com.gtf.controller;

import com.gtf.dto.EquipoDTO;
import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaEquipo;
import com.gtf.response.ApiResponse;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.estadisticaEquipo.EstadisticaEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final EquipoService equipoService;
    private final EstadisticaEquipoService estadisticaEquipoService;

    @GetMapping("/equipo")
    public ResponseEntity<ApiResponse> getEquipoByNombre(@RequestParam String nombreEquipo){
        try {
            Equipo equipo = equipoService.getEquipoByNombre(nombreEquipo);
            EquipoDTO equipoDTO = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo", equipoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse> getEquiposByTorneo(@RequestParam String torneoNombre){
        try {
            List<Equipo> equipos = equipoService.getEquiposByTorneo(torneoNombre);
            List<EquipoDTO> equipoDTOS= equipoService.convertirAEquiposDTO(equipos);
            return ResponseEntity.ok(new ApiResponse("Equipos del Torneo", equipoDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/equipo/estadisticas")
    public ResponseEntity<ApiResponse> getEquiposEstadisticas(@RequestParam String nombreEquipo){
        try {
            EstadisticaEquipo estadisticaEquipo = estadisticaEquipoService.getEstadisticaEquipoByNombreEquipo(nombreEquipo);
            EstadisticaEquipoDTO estadisticaEquipoDTO = estadisticaEquipoService.convertirEstadicticaEquipoADTO(estadisticaEquipo);
            return ResponseEntity.ok(new ApiResponse("Estadisticas del equipo", estadisticaEquipoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/equipo/agregar")
    public ResponseEntity<ApiResponse> agregarEquipo(@RequestBody EquipoDTO equipoDTO){
        try {
            Equipo equipo = equipoService.agregarEquipo(equipoDTO);
            EquipoDTO equipoDTO1 = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo Agregado", equipoDTO1));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/equipo/{idEquipo}/estadisticas/agregar")
    public ResponseEntity<ApiResponse> agregarEstadisticaEquipo(@RequestBody EstadisticaEquipoDTO estadisticaEquipoDTO,
                                                                @PathVariable Integer idEquipo){
        try {
            EstadisticaEquipo estadisticaEquipo = estadisticaEquipoService.agregarEstditicaEquipo(estadisticaEquipoDTO, idEquipo);
            EstadisticaEquipoDTO estadisticaEquipoDTO1 = estadisticaEquipoService.convertirEstadicticaEquipoADTO(estadisticaEquipo);
            return ResponseEntity.ok(new ApiResponse("Estadisticas del equipo agregadas", estadisticaEquipoDTO1));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/equipo/{idEquipo}/eliminar")
    public ResponseEntity<ApiResponse> eliminarEquipo(@PathVariable Integer idEquipo){
        try {
            equipoService.eliminarEquipo(idEquipo);
            return ResponseEntity.ok(new ApiResponse("Equipo Eliminado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/equipo/{idEquipo}/modificar")
    public ResponseEntity<ApiResponse> modificarEquipo(@PathVariable Integer idEquipo,
                                                       @RequestBody EquipoDTO equipoDTO){
        try {
            Equipo equipo = equipoService.updateEquipo(equipoDTO, idEquipo);
            EquipoDTO equipoDTO1 = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo modificado", equipoDTO1));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
