package com.poss.mappers.admin;

import com.poss.dto.admin.CodigoPostalDTO;
import com.poss.entities.admin.CodigoPostal;
import com.poss.entities.admin.Municipio;

public class CodigoPostalMapper {

    private CodigoPostalMapper() {
        // Clase de utilidad, no instanciable
    }

    public static CodigoPostalDTO toDto(CodigoPostal codigoPostal) {
        if (codigoPostal == null) {
            return null;
        }
        return new CodigoPostalDTO(
                codigoPostal.getId(),
                codigoPostal.getCodigoPostal(),
                codigoPostal.getMunicipio() != null ? codigoPostal.getMunicipio().getId() : null
        );
    }

    public static CodigoPostal toEntity(CodigoPostalDTO codigoPostalDTO) {
        if (codigoPostalDTO == null) {
            return null;
        }
        CodigoPostal codigoPostal = new CodigoPostal();
        codigoPostal.setId(codigoPostalDTO.getId());
        codigoPostal.setCodigoPostal(codigoPostalDTO.getCodigoPostal());

        if (codigoPostalDTO.getMunicipioId() != null) {
            Municipio municipio = new Municipio();
            municipio.setId(codigoPostalDTO.getMunicipioId());
            codigoPostal.setMunicipio(municipio);
        }
        return codigoPostal;
    }
}
