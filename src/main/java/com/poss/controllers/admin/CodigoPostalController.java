package com.poss.controllers.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.CodigoPostalDTO;
import com.poss.services.admin.CodigoPostalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/codigos_postales")
@RequiredArgsConstructor
public class CodigoPostalController {

    private final CodigoPostalService codigoPostalService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO> findAll() {
        return codigoPostalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
        return codigoPostalService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> save(@Valid @RequestBody CodigoPostalDTO codigoPostalDTO) {
        return codigoPostalService.save(codigoPostalDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody CodigoPostalDTO codigoPostalDTO) {
        return codigoPostalService.update(id, codigoPostalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
        return codigoPostalService.delete(id);
    }

    @GetMapping("/municipio/{id}")
    public ResponseEntity<ApiResponseDTO> findByMunicipioId(@PathVariable Integer id) {
        return codigoPostalService.findByMunicipioId(id);
    }

    @GetMapping("/buscar/{codigoPostal}")
    public ResponseEntity<ApiResponseDTO> findByCodigoPostalContaining(@PathVariable String codigoPostal) {
        return codigoPostalService.findByCodigoPostalContaining(codigoPostal);
    }
}
