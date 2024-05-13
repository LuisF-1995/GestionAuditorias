package com.lurodev.ApiGestionInspecciones.Service.Constructor;

import com.lurodev.ApiGestionInspecciones.Entities.Constructor;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.IConstructorRepository;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.JwtService;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class ConstructorService implements IConstructorService{
    private final IConstructorRepository constructorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ConstructorService(IConstructorRepository constructorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.constructorRepository = constructorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<Constructor> getAllConstructores() {
        return constructorRepository.findAll();
    }

    @Override
    public Optional<Constructor> getConstructorById(Long id) {
        return constructorRepository.findById(id);
    }

    @Override
    public Optional<Constructor> getConstructorByNombres(String nombres) {
        return constructorRepository.findConstructorByNombres(nombres);
    }

    @Override
    public Optional<Constructor> getConstructorByApellidos(String apellidos) {
        return constructorRepository.findConstructorByApellidos(apellidos);
    }

    @Override
    public Optional<Constructor> getConstructorByNumeroDocumento(Long numeroDocumento) {
        return constructorRepository.findConstructorByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Optional<Constructor> getConstructorByMatriculaProfesional(String matriculaProfesional) {
        return constructorRepository.findConstructorByMatriculaProfesional(matriculaProfesional);
    }

    @Override
    public Optional<Constructor> getConstructorByEmail(String email) {
        return constructorRepository.findConstructorByEmail(email);
    }

    @Override
    public AuthenticationResponse postConstructor(Constructor constructor) {
        if(getConstructorByEmail(constructor.getEmail()).isPresent()){
            return new AuthenticationResponse(false, null, null, "El usuario ya existe");
        }

        String encryptedPasswordConstructor = passwordEncoder.encode(constructor.getPassword());
        constructor.setPassword(encryptedPasswordConstructor);

        constructorRepository.save(constructor);

        Collection<GrantedAuthority> constructorAuthority = Collections.singleton(new SimpleGrantedAuthority(constructor.getRol().name()));
        UserDetails constructorDetails = new UserDetailsImpl(constructor.getEmail(), constructor.getPassword(), constructorAuthority);

        var jwtToken = jwtService.generateToken(constructorDetails);
        return new AuthenticationResponse(true, constructor, jwtToken);
    }

    @Override
    public Constructor updateConstructor(Constructor constructor) {
        if(constructor.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(constructor.getPassword());
            constructor.setPassword(encryptedPassword);
        }
        return constructorRepository.save(constructor);
    }

    @Override
    public void deleteConstructorById(Long id) {
        constructorRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateConstructor(AuthCredentials constructorCredentials) {
        String email = constructorCredentials.getEmail();
        String password = constructorCredentials.getPassword();

        if(getConstructorByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.CONSTRUCTOR, password)
            );

            var constructor = constructorRepository.findConstructorByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Constructor no encontrado"));

            Collection<GrantedAuthority> constructorAuthority = Collections.singleton(new SimpleGrantedAuthority(constructor.getRol().name()));
            UserDetails constructorDetails = new UserDetailsImpl(constructor.getEmail() + USER_ROLE_SEPARATOR + UserRoles.CONSTRUCTOR, constructor.getPassword(), constructorAuthority);

            var jwtToken = jwtService.generateToken(constructorDetails);
            constructor.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, constructor, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El constructor no está registrado")
            );
        }
    }
}
