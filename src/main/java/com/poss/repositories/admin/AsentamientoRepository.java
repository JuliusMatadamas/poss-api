package com.poss.repositories.admin;

import com.poss.entities.admin.Asentamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsentamientoRepository extends JpaRepository<Asentamiento, Integer> {

    @Query("SELECT a FROM Asentamiento a JOIN FETCH a.codigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado")
    @Override
    @NonNull
    List<Asentamiento> findAll();

    @Query("SELECT a FROM Asentamiento a JOIN FETCH a.codigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado WHERE cp.id = :id")
    List<Asentamiento> findByCodigoPostalId(@Param("id") Integer id);

    @Query("SELECT a FROM Asentamiento a JOIN FETCH a.codigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado WHERE lower(a.nombre) LIKE lower(concat('%', :nombre, '%'))")
    List<Asentamiento> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}
