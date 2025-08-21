package com.poss.entities.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "generos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "genero", nullable = false, unique = true, length = 1)
    private String nombre;
}

