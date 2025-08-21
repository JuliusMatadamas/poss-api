package com.poss.controllers.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.AsentamientoDTO;
import com.poss.services.admin.AsentamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/asentamientos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AsentamientoController {

    private final AsentamientoService asentamientoService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
        return asentamientoService.findById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> findAll() {
        return asentamientoService.findAll();
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody AsentamientoDTO asentamientoDTO) {
        return asentamientoService.create(asentamientoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody AsentamientoDTO asentamientoDTO) {
        return asentamientoService.update(id, asentamientoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
        return asentamientoService.delete(id);
    }

    @GetMapping("/codigo_postal/{id}")
    public ResponseEntity<ApiResponseDTO> findByCodigoPostalId(@PathVariable Integer id) {
        return asentamientoService.findByCodigoPostalId(id);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<ApiResponseDTO> findByNombre(@PathVariable String nombre) {
        return asentamientoService.findByNombreContaining(nombre);
    }
}
