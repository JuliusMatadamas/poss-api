package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.GeneroDTO;
import com.poss.entities.admin.Genero;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.GeneroMapper;
import com.poss.repositories.admin.GeneroRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<GeneroDTO> generos = generoRepository.findAll().stream()
                .map(GeneroMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(generos);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado."));
        return ApiResponseUtil.buildSuccessResponse(GeneroMapper.toDto(genero));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> save(GeneroDTO generoDTO) {
        Genero genero = GeneroMapper.toEntity(generoDTO);
        Genero savedGenero = generoRepository.save(genero);
        return ApiResponseUtil.buildCreatedResponse(GeneroMapper.toDto(savedGenero));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, GeneroDTO generoDTO) {
        Genero generoExistente = generoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado para actualizar."));

        generoExistente.setNombre(generoDTO.getNombre());
        Genero updatedGenero = generoRepository.save(generoExistente);
        return ApiResponseUtil.buildSuccessResponse(GeneroMapper.toDto(updatedGenero));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!generoRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado para eliminar.");
        }
        generoRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Género con ID " + id + " eliminado correctamente.");
    }
}
