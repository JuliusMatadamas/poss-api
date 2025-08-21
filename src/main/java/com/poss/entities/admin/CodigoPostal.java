package com.poss.entities.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "codigos_postales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodigoPostal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_postal", columnDefinition = "INT UNSIGNED")
    private Integer id;

	@SuppressWarnings("all")
    @Pattern(regexp = "^\\d{5}$", message = "El código postal debe contener exactamente 5 dígitos.")
    @Column(name = "codigo_postal", nullable = false, unique = true, length = 5)
    private String codigoPostal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio", nullable = false)
    private Municipio municipio;
}
