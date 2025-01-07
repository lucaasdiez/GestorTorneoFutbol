package com.gtf.controller;

import com.gtf.dto.TorneoDTO;
import com.gtf.enums.TorneoEstado;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Torneo;
import com.gtf.response.ApiResponse;
import com.gtf.service.torneo.TorneoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/torneos")
public class TorneoController {
    private final TorneoService torneoService;

    @GetMapping("/torneo/{nombre}")
    public ResponseEntity<ApiResponse> getTorneoByNombre(@PathVariable String nombre){
        try {
            Torneo torneo = torneoService.getTorneoByNombre(nombre);
            TorneoDTO torneoDTO = torneoService.convertirATorneoDTO(torneo);
            return ResponseEntity.ok(new ApiResponse("Torneo", torneoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/torneo/estado")
    public ResponseEntity<ApiResponse> getTorneosByEstado(@RequestParam("estado")TorneoEstado estado){
        try{
            List<Torneo> torneos = torneoService.getTorneosPorEstado(estado);
            List<TorneoDTO> torneoDTOS = torneoService.convertirATorneosDTO(torneos);
            return ResponseEntity.ok(new ApiResponse("Torneos", torneoDTOS));
        }catch (Exception e){
            return ResponseEntity.badRequest().body( new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/toneo/{torneoId}/equipo/{equipoId}/agregar")
    public ResponseEntity<ApiResponse> agregarEquipoATorneo(@PathVariable Integer torneoId,
                                                            @PathVariable Integer equipoId){
        try{
            Torneo torneo = torneoService.agregarEquipoATorneo(torneoId, equipoId);
            TorneoDTO torneoDTO = torneoService.convertirATorneoDTO(torneo);
            return ResponseEntity.ok(new ApiResponse("Torneo", torneoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/torneo/crear")
    public ResponseEntity<ApiResponse> crearTorneo(@RequestBody TorneoDTO torneoDTO){
        try {
            Torneo torneo = torneoService.crearTorneo(torneoDTO);
            TorneoDTO torneoDTONuevo = torneoService.convertirATorneoDTO(torneo);
            return ResponseEntity.ok(new ApiResponse("Torneo", torneoDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body( new ApiResponse(e.getMessage(), null));
        }
    }



}
