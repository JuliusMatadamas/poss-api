package com.poss.repositories.admin;

import com.poss.entities.admin.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    @Query("SELECT m FROM Municipio m JOIN FETCH m.estado")
    @Override
    @NonNull
    List<Municipio> findAll();

    @Query("SELECT m FROM Municipio m JOIN FETCH m.estado WHERE lower(m.nombre) LIKE lower(concat('%', :nombre, '%'))")
    List<Municipio> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

    @Query("SELECT m FROM Municipio m JOIN FETCH m.estado e WHERE e.id = :id")
    List<Municipio> findByEstadoId(@Param("id") Integer id);
}
