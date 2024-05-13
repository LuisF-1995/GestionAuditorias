package com.lurodev.ApiGestionInspecciones.Service.Inspector;

import com.lurodev.ApiGestionInspecciones.Entities.Inspector;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.InspectorRepository;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.JwtService;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class InspectorService implements InterfaceInspectorService{
    private InspectorRepository inspectorRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public InspectorService(InspectorRepository inspectorRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.inspectorRepository = inspectorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<Inspector> getAllInspectores() {
        List<Inspector> listadoInspectores = inspectorRepository.findAll();
        return listadoInspectores;
    }

    @Override
    public Optional<Inspector> getInspectorById(Long id) {
        return inspectorRepository.findById(id);
    }

    @Override
    public Optional<Inspector> getInspectorByNumeroDocumento(Long numeroDocumento) {
        return inspectorRepository.findInspectorByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Optional<Inspector> getInspectorByMatriculaProfesional(String matriculaProfesional) {
        return inspectorRepository.findInspectorByMatriculaProfesional(matriculaProfesional);
    }

    @Override
    public Optional<Inspector> getInspectorByApellidos(String apellidos) {
        return inspectorRepository.findInspectorByApellidos(apellidos);
    }

    @Override
    public Optional<Inspector> getInspectorByNombres(String nombres) {
        return inspectorRepository.findInspectorByNombres(nombres);
    }

    @Override
    public Optional<Inspector> getInspectorByEmail(String email) {
        return inspectorRepository.findInspectorByEmail(email);
    }

    @Override
    public Inspector postInspector(Inspector inspector) {
        String encryptedPassword = passwordEncoder.encode(inspector.getPassword());
        inspector.setPassword(encryptedPassword);
        return inspectorRepository.save(inspector);
    }

    @Override
    public Inspector updateInspector(Inspector inspector) {
        if(inspector.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(inspector.getPassword());
            inspector.setPassword(encryptedPassword);
        }
        return inspectorRepository.save(inspector);
    }

    @Override
    public void deleteInspector(Long id) {
        inspectorRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateInspector(AuthCredentials inspectorCredentials) {
        String email = inspectorCredentials.getEmail();
        String password = inspectorCredentials.getPassword();

        if(getInspectorByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.INSPECTOR, password)
            );

            var inspector = inspectorRepository.findInspectorByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("inspector no encontrado"));

            Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(inspector.getRol().name()));
            UserDetails userDetails = new UserDetailsImpl(inspector.getEmail() + USER_ROLE_SEPARATOR + UserRoles.INSPECTOR, inspector.getPassword(), grantedAuthority);

            var jwtToken = jwtService.generateToken(userDetails);
            inspector.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, inspector, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El inspector no está registrado")
            );
        }
    }

}
