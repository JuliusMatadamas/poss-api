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
public class MunicipioDTO {

    private Integer id;

    @NotBlank(message = "El nombre del municipio no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre del municipio debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ0-9,. -]+$", message = "El nombre del municipio solo puede contener letras, números, espacios, comas, puntos y guiones medios.")
    private String nombre;

    @NotNull(message = "El ID del estado no puede ser nulo")
    private Integer idEstado;
}
