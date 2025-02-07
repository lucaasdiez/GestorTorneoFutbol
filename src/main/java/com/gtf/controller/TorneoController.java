package com.gtf.controller;

import com.gtf.dto.TorneoDTO;
import com.gtf.enums.TorneoEstado;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Torneo;
import com.gtf.response.ApiResponse;
import com.gtf.service.torneo.TorneoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/torneos")
public class TorneoController {
    private final TorneoService torneoService;

    @GetMapping("/torneo")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getTorneoByNombre(@RequestParam String nombreTorneo){
        try {
            Torneo torneo = torneoService.getTorneoByNombre(nombreTorneo);
            TorneoDTO torneoDTO = torneoService.convertirATorneoDTO(torneo);
            return ResponseEntity.ok(new ApiResponse("Torneo", torneoDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/torneo/estado")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getTorneosByEstado(@RequestParam TorneoEstado estado){
        try{
            List<Torneo> torneos = torneoService.getTorneosPorEstado(estado);
            List<TorneoDTO> torneoDTOS = torneoService.convertirATorneosDTO(torneos);
            return ResponseEntity.ok(new ApiResponse("Torneos", torneoDTOS));
        }catch (Exception e){
            return ResponseEntity.badRequest().body( new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/torneo/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> crearTorneo(@RequestParam String torneoNombre){
        try {
            torneoService.crearTorneo(torneoNombre);
            return ResponseEntity.ok(new ApiResponse("Torneo Creado Correctamente", null));
        }catch (Exception e){
            return ResponseEntity.badRequest().body( new ApiResponse(e.getMessage(), null));
        }
    }



}
