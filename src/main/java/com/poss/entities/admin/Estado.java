package com.poss.entities.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "estado", nullable = false, unique = true)
    private String nombre;
}
