package com.gtf.controller;

import com.gtf.exeptions.ResourceNotFoundException;
import com.gtf.model.Torneo;
import com.gtf.response.ApiResponse;
import com.gtf.service.torneo.TorneoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/torneo")
public class TorneoController {
    private final TorneoService torneoService;

    @GetMapping("/{id}/torneo")
    public ResponseEntity<ApiResponse> getTorneo(@PathVariable Integer id) {
        try {
            Torneo torneo = torneoService.getTorneoById(id);
            return ResponseEntity.ok(new ApiResponse("Torneo", torneo));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
