package com.poss.repositories.admin;

import com.poss.entities.admin.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    List<Municipio> findByNombreContainingIgnoreCase(String nombre);
    List<Municipio> findByEstadoId(Integer id);
}
