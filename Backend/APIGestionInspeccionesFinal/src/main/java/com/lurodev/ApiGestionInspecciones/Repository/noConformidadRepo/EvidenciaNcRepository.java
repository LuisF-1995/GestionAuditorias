package com.lurodev.ApiGestionInspecciones.Repository.noConformidadRepo;

import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.EvidenciaNC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenciaNcRepository extends JpaRepository<EvidenciaNC, Long> {
}
