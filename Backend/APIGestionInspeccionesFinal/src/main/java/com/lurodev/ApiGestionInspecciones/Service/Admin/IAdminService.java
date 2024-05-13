package com.lurodev.ApiGestionInspecciones.Service.Admin;

import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(Short id);
    Optional<Admin> getAdminByEmail(String email);
    Optional<Admin> getAdminByDocument(Long documentNumber);
    RegistrationResponse registerAdmin(Admin admin) throws IOException;
    Admin updateAdmin(Admin admin);
    boolean deleteAdminById(Short id);
    ResponseEntity<AuthenticationResponse> authenticateAdmin(AuthCredentials adminCredentials);
}
