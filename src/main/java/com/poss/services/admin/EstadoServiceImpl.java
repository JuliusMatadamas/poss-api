package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EstadoDTO;
import com.poss.entities.admin.Estado;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.EstadoMapper;
import com.poss.repositories.admin.EstadoRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<EstadoDTO> estados = estadoRepository.findAll().stream()
                .map(EstadoMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(estados);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El estado con ID " + id + " no fue encontrado."));
        return ApiResponseUtil.buildSuccessResponse(EstadoMapper.toDto(estado));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> save(EstadoDTO estadoDTO) {
        Estado estado = EstadoMapper.toEntity(estadoDTO);
        Estado savedEstado = estadoRepository.save(estado);
        return ApiResponseUtil.buildCreatedResponse(EstadoMapper.toDto(savedEstado));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, EstadoDTO estadoDTO) {
        Estado estadoExistente = estadoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El estado con ID " + id + " no fue encontrado para actualizar."));

        estadoExistente.setNombre(estadoDTO.getNombre());
        Estado updatedEstado = estadoRepository.save(estadoExistente);
        return ApiResponseUtil.buildSuccessResponse(EstadoMapper.toDto(updatedEstado));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!estadoRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El estado con ID " + id + " no fue encontrado para eliminar.");
        }
        estadoRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Estado con ID " + id + " eliminado correctamente.");
    }
}
