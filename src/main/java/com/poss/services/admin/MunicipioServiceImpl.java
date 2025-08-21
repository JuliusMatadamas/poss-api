package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.MunicipioDTO;
import com.poss.entities.admin.Municipio;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.MunicipioMapper;
import com.poss.repositories.admin.EstadoRepository;
import com.poss.repositories.admin.MunicipioRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;
    private final EstadoRepository estadoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<MunicipioDTO> municipios = municipioRepository.findAll().stream()
                .map(MunicipioMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(municipios);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El municipio con ID " + id + " no fue encontrado."));
        return ApiResponseUtil.buildSuccessResponse(MunicipioMapper.toDto(municipio));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> save(MunicipioDTO municipioDTO) {
        if (!estadoRepository.existsById(municipioDTO.getIdEstado())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El estado con ID " + municipioDTO.getIdEstado() + " no existe.");
        }
        Municipio municipio = MunicipioMapper.toEntity(municipioDTO);
        Municipio savedMunicipio = municipioRepository.save(municipio);
        return ApiResponseUtil.buildCreatedResponse(MunicipioMapper.toDto(savedMunicipio));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, MunicipioDTO municipioDTO) {
        Municipio municipioExistente = municipioRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "El municipio con ID " + id + " no fue encontrado para actualizar."));

        // Se recupera la entidad Estado completa
        com.poss.entities.admin.Estado estado = estadoRepository.findById(municipioDTO.getIdEstado())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "El estado con ID " + municipioDTO.getIdEstado() + " no existe."));

        municipioExistente.setNombre(municipioDTO.getNombre());
        municipioExistente.setEstado(estado); // Se asocia la entidad Estado recuperada
        Municipio updatedMunicipio = municipioRepository.save(municipioExistente);
        return ApiResponseUtil.buildSuccessResponse(MunicipioMapper.toDto(updatedMunicipio));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!municipioRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El municipio con ID " + id + " no fue encontrado para eliminar.");
        }
        municipioRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Municipio con ID " + id + " eliminado correctamente.");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByNombreContainingIgnoreCase(String nombre) {
        List<MunicipioDTO> municipios = municipioRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(MunicipioMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(municipios);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByEstadoId(Integer id) {
        if (!estadoRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El estado con ID " + id + " no existe.");
        }
        List<MunicipioDTO> municipios = municipioRepository.findByEstadoId(id).stream()
                .map(MunicipioMapper::toDto)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(municipios);
    }
}
