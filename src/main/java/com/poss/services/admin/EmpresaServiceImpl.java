package com.poss.services.admin;

import com.poss.dto.ApiResponseDTO;
import com.poss.dto.admin.EmpresaDTO;
import com.poss.entities.admin.Asentamiento;
import com.poss.entities.admin.Empresa;
import com.poss.exceptions.ApiException;
import com.poss.mappers.admin.EmpresaMapper;
import com.poss.repositories.admin.AsentamientoRepository;
import com.poss.repositories.admin.EmpresaRepository;
import com.poss.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final AsentamientoRepository asentamientoRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> findById(Integer id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Empresa no encontrada con ID: " + id));
        return ApiResponseUtil.buildSuccessResponse(EmpresaMapper.toDTO(empresa));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findAll() {
        List<EmpresaDTO> dtoList = empresaRepository.findAll().stream()
                .map(EmpresaMapper::toDTO)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(dtoList);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> create(EmpresaDTO empresaDTO) {
        if (!asentamientoRepository.existsById(empresaDTO.getAsentamientoId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El asentamiento con ID " + empresaDTO.getAsentamientoId() + " no existe.");
        }
        Empresa empresa = EmpresaMapper.toEntity(empresaDTO);
        Empresa savedEmpresa = empresaRepository.save(empresa);
        return ApiResponseUtil.buildCreatedResponse(EmpresaMapper.toDTO(savedEmpresa));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> update(Integer id, EmpresaDTO empresaDTO) {
        Empresa existingEmpresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Empresa no encontrada con ID: " + id));

        Asentamiento asentamiento = asentamientoRepository.findById(empresaDTO.getAsentamientoId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "El asentamiento con ID " + empresaDTO.getAsentamientoId() + " no existe."));

        existingEmpresa.setNombreEmpresaLargo(empresaDTO.getNombreEmpresaLargo());
        existingEmpresa.setNombreEmpresaCorto(empresaDTO.getNombreEmpresaCorto());
        existingEmpresa.setRazonSocial(empresaDTO.getRazonSocial());
        existingEmpresa.setRfc(empresaDTO.getRfc());
        existingEmpresa.setAsentamiento(asentamiento);
        existingEmpresa.setDireccion(empresaDTO.getDireccion());

        Empresa updatedEmpresa = empresaRepository.save(existingEmpresa);
        return ApiResponseUtil.buildSuccessResponse(EmpresaMapper.toDTO(updatedEmpresa));
    }

    @Override
    public ResponseEntity<ApiResponseDTO> delete(Integer id) {
        if (!empresaRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Empresa no encontrada con ID: " + id);
        }
        empresaRepository.deleteById(id);
        return ApiResponseUtil.buildSuccessResponse("Empresa eliminada con éxito.");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> findByNombreEmpresaLargoContaining(String nombre) {
        List<EmpresaDTO> dtoList = empresaRepository.findByNombreEmpresaLargoContainingIgnoreCase(nombre).stream()
                .map(EmpresaMapper::toDTO)
                .toList();
        return ApiResponseUtil.buildSuccessResponse(dtoList);
    }
}