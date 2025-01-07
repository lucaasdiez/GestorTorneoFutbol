package com.gtf.controller;

import com.gtf.dto.EquipoDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Equipo;
import com.gtf.response.ApiResponse;
import com.gtf.service.equipo.EquipoService;
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

    @GetMapping("/equipo/{nombreEquipo}")
    public ResponseEntity<ApiResponse> getEquipoByNombre(@PathVariable String nombreEquipo){
        try {
            Equipo equipo = equipoService.getEquipoByNombre(nombreEquipo);
            EquipoDTO equipoDTO = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo", equipoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/todos/{torneoNombre}")
    public ResponseEntity<ApiResponse> getEquiposByTorneo(@PathVariable String torneoNombre){
        try {
            List<Equipo> equipos = equipoService.getEquiposByTorneo(torneoNombre);
            List<EquipoDTO> equipoDTOS= equipoService.convertirAEquiposDTO(equipos);
            return ResponseEntity.ok(new ApiResponse("Equipos", equipoDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/equipo/agregar")
    public ResponseEntity<ApiResponse> agregarEquipo(@RequestBody EquipoDTO equipoDTO){
        try {
            Equipo equipo = equipoService.agregarEquipo(equipoDTO);
            EquipoDTO equipoDTO1 = equipoService.convertirEquipoADto(equipo);
            return ResponseEntity.ok(new ApiResponse("Equipo", equipoDTO1));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/equipo/{idequipo}/eliminar")
    public ResponseEntity<ApiResponse> eliminarEquipo(@PathVariable Integer idequipo){
        try {
            equipoService.eliminarEquipo(idequipo);
            return ResponseEntity.ok(null);
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
            return ResponseEntity.ok(new ApiResponse("Equipo", equipoDTO1));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
