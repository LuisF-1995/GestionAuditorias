package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorTecnicoRepository extends JpaRepository<DirectorTecnico, Short> {
    Optional<DirectorTecnico> findDirectorTecnicoByNumeroDocumento(Long numeroDocumento);
    Optional<DirectorTecnico> findDirectorTecnicoByMatriculaProfesional(String matriculaProfesional);
    Optional<DirectorTecnico> findDirectorTecnicoByEmail(String email);
}
