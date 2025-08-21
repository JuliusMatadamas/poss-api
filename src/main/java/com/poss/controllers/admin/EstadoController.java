package com.poss.controllers.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EstadoDTO;
import com.poss.services.admin.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> findAll() {
        return estadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
        return estadoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> save(@Valid @RequestBody EstadoDTO estadoDTO) {
        return estadoService.save(estadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody EstadoDTO estadoDTO) {
        return estadoService.update(id, estadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
        return estadoService.delete(id);
    }
}
