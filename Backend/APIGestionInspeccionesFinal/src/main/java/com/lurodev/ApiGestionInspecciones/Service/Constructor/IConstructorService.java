package com.lurodev.ApiGestionInspecciones.Service.Constructor;

import com.lurodev.ApiGestionInspecciones.Entities.Constructor;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IConstructorService {
    List<Constructor> getAllConstructores();
    Optional<Constructor> getConstructorById(Long id);
    Optional<Constructor> getConstructorByNombres(String nombres);
    Optional<Constructor> getConstructorByApellidos(String apellidos);
    Optional<Constructor> getConstructorByNumeroDocumento(Long numeroDocumento);
    Optional<Constructor> getConstructorByMatriculaProfesional(String matriculaProfesional);
    Optional<Constructor> getConstructorByEmail(String email);
    AuthenticationResponse postConstructor(Constructor constructor);
    Constructor updateConstructor(Constructor constructor);
    void deleteConstructorById(Long id);

    ResponseEntity<AuthenticationResponse> authenticateConstructor(AuthCredentials constructorCredentials);
}
