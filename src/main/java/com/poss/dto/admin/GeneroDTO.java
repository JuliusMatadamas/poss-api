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
public class GeneroDTO {
    private Integer id;

    @NotBlank(message = "El nombre del género no puede estar vacío.")
    @Size(min = 1, max = 1, message = "El nombre del género debe ser un solo carácter.")
    @Pattern(regexp = "^[a-zA-Z]$", message = "El nombre del género debe ser una letra del abecedario.")
    private String nombre;

    public void setNombre(String nombre) {
        if (nombre != null) {
            this.nombre = nombre.toUpperCase();
        } else {
            this.nombre = null;
        }
    }
}

