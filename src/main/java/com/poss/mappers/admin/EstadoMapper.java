package com.poss.mappers.admin;

import com.poss.dto.admin.EstadoDTO;
import com.poss.entities.admin.Estado;

public class EstadoMapper {

    private EstadoMapper() {
        // Clase de utilidad, no instanciable
    }

    public static EstadoDTO toDto(Estado estado) {
        if (estado == null) {
            return null;
        }
        return new EstadoDTO(
                estado.getId(),
                estado.getNombre()
        );
    }

    public static Estado toEntity(EstadoDTO estadoDTO) {
        if (estadoDTO == null) {
            return null;
        }
        return new Estado(
                estadoDTO.getId(),
                estadoDTO.getNombre()
        );
    }
}
