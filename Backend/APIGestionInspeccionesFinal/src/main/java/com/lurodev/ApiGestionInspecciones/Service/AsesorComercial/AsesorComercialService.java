package com.lurodev.ApiGestionInspecciones.Service.AsesorComercial;

import com.lurodev.ApiGestionInspecciones.Entities.AsesorComercial;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.AsesorComercialRepository;
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
public class AsesorComercialService implements IAsesorComercial{
    private AsesorComercialRepository asesorComercialRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AsesorComercialService(AsesorComercialRepository asesorComercialRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.asesorComercialRepository = asesorComercialRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<AsesorComercial> getAllAsesoresComerciales() {
        return asesorComercialRepository.findAll();
    }

    @Override
    public Optional<AsesorComercial> getAsesorComercialById(Long id) {
        return asesorComercialRepository.findById(id);
    }
    public Optional<AsesorComercial> getAsesorComercialByEmail(String email) {
        return asesorComercialRepository.findAsesorComercialByEmail(email);
    }

    @Override
    public AsesorComercial postAsesorComercial(AsesorComercial asesorComercial) {
        String encryptedPassword = passwordEncoder.encode(asesorComercial.getPassword());
        asesorComercial.setPassword(encryptedPassword);
        return asesorComercialRepository.save(asesorComercial);
    }

    @Override
    public AsesorComercial updateAsesorComercial(AsesorComercial asesorComercial) {
        if(asesorComercial.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(asesorComercial.getPassword());
            asesorComercial.setPassword(encryptedPassword);
        }
        return asesorComercialRepository.save(asesorComercial);
    }

    @Override
    public void deleteAsesorComercialById(Long id) {
        asesorComercialRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateAsesorComercial(AuthCredentials asesorComercialCredentials) {
        String email = asesorComercialCredentials.getEmail();
        String password = asesorComercialCredentials.getPassword();

        if(getAsesorComercialByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.ASESOR_COMERCIAL, password)
            );

            var asesorComercial = asesorComercialRepository.findAsesorComercialByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Asesor comercial no encontrado"));

            Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(asesorComercial.getRol().name()));
            UserDetails userDetails = new UserDetailsImpl(asesorComercial.getEmail() + USER_ROLE_SEPARATOR + UserRoles.ASESOR_COMERCIAL, asesorComercial.getPassword(), grantedAuthority);

            var jwtToken = jwtService.generateToken(userDetails);
            asesorComercial.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, asesorComercial, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El Asesor comercial no está registrado con el email ingresado")
            );
        }
    }
}
