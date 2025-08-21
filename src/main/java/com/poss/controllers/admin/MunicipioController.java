package com.poss.controllers.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.MunicipioDTO;
import com.poss.services.admin.MunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> findAll() {
        return municipioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
        return municipioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> save(@Valid @RequestBody MunicipioDTO municipioDTO) {
        return municipioService.save(municipioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MunicipioDTO municipioDTO) {
        return municipioService.update(id, municipioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
        return municipioService.delete(id);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<ApiResponseDTO> findByNombre(@PathVariable String nombre) {
        return municipioService.findByNombreContainingIgnoreCase(nombre);
    }

    @GetMapping("/estado/{id}")
    public ResponseEntity<ApiResponseDTO> findByEstadoId(@PathVariable Integer id) {
        return municipioService.findByEstadoId(id);
    }
}
