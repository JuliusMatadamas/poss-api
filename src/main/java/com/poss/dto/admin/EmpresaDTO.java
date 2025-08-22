package com.poss.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

    private Integer id;

    @NotBlank(message = "El nombre largo de la empresa no puede estar vacío.")
    @Size(max = 100, message = "El nombre largo de la empresa no puede exceder los 100 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜ .,'()\\[\\]_/#°-]*$", message = "El nombre largo contiene caracteres no permitidos.")
    private String nombreEmpresaLargo;

    @NotBlank(message = "El nombre corto de la empresa no puede estar vacío.")
    @Size(max = 10, message = "El nombre corto de la empresa no puede exceder los 10 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜ .,'()\\[\\]_/#°-]*$", message = "El nombre corto contiene caracteres no permitidos.")
    private String nombreEmpresaCorto;

    @NotBlank(message = "La razón social no puede estar vacía.")
    @Size(max = 100, message = "La razón social no puede exceder los 100 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜ .,'()\\[\\]_/#°-]*$", message = "La razón social contiene caracteres no permitidos.")
    private String razonSocial;

    @NotBlank(message = "El RFC no puede estar vacío.")
    @Pattern(regexp = "^[A-Z&Ñ]{3,4}\\d{6}[A-Z\\d]{3}$", message = "El formato del RFC no es válido.")
    private String rfc;

    @NotNull(message = "El ID del asentamiento no puede ser nulo.")
    private Integer asentamientoId;

    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚüÜ .,'()\\[\\]_/#°-]*$", message = "La dirección contiene caracteres no permitidos.")
    private String direccion;
}
