package com.poss.mappers.admin;

import com.poss.dto.admin.MunicipioDTO;
import com.poss.entities.admin.Estado;
import com.poss.entities.admin.Municipio;

public class MunicipioMapper {

    private MunicipioMapper() {
        // Clase de utilidad, no instanciable
    }

    public static MunicipioDTO toDto(Municipio municipio) {
        if (municipio == null) {
            return null;
        }
        return new MunicipioDTO(
                municipio.getId(),
                municipio.getNombre(),
                municipio.getEstado() != null ? municipio.getEstado().getId() : null
        );
    }

    public static Municipio toEntity(MunicipioDTO municipioDTO) {
        if (municipioDTO == null) {
            return null;
        }
        Municipio municipio = new Municipio();
        municipio.setId(municipioDTO.getId());
        municipio.setNombre(municipioDTO.getNombre());

        if (municipioDTO.getIdEstado() != null) {
            Estado estado = new Estado();
            estado.setId(municipioDTO.getIdEstado());
            municipio.setEstado(estado);
        }
        return municipio;
    }
}
