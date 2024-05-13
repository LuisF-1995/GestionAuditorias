package com.lurodev.ApiGestionInspecciones.Service.AsesorComercial;

import com.lurodev.ApiGestionInspecciones.Entities.AsesorComercial;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAsesorComercial {
    List<AsesorComercial> getAllAsesoresComerciales();
    Optional<AsesorComercial> getAsesorComercialById(Long id);
    Optional<AsesorComercial> getAsesorComercialByEmail(String email);
    AsesorComercial postAsesorComercial(AsesorComercial asesorComercial);
    AsesorComercial updateAsesorComercial(AsesorComercial asesorComercial);
    void deleteAsesorComercialById(Long id);
    ResponseEntity<AuthenticationResponse> authenticateAsesorComercial(AuthCredentials inspectorCredentials);
}
