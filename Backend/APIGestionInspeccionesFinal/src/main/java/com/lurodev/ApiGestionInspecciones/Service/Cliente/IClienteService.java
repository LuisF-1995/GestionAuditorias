package com.lurodev.ApiGestionInspecciones.Service.Cliente;

import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> getAllClients();
    Optional<Cliente> getClientById(Long id);
    Optional<Cliente> getClientByEmail(String email);
    Optional<Cliente> getClientByName(String nombre);
    AuthenticationResponse postClient(Cliente cliente);
    Cliente updateClient(Cliente cliente);
    void deleteClient(Long id);
    ResponseEntity<AuthenticationResponse> authenticateClient(AuthCredentials clientCredentials);
}
