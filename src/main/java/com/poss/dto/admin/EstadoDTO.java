package com.poss.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
    private Integer id;

    @NotBlank(message = "El nombre del estado no puede estar vacío")
    @Size(min = 2, max = 45, message = "El nombre del estado debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ,. ]+$", message = "El nombre del estado solo puede contener letras, espacios, comas y puntos.")
    private String nombre;
}
