package com.gtf.controller;

import com.gtf.dto.ArbitroDTO;
import com.gtf.enums.EstadoEnum;
import com.gtf.model.Arbitro;
import com.gtf.response.ApiResponse;
import com.gtf.service.arbitro.ArbitroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/arbitros")
public class ArbitroController {
    private final ArbitroService arbitroService;

    @PostMapping("/arbitro/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> agregarArbitro(@RequestBody ArbitroDTO arbitro) {
        arbitroService.agregarArbitro(arbitro);
        return ResponseEntity.ok(new ApiResponse("Arbitro Agregado", null));
    }

    @GetMapping("/arbitro/{dni}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getArbitro(@PathVariable String dni) {
        Arbitro arbitro = arbitroService.getArbitroByDni(dni);
        ArbitroDTO arbitroDTO = arbitroService.convertirArbitroADTO(arbitro);
        return ResponseEntity.ok(new ApiResponse("Arbitro", arbitroDTO));
    }

    @GetMapping("/arbitro/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getArbitroEstado(@PathVariable EstadoEnum estado) {
        List<Arbitro> arbitro = arbitroService.getArbitroByEstado(estado);
        List<ArbitroDTO> arbitrosDTO = arbitroService.convertirArbitrosDTO(arbitro);
        return ResponseEntity.ok(new ApiResponse("Arbitros", arbitrosDTO));
    }

    @PatchMapping("/arbitro/{dni}/cambiarEstado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> cambiarEstado(@PathVariable String dni) {
        arbitroService.cambiarEstadoArbitro(dni);
        return ResponseEntity.ok(new ApiResponse("Arbitro Cambiado", null));
    }
}
