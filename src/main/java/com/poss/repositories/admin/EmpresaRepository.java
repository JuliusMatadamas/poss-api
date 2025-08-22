package com.poss.repositories.admin;

import com.poss.entities.admin.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query("SELECT e FROM Empresa e JOIN FETCH e.asentamiento a JOIN FETCH a.codigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado")
    @Override
    @NonNull
    List<Empresa> findAll();

    @Query("SELECT e FROM Empresa e JOIN FETCH e.asentamiento a JOIN FETCH a.codigoPostal cp JOIN FETCH cp.municipio m JOIN FETCH m.estado WHERE lower(e.nombreEmpresaLargo) LIKE lower(concat('%', :nombre, '%'))")
    List<Empresa> findByNombreEmpresaLargoContainingIgnoreCase(@Param("nombre") String nombre);
}
