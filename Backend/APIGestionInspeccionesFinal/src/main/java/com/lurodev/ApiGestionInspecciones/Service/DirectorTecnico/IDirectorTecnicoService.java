package com.lurodev.ApiGestionInspecciones.Service.DirectorTecnico;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorTecnico;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IDirectorTecnicoService {
    List<DirectorTecnico> getAllDirectoresTecnicos();
    Optional<DirectorTecnico> getDirectorTecnicoById(Short id);
    Optional<DirectorTecnico> getDirectorTecnicoByNumeroDocumento(Long numeroDocumento);
    Optional<DirectorTecnico> getDirectorTecnicoByMatriculaProfesional(String matriculaProfesional);
    Optional<DirectorTecnico> getDirectorTecnicoByEmail(String email);
    DirectorTecnico postDirectorTecnico(DirectorTecnico directorTecnico);
    DirectorTecnico updateDirectorTecnico(DirectorTecnico directorTecnico);
    void deleteDirectorTecnico(Short id);
    ResponseEntity<AuthenticationResponse> authenticateDirectorTecnico(AuthCredentials dirTecnicoCredentials);
}
