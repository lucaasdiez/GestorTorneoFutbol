package com.gtf.controller;

import com.gtf.dto.FechaDTO;
import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Fecha;
import com.gtf.response.ApiResponse;
import com.gtf.service.fecha.FechaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fechas")
public class FechaController {
    private final FechaService fechaService;

    @GetMapping("/fecha/{numeroFecha}")
    public ResponseEntity<ApiResponse> getFechaByNumeroFecha(@PathVariable int numeroFecha) {
        try {
            Fecha fecha = fechaService.getFechaByNumero(numeroFecha);
            FechaDTO fechaDTO = fechaService.convertirFechaADTO(fecha);
            return ResponseEntity.ok(new ApiResponse("Fecha", fechaDTO));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/todas/filtro")
    public ResponseEntity<ApiResponse> todasFechasTorneo(@RequestParam(required = false) String torneoNombre,
                                                         @RequestParam(required = false) LocalDate dia) {
        try {
            List<Fecha> fechas = fechaService.getAllFechasByDiaOrTorneo(dia, torneoNombre);
            List<FechaDTO> fechaDTOS = fechaService.convertirAFechasDTO(fechas);
            return ResponseEntity.ok(new ApiResponse("Fechas", fechaDTOS));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/fecha/agregar")
    public ResponseEntity<ApiResponse> agregarFecha(@RequestBody FechaDTO fecha) {
        try {
           Fecha fechaNueva =  fechaService.agregarFecha(fecha);
           FechaDTO fechaDTO = fechaService.convertirFechaADTO(fechaNueva);
           return ResponseEntity.ok(new ApiResponse("Fecha", fechaDTO));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }


}
