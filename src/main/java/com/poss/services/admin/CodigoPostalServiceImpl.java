package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.CodigoPostalDTO;
import com.poss.entities.admin.CodigoPostal;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.CodigoPostalMapper;
import com.poss.repositories.admin.CodigoPostalRepository;
import com.poss.repositories.admin.MunicipioRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodigoPostalServiceImpl implements CodigoPostalService {

    private final CodigoPostalRepository codigoPostalRepository;
    private final MunicipioRepository municipioRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<CodigoPostalDTO> codigosPostales = codigoPostalRepository.findAll().stream()
                .map(CodigoPostalMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(codigosPostales);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        CodigoPostal codigoPostal = codigoPostalRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El código postal con ID " + id + " no fue encontrado."));
        return ApiResponseUtil.buildSuccessResponse(CodigoPostalMapper.toDto(codigoPostal));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> save(CodigoPostalDTO codigoPostalDTO) {
        if (!municipioRepository.existsById(codigoPostalDTO.getMunicipioId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El municipio con ID " + codigoPostalDTO.getMunicipioId() + " no existe.");
        }
        CodigoPostal codigoPostal = CodigoPostalMapper.toEntity(codigoPostalDTO);
        CodigoPostal savedCodigoPostal = codigoPostalRepository.save(codigoPostal);
        return ApiResponseUtil.buildCreatedResponse(CodigoPostalMapper.toDto(savedCodigoPostal));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, CodigoPostalDTO codigoPostalDTO) {
        CodigoPostal codigoPostalExistente = codigoPostalRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El código postal con ID " + id + " no fue encontrado para actualizar."));

        com.poss.entities.admin.Municipio municipio = municipioRepository.findById(codigoPostalDTO.getMunicipioId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "El municipio con ID " + codigoPostalDTO.getMunicipioId() + " no existe."));

        codigoPostalExistente.setCodigoPostal(codigoPostalDTO.getCodigoPostal());
        codigoPostalExistente.setMunicipio(municipio);
        CodigoPostal updatedCodigoPostal = codigoPostalRepository.save(codigoPostalExistente);
        return ApiResponseUtil.buildSuccessResponse(CodigoPostalMapper.toDto(updatedCodigoPostal));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!codigoPostalRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El código postal con ID " + id + " no fue encontrado para eliminar.");
        }
        codigoPostalRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Código postal con ID " + id + " eliminado correctamente.");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByMunicipioId(Integer id) {
        if (!municipioRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El municipio con ID " + id + " no existe.");
        }
        List<CodigoPostalDTO> codigosPostales = codigoPostalRepository.findByMunicipioId(id).stream()
                .map(CodigoPostalMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(codigosPostales);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByCodigoPostalContaining(String codigoPostal) {
        List<CodigoPostalDTO> codigosPostales = codigoPostalRepository.findByCodigoPostalContaining(codigoPostal).stream()
                .map(CodigoPostalMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(codigosPostales);
    }
}
