package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.AsentamientoDTO;
import com.poss.entities.admin.Asentamiento;
import com.poss.entities.admin.CodigoPostal;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.AsentamientoMapper;
import com.poss.repositories.admin.AsentamientoRepository;
import com.poss.repositories.admin.CodigoPostalRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsentamientoServiceImpl implements AsentamientoService {

    private final AsentamientoRepository asentamientoRepository;
    private final CodigoPostalRepository codigoPostalRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Asentamiento asentamiento = asentamientoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Asentamiento no encontrado con ID: " + id));
        return ApiResponseUtil.buildSuccessResponse(AsentamientoMapper.toDTO(asentamiento));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<AsentamientoDTO> dtoList = asentamientoRepository.findAll().stream()
                .map(AsentamientoMapper::toDTO)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(dtoList);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> create(AsentamientoDTO asentamientoDTO) {
        if (!codigoPostalRepository.existsById(asentamientoDTO.getCodigoPostalId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El código postal con ID " + asentamientoDTO.getCodigoPostalId() + " no existe.");
        }
        Asentamiento asentamiento = AsentamientoMapper.toEntity(asentamientoDTO);
        Asentamiento savedAsentamiento = asentamientoRepository.save(asentamiento);
        return ApiResponseUtil.buildCreatedResponse(AsentamientoMapper.toDTO(savedAsentamiento));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, AsentamientoDTO asentamientoDTO) {
        Asentamiento existingAsentamiento = asentamientoRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Asentamiento no encontrado con ID: " + id));

        CodigoPostal codigoPostal = codigoPostalRepository.findById(asentamientoDTO.getCodigoPostalId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "El código postal con ID " + asentamientoDTO.getCodigoPostalId() + " no existe."));

        existingAsentamiento.setNombre(asentamientoDTO.getNombre());
        existingAsentamiento.setCodigoPostal(codigoPostal);

        Asentamiento updatedAsentamiento = asentamientoRepository.save(existingAsentamiento);
        return ApiResponseUtil.buildSuccessResponse(AsentamientoMapper.toDTO(updatedAsentamiento));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!asentamientoRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Asentamiento no encontrado con ID: " + id);
        }
        asentamientoRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Asentamiento eliminado con éxito.");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByCodigoPostalId(Integer id) {
        if (!codigoPostalRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Código Postal no encontrado con ID: " + id);
        }
        List<AsentamientoDTO> dtoList = asentamientoRepository.findByCodigoPostalId(id).stream()
                .map(AsentamientoMapper::toDTO)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(dtoList);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByNombreContaining(String nombre) {
        List<AsentamientoDTO> dtoList = asentamientoRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(AsentamientoMapper::toDTO)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(dtoList);
    }
}
