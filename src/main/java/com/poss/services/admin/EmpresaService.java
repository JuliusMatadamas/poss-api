package com.poss.services.admin;

import org.springframework.http.ResponseEntity;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EmpresaDTO;

public interface EmpresaService {

	ResponseEntity<ApiResponseDTO> findById(Integer id);

    ResponseEntity<ApiResponseDTO> findAll();

    ResponseEntity<ApiResponseDTO> create(EmpresaDTO empresaDTO);

    ResponseEntity<ApiResponseDTO> update(Integer id, EmpresaDTO empresaDTO);

    ResponseEntity<ApiResponseDTO> delete(Integer id);

    ResponseEntity<ApiResponseDTO> findByNombreEmpresaLargoContaining(String nombre);
}
