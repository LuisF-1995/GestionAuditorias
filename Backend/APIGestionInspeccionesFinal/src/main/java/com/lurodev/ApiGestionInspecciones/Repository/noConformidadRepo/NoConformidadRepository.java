package com.lurodev.ApiGestionInspecciones.Repository.noConformidadRepo;

import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.NoConformidad;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface NoConformidadRepository extends JpaRepository<NoConformidad, Long> {
    Optional<Set<NoConformidad>> findNoConformidadByDescripcionNoConformidad(String descripcionNoConformidad);
    Optional<Set<NoConformidad>> findNoConformidadByArticuloNormativo(String articuloNormativo);
    List<NoConformidad> findTop10ByOrderByNumeroOcurrenciasDesc(PageRequest pageRequest);
}
