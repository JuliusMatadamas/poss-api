package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.MunicipioDTO;
import org.springframework.http.ResponseEntity;

public interface MunicipioService {

    ResponseEntity<ApiResponseDTO> findAll();
    ResponseEntity<ApiResponseDTO> findById(Integer id);
	ResponseEntity<ApiResponseDTO> findByEstadoId(Integer id);
	ResponseEntity<ApiResponseDTO> findByNombreContainingIgnoreCase(String nombre);
    ResponseEntity<ApiResponseDTO> save(MunicipioDTO municipioDTO);
    ResponseEntity<ApiResponseDTO> update(Integer id, MunicipioDTO municipioDTO);
    ResponseEntity<ApiResponseDTO> delete(Integer id);
}
