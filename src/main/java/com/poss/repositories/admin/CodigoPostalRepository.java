package com.poss.repositories.admin;

import com.poss.entities.admin.CodigoPostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, Integer> {
    List<CodigoPostal> findByMunicipioId(Integer id);
    List<CodigoPostal> findByCodigoPostalContaining(String codigoPostal);
}
