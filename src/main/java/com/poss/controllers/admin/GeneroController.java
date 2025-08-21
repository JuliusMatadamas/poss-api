package com.poss.controllers.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.GeneroDTO;
import com.poss.services.admin.GeneroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> findAll() {
        return generoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
        return generoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> save(@Valid @RequestBody GeneroDTO generoDTO) {
        return generoService.save(generoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody GeneroDTO generoDTO) {
        return generoService.update(id, generoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
        return generoService.delete(id);
    }
}
