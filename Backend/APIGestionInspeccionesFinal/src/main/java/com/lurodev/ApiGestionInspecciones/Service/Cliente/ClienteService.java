package com.lurodev.ApiGestionInspecciones.Service.Cliente;

import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.ClienteRepository;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.JwtService;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class ClienteService implements IClienteService {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClientById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> getClientByEmail(String email) {
        return clienteRepository.findClienteByEmail(email);
    }

    @Override
    public Optional<Cliente> getClientByName(String nombre) {
        return clienteRepository.findClienteByNombre(nombre);
    }

    @Override
    public AuthenticationResponse postClient(Cliente cliente) {
        Optional<Cliente> existentClient = getClientByEmail(cliente.getEmail());
        AuthenticationResponse registerClientResponse = null;
        if(existentClient.isEmpty()){
            String encryptedPassword = passwordEncoder.encode(cliente.getPassword());
            cliente.setPassword(encryptedPassword);
            clienteRepository.save(cliente);
            registerClientResponse = new AuthenticationResponse(true, cliente, "", "Cliente registrado exitosamente");
        }
        else{
            registerClientResponse = new AuthenticationResponse(false, null, "", "El cliente ya existe");
        }

        return registerClientResponse;
    }

    @Override
    public Cliente updateClient(Cliente cliente) {
        if(cliente.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(cliente.getPassword());
            cliente.setPassword(encryptedPassword);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }


    @Override
    public ResponseEntity<AuthenticationResponse> authenticateClient(AuthCredentials clientCredentials) {
        String email = clientCredentials.getEmail();
        String password = clientCredentials.getPassword();

        if(getClientByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.CLIENTE, password)
            );

            var cliente = clienteRepository.findClienteByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

            Collection<GrantedAuthority> clientAuthority = Collections.singleton(new SimpleGrantedAuthority(cliente.getRol().name()));
            UserDetails clientDetails = new UserDetailsImpl(cliente.getEmail() + USER_ROLE_SEPARATOR + UserRoles.CLIENTE, cliente.getPassword(), clientAuthority);

            var jwtToken = jwtService.generateToken(clientDetails);
            cliente.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, cliente, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El cliente no está registrado")
            );
        }
    }

}
