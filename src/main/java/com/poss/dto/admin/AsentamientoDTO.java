package com.poss.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsentamientoDTO {

    private Integer id;

    @NotBlank(message = "El nombre del asentamiento no puede estar vacío.")
    @Size(max = 45, message = "El nombre del asentamiento no puede exceder los 45 caracteres.")
        @Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜ .,'()\\[\\]_/#°-]*$", message = "El nombre contiene caracteres no permitidos.")
    private String nombre;

    @NotNull(message = "El ID del código postal no puede ser nulo.")
    private Integer codigoPostalId;
}
