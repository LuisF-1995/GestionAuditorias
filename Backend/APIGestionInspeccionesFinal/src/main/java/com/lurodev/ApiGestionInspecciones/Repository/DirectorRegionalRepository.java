package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorRegional;
import com.lurodev.ApiGestionInspecciones.Entities.Regional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRegionalRepository extends JpaRepository<DirectorRegional, Long> {
    Optional<DirectorRegional> findDirectorRegionalByNombres(String nombres);
    Optional<DirectorRegional> findDirectorRegionalByApellidos(String apellidos);
    Optional<DirectorRegional> findDirectorRegionalByNumeroDocumento(Long numeroDocumento);
    Optional<DirectorRegional> findDirectorRegionalByEmail(String email);
    Optional<DirectorRegional> findDirectorRegionalByRegional(Regional regional);
}
