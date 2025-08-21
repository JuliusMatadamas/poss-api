package com.poss.repositories.admin;

import com.poss.entities.admin.CodigoPostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Integer> {

    @Query("SELECT cp FROM CodigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado")
    @Override
    @NonNull
    List<CodigoPostal> findAll();

    @Query("SELECT cp FROM CodigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado WHERE m.id = :id")
    List<CodigoPostal> findByMunicipioId(@Param("id") Integer id);

    @Query("SELECT cp FROM CodigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado WHERE cp.codigoPostal LIKE %:codigoPostal%")
    List<CodigoPostal> findByCodigoPostalContaining(@Param("codigoPostal") String codigoPostal);
}
