package com.poss.mappers.admin;

import com.poss.dto.admin.EmpresaDTO;
import com.poss.entities.admin.Asentamiento;
import com.poss.entities.admin.Empresa;

public class EmpresaMapper {

    private EmpresaMapper() {
        // Clase de utilidad
    }

    public static EmpresaDTO toDTO(Empresa entity) {
        if (entity == null) {
            return null;
        }
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(entity.getId());
        dto.setNombreEmpresaLargo(entity.getNombreEmpresaLargo());
        dto.setNombreEmpresaCorto(entity.getNombreEmpresaCorto());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setRfc(entity.getRfc());
        if (entity.getAsentamiento() != null) {
            dto.setAsentamientoId(entity.getAsentamiento().getId());
        }
        dto.setDireccion(entity.getDireccion());
        return dto;
    }

    public static Empresa toEntity(EmpresaDTO dto) {
        if (dto == null) {
            return null;
        }
        Empresa entity = new Empresa();
        entity.setId(dto.getId());
        entity.setNombreEmpresaLargo(dto.getNombreEmpresaLargo());
        entity.setNombreEmpresaCorto(dto.getNombreEmpresaCorto());
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setRfc(dto.getRfc());
        if (dto.getAsentamientoId() != null) {
            Asentamiento asentamiento = new Asentamiento();
            asentamiento.setId(dto.getAsentamientoId());
            entity.setAsentamiento(asentamiento);
        }
        entity.setDireccion(dto.getDireccion());
        return entity;
    }
}
