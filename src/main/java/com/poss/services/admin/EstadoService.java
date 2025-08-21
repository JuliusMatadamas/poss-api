package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EstadoDTO;
import org.springframework.http.ResponseEntity;

public interface EstadoService {

    ResponseEntity<ApiResponseDTO> findAll();
    ResponseEntity<ApiResponseDTO> findById(Integer id);
    ResponseEntity<ApiResponseDTO> save(EstadoDTO estadoDTO);
    ResponseEntity<ApiResponseDTO> update(Integer id, EstadoDTO estadoDTO);
    ResponseEntity<ApiResponseDTO> delete(Integer id);
}
