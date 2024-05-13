package com.lurodev.ApiGestionInspecciones.Service.DirectorRegional;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorRegional;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IDirectorRegionalService {
    List<DirectorRegional> getAllDirectoresRegional();
    Optional<DirectorRegional> getDirectorRegionalById(Long id);
    Optional<DirectorRegional> getDirectorRegionalByNombres(String nombres);
    Optional<DirectorRegional> getDirectorRegionalByApellidos(String apellidos);
    Optional<DirectorRegional> getDirectorRegionalByNumeroDocumento(Long numeroDocumento);
    Optional<DirectorRegional> getDirectorRegionalByEmail(String email);

    DirectorRegional postDirectorRegional(DirectorRegional directorRegional);
    DirectorRegional updateDirectorRegional(DirectorRegional directorRegional);
    void deleteDirectorRegionalById(Long id);
    ResponseEntity<AuthenticationResponse> authenticateDirectorRegional(AuthCredentials dirRegionalCredentials);
}
