package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.AsesorComercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsesorComercialRepository extends JpaRepository<AsesorComercial, Long> {
    List<AsesorComercial> findAllByCompanyId(String companyId);
    Optional<AsesorComercial> findAsesorComercialByEmail(String email);
}
