package com.lurodev.ApiGestionInspecciones.Service.DirectorTecnico;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorTecnico;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.DirectorTecnicoRepository;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class DirectorTecnicoService implements IDirectorTecnicoService{
    private final DirectorTecnicoRepository directorTecnicoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public DirectorTecnicoService(DirectorTecnicoRepository directorTecnicoRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.directorTecnicoRepository = directorTecnicoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<DirectorTecnico> getAllDirectoresTecnicos() {
        return directorTecnicoRepository.findAll();
    }

    @Override
    public Optional<DirectorTecnico> getDirectorTecnicoById(Short id) {
        return directorTecnicoRepository.findById(id);
    }

    @Override
    public Optional<DirectorTecnico> getDirectorTecnicoByNumeroDocumento(Long numeroDocumento) {
        return directorTecnicoRepository.findDirectorTecnicoByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Optional<DirectorTecnico> getDirectorTecnicoByMatriculaProfesional(String matriculaProfesional) {
        return directorTecnicoRepository.findDirectorTecnicoByMatriculaProfesional(matriculaProfesional);
    }

    @Override
    public Optional<DirectorTecnico> getDirectorTecnicoByEmail(String email) {
        return directorTecnicoRepository.findDirectorTecnicoByEmail(email);
    }

    @Override
    public DirectorTecnico postDirectorTecnico(DirectorTecnico directorTecnico) {
        String encryptedPassword = passwordEncoder.encode(directorTecnico.getPassword());
        directorTecnico.setPassword(encryptedPassword);
        return directorTecnicoRepository.save(directorTecnico);
    }

    @Override
    public DirectorTecnico updateDirectorTecnico(DirectorTecnico directorTecnico) {
        if(directorTecnico.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(directorTecnico.getPassword());
            directorTecnico.setPassword(encryptedPassword);
        }
        return directorTecnicoRepository.save(directorTecnico);
    }

    @Override
    public void deleteDirectorTecnico(Short id) {
        directorTecnicoRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateDirectorTecnico(AuthCredentials dirTecnicoCredentials) {
        String email = dirTecnicoCredentials.getEmail();
        String password = dirTecnicoCredentials.getPassword();

        if(getDirectorTecnicoByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.DIRECTOR_TECNICO, password)
            );

            var directorTecnico = directorTecnicoRepository.findDirectorTecnicoByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Director técnico no encontrado"));

            Collection<GrantedAuthority> dirTecnicoAuthority = Collections.singleton(new SimpleGrantedAuthority(directorTecnico.getRol().name()));
            UserDetails dirTecnicoDetails = new UserDetailsImpl(directorTecnico.getEmail() + USER_ROLE_SEPARATOR + UserRoles.DIRECTOR_TECNICO, directorTecnico.getPassword(), dirTecnicoAuthority);

            var jwtToken = jwtService.generateToken(dirTecnicoDetails);
            directorTecnico.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, directorTecnico, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El director técnico no está registrado")
            );
        }
    }
}
