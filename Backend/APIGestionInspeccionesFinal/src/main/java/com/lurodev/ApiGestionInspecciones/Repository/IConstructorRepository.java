package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConstructorRepository extends JpaRepository<Constructor, Long> {
    Optional<Constructor> findConstructorByNombres(String nombres);
    Optional<Constructor> findConstructorByApellidos(String apellidos);
    Optional<Constructor> findConstructorByNumeroDocumento(Long numeroDocumento);
    Optional<Constructor> findConstructorByMatriculaProfesional(String matriculaProfesional);
    Optional<Constructor> findConstructorByEmail(String email);

}
