package com.gtf.controller;

import com.gtf.dto.equipo.SimpleEquipoDTO;
import com.gtf.dto.equipo.FullEquipoDTO;
import com.gtf.dto.EstadisticaEquipoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.model.EstadisticaEquipo;
import com.gtf.response.ApiResponse;
import com.gtf.service.equipo.EquipoService;
import com.gtf.service.estadisticaEquipo.EstadisticaEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/equipos")
@CrossOrigin("*")
public class EquipoController {
    private final EquipoService equipoService;
    private final EstadisticaEquipoService estadisticaEquipoService;

    @GetMapping("/equipo")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getEquipoByNombre(@RequestParam String nombreEquipo){
        try {
            Equipo equipo = equipoService.getEquipoByNombre(nombreEquipo);
            FullEquipoDTO fullEquipoDTO = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo", fullEquipoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getEquiposByTorneo(@RequestParam String torneoNombre){
        try {
            List<Equipo> equipos = equipoService.getEquiposByTorneo(torneoNombre);
            List<SimpleEquipoDTO> fullEquipoDTOS = equipoService.convertirAEquiposDTO(equipos);
            return ResponseEntity.ok(new ApiResponse("Equipos del Torneo", fullEquipoDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/equipo/estadisticas")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> agregarEquipo(@RequestBody SimpleEquipoDTO equipoDTO){
        try {
             equipoService.agregarEquipo(equipoDTO);
            return ResponseEntity.ok(new ApiResponse("Equipo Agregado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/equipo/{idEquipo}/estadisticas/agregar")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> agregarEstadisticaEquipo(@RequestBody EstadisticaEquipoDTO estadisticaEquipoDTO,
                                                                @PathVariable Integer idEquipo){
        try {
            estadisticaEquipoService.agregarEstditicaEquipo(estadisticaEquipoDTO, idEquipo);
            return ResponseEntity.ok(new ApiResponse("Estadisticas del equipo agregadas", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/equipo/{idEquipo}/eliminar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> eliminarEquipo(@PathVariable Integer idEquipo){
        try {
            equipoService.eliminarEquipo(idEquipo);
            return ResponseEntity.ok(new ApiResponse("Equipo Eliminado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/equipo/{idEquipo}/modificar")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> modificarEquipo(@PathVariable Integer idEquipo,
                                                       @RequestBody FullEquipoDTO fullEquipoDTO){
        try {
            equipoService.updateEquipo(fullEquipoDTO, idEquipo);
            return ResponseEntity.ok(new ApiResponse("Equipo modificado", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
