package com.poss.entities.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer id;

    @Column(name = "nombre_empresa_largo", nullable = false, unique = true, length = 100)
    private String nombreEmpresaLargo;

    @Column(name = "nombre_empresa_corto", nullable = false, length = 10)
    private String nombreEmpresaCorto;

    @Column(name = "razon_social", nullable = false, unique = true, length = 100)
    private String razonSocial;

    @Column(name = "rfc", nullable = false, unique = true, length = 45)
    private String rfc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_asentamiento", nullable = false)
    private Asentamiento asentamiento;

    @Column(name = "direccion", length = 255)
    private String direccion;
}
