package com.poss.dto.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodigoPostalDTO {

	private Integer id;

    @Pattern(regexp = "^\\d{5}$", message = "El código postal debe contener exactamente 5 dígitos.")
    private String codigoPostal;

    @NotNull(message = "El ID del municipio no puede ser nulo.")
    private Integer municipioId;
}
