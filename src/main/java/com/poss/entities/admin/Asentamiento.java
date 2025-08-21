package com.poss.entities.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asentamientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asentamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asentamiento", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "asentamiento", nullable = false, length = 45)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_codigo_postal", referencedColumnName = "id_codigo_postal", nullable = false)
    private CodigoPostal codigoPostal;
}
