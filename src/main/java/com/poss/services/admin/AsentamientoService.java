package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.AsentamientoDTO;
import org.springframework.http.ResponseEntity;

public interface AsentamientoService {

    ResponseEntity<ApiResponseDTO> findById(Integer id);

    ResponseEntity<ApiResponseDTO> findAll();

    ResponseEntity<ApiResponseDTO> create(AsentamientoDTO asentamientoDTO);

    ResponseEntity<ApiResponseDTO> update(Integer id, AsentamientoDTO asentamientoDTO);

    ResponseEntity<ApiResponseDTO> delete(Integer id);

    ResponseEntity<ApiResponseDTO> findByCodigoPostalId(Integer id);

    ResponseEntity<ApiResponseDTO> findByNombreContaining(String nombre);
}
