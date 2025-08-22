package com.poss.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EmpresaDTO;
import com.poss.services.admin.EmpresaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/admin/empresas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmpresaController {
	private final EmpresaService empresaService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO> findById(@PathVariable Integer id) {
		return empresaService.findById(id);
	}

	@GetMapping
	public ResponseEntity<ApiResponseDTO> findAll() {
		return empresaService.findAll();
	}

	@PostMapping
    public ResponseEntity<ApiResponseDTO> create(@Valid @RequestBody EmpresaDTO empresaDTO) {
		return empresaService.create(empresaDTO);
	}

	@PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody EmpresaDTO empresaDTO) {
		return empresaService.update(id, empresaDTO);
	}

	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> delete(@PathVariable Integer id) {
		return empresaService.delete(id);
	}

	@GetMapping("/buscar/{nombre}")
    public ResponseEntity<ApiResponseDTO> findByNombreEmpresaLargoContaining(@PathVariable String nombre) {
		return empresaService.findByNombreEmpresaLargoContaining(nombre);
	}
}
