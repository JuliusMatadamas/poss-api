package com.poss.mappers.admin;

import com.poss.dto.admin.AsentamientoDTO;
import com.poss.entities.admin.Asentamiento;
import com.poss.entities.admin.CodigoPostal;
public class AsentamientoMapper {

    private AsentamientoMapper() {
        // Clase de utilidad
    }

    public static Asentamiento toEntity(AsentamientoDTO dto) {
        if (dto == null) {
            return null;
        }

        Asentamiento entity = new Asentamiento();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());

        if (dto.getCodigoPostalId() != null) {
            CodigoPostal codigoPostal = new CodigoPostal();
            codigoPostal.setId(dto.getCodigoPostalId());
            entity.setCodigoPostal(codigoPostal);
        }

        return entity;
    }

    public static AsentamientoDTO toDTO(Asentamiento entity) {
        if (entity == null) {
            return null;
        }

        AsentamientoDTO dto = new AsentamientoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        if (entity.getCodigoPostal() != null) {
            dto.setCodigoPostalId(entity.getCodigoPostal().getId());
        }

        return dto;
    }
}
