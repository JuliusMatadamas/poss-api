package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.CodigoPostalDTO;
import org.springframework.http.ResponseEntity;

public interface CodigoPostalService {
    ResponseEntity<ApiResponseDTO> findAll();
    ResponseEntity<ApiResponseDTO> findById(Integer id);
    ResponseEntity<ApiResponseDTO> save(CodigoPostalDTO codigoPostalDTO);
    ResponseEntity<ApiResponseDTO> update(Integer id, CodigoPostalDTO codigoPostalDTO);
    ResponseEntity<ApiResponseDTO> delete(Integer id);
    ResponseEntity<ApiResponseDTO> findByMunicipioId(Integer id);
    ResponseEntity<ApiResponseDTO> findByCodigoPostalContaining(String codigoPostal);
}
