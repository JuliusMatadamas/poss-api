package com.poss.service.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.GeneroDTO;

import org.springframework.http.ResponseEntity;

public interface GeneroService {

    ResponseEntity<ApiResponseDTO> findAll();

    ResponseEntity<ApiResponseDTO> findById(Integer id);

    ResponseEntity<ApiResponseDTO> save(GeneroDTO generoDTO);

    ResponseEntity<ApiResponseDTO> update(Integer id, GeneroDTO generoDTO);

    ResponseEntity<ApiResponseDTO> delete(Integer id);
}
