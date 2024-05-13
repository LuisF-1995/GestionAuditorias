package com.lurodev.ApiGestionInspecciones.Service.Inspector;

import com.lurodev.ApiGestionInspecciones.Entities.Inspector;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InterfaceInspectorService {
    List<Inspector> getAllInspectores();
    Optional<Inspector> getInspectorById(Long id);
    Optional<Inspector> getInspectorByNumeroDocumento(Long numeroDocumento);
    Optional<Inspector> getInspectorByMatriculaProfesional(String matriculaProfesional);
    Optional<Inspector> getInspectorByApellidos(String apellidos);
    Optional<Inspector> getInspectorByNombres(String nombres);
    Optional<Inspector> getInspectorByEmail(String email);

    Inspector postInspector(Inspector inspector);
    Inspector updateInspector(Inspector inspector);
    void deleteInspector(Long id);

    ResponseEntity<AuthenticationResponse> authenticateInspector(AuthCredentials asesorComercialCredencials);

}
