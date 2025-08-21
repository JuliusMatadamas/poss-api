package com.poss.service.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.GeneroDTO;
import com.poss.entities.admin.Genero;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.GeneroMapper;
import com.poss.repository.admin.GeneroRepository;
import com.poss.utils.Meta;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<GeneroDTO> generos = generoRepository.findAll().stream()
                .map(GeneroMapper::toDto)
                .toList();

		Meta meta = new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value());
        ApiResponseDTO response = new ApiResponseDTO(meta, generos);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado."));
        Meta meta = new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value());
        ApiResponseDTO response = new ApiResponseDTO(meta, genero);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> save(GeneroDTO generoDTO) {
        Genero genero = GeneroMapper.toEntity(generoDTO);
        Genero savedGenero = generoRepository.save(genero);
        Meta meta = new Meta(UUID.randomUUID().toString(), "Created", HttpStatus.CREATED.value());
        ApiResponseDTO response = new ApiResponseDTO(meta, savedGenero);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, GeneroDTO generoDTO) {
        Genero generoExistente = generoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado para actualizar."));

        generoExistente.setNombre(generoDTO.getNombre());
        Genero updatedGenero = generoRepository.save(generoExistente);
        Meta meta = new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value());
        ApiResponseDTO response = new ApiResponseDTO(meta, updatedGenero);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!generoRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El género con ID " + id + " no fue encontrado para eliminar.");
        }

		generoRepository.deleteById(id);

		Meta meta = new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value());
        ApiResponseDTO response = new ApiResponseDTO(meta, "Género con ID " + id + " eliminado correctamente.");
        return ResponseEntity.ok(response);
    }
}
