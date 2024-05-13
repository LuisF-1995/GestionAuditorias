package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {
    Optional<Inspector> findInspectorByNumeroDocumento(Long numeroDocumento);
    Optional<Inspector> findInspectorByMatriculaProfesional(String matriculaProfesional);
    Optional<Inspector> findInspectorByNombres(String nombres);
    Optional<Inspector> findInspectorByApellidos(String apellidos);
    Optional<Inspector> findInspectorByEmail(String email);
}
