package com.lurodev.ApiGestionInspecciones.Service.DirectorRegional;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorRegional;
import com.lurodev.ApiGestionInspecciones.Entities.Regional;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;
import com.lurodev.ApiGestionInspecciones.Repository.DirectorRegionalRepository;
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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class DirectorRegionalService implements IDirectorRegionalService{
    private final DirectorRegionalRepository directorRegionalRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public DirectorRegionalService(DirectorRegionalRepository directorRegionalRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.directorRegionalRepository = directorRegionalRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<DirectorRegional> getAllDirectoresRegional() {
        return directorRegionalRepository.findAll();
    }

    @Override
    public Optional<DirectorRegional> getDirectorRegionalById(Long id) {
        return directorRegionalRepository.findById(id);
    }

    @Override
    public Optional<DirectorRegional> getDirectorRegionalByNombres(String nombres) {
        return directorRegionalRepository.findDirectorRegionalByNombres(nombres);
    }

    @Override
    public Optional<DirectorRegional> getDirectorRegionalByApellidos(String apellidos) {
        return directorRegionalRepository.findDirectorRegionalByApellidos(apellidos);
    }

    @Override
    public Optional<DirectorRegional> getDirectorRegionalByNumeroDocumento(Long numeroDocumento) {
        return directorRegionalRepository.findDirectorRegionalByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Optional<DirectorRegional> getDirectorRegionalByEmail(String email) {
        return directorRegionalRepository.findDirectorRegionalByEmail(email);
    }

    public Optional<DirectorRegional> getDirectorRegionalByRegional(Regional regional) {
        return directorRegionalRepository.findDirectorRegionalByRegional(regional);
    }

    @Override
    public DirectorRegional postDirectorRegional(DirectorRegional directorRegional) {
        String encryptedPassword = passwordEncoder.encode(directorRegional.getPassword());
        directorRegional.setPassword(encryptedPassword);
        return directorRegionalRepository.save(directorRegional);
    }

    @Override
    public DirectorRegional updateDirectorRegional(DirectorRegional directorRegional) {

        DirectorRegional directorRegionalUpdate = null;
        Optional<DirectorRegional> dirRegionalOpt = Optional.empty();

        if (directorRegional.getId() != null){
            dirRegionalOpt = directorRegionalRepository.findById(directorRegional.getId());
        }
        else if (directorRegional.getNumeroDocumento() != null) {
            dirRegionalOpt = directorRegionalRepository.findDirectorRegionalByNumeroDocumento(directorRegional.getNumeroDocumento());
        }
        else if (directorRegional.getEmail() != null) {
            dirRegionalOpt = directorRegionalRepository.findDirectorRegionalByEmail(directorRegional.getEmail());
        }

        if(dirRegionalOpt.isPresent()){
            directorRegionalUpdate = dirRegionalOpt.get();
            Field[] dirRegionalFields = directorRegional.getClass().getDeclaredFields();

            for(Field field: dirRegionalFields){
                try {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(directorRegional);

                    if(value != null){
                        switch (fieldName) {
                            case "nombres"-> directorRegionalUpdate.setNombres(value.toString());
                            case "apellidos"-> directorRegionalUpdate.setApellidos(value.toString());
                            case "email"-> directorRegionalUpdate.setEmail(value.toString());
                            case "password"-> {
                                String encryptedPassword = passwordEncoder.encode(value.toString());
                                directorRegionalUpdate.setPassword(encryptedPassword);
                            }
                            case "telefono"-> directorRegionalUpdate.setTelefono(value.toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            Optional<DirectorRegional> dirRegionalOptByRegional = this.getDirectorRegionalByRegional(directorRegional.getRegional());
            if(dirRegionalOptByRegional.isPresent()){
                DirectorRegional dirRegionalExistent = dirRegionalOptByRegional.get();
                dirRegionalExistent.setRegional(null);
                directorRegionalRepository.save(dirRegionalExistent);
            }
            directorRegionalUpdate.setRegional(directorRegional.getRegional());
            directorRegionalUpdate = directorRegionalRepository.save(directorRegionalUpdate);
        }

        return directorRegionalUpdate;
    }

    @Override
    public void deleteDirectorRegionalById(Long id) {
        directorRegionalRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateDirectorRegional(AuthCredentials dirRegionalCredentials) {
        String email = dirRegionalCredentials.getEmail();
        String password = dirRegionalCredentials.getPassword();

        if(getDirectorRegionalByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.DIRECTOR_REGIONAL, password)
            );

            var directorRegional = directorRegionalRepository.findDirectorRegionalByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Director de regional no encontrado"));

            Collection<GrantedAuthority> dirRegionalAuthority = Collections.singleton(new SimpleGrantedAuthority(directorRegional.getRol().name()));
            UserDetails dirRegionalDetails = new UserDetailsImpl(directorRegional.getEmail() + USER_ROLE_SEPARATOR + UserRoles.DIRECTOR_REGIONAL, directorRegional.getPassword(), dirRegionalAuthority);

            var jwtToken = jwtService.generateToken(dirRegionalDetails);
            directorRegional.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, directorRegional, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El director de regional no está registrado")
            );
        }
    }
}
