package com.poss.mappers.admin;

import com.poss.dto.admin.GeneroDTO;
import com.poss.entities.admin.Genero;

public class GeneroMapper {

    private GeneroMapper() {
        // Clase de utilidad, no instanciable
    }

    public static GeneroDTO toDto(Genero genero) {
        if (genero == null) {
            return null;
        }
        return new GeneroDTO(
                genero.getId(),
                genero.getNombre()
        );
    }

    public static Genero toEntity(GeneroDTO generoDTO) {
        if (generoDTO == null) {
            return null;
        }
        return new Genero(
                generoDTO.getId(),
                generoDTO.getNombre()
        );
    }
}

