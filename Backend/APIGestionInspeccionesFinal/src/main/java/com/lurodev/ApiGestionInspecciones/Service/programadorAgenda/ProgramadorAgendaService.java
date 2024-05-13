package com.lurodev.ApiGestionInspecciones.Service.programadorAgenda;

import com.lurodev.ApiGestionInspecciones.Entities.ProgramadorAgenda;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Repository.ProgramadorAgendaRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class ProgramadorAgendaService implements IProgramadorAgendaService{
    private final ProgramadorAgendaRepository progradorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ProgramadorAgendaService(ProgramadorAgendaRepository progradorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.progradorRepository = progradorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<ProgramadorAgenda> getAllProgramadoresAgenda() {
        return progradorRepository.findAll();
    }

    @Override
    public Optional<ProgramadorAgenda> getProgramadorAgendaById(Long id) {
        return progradorRepository.findById(id);
    }

    @Override
    public Optional<ProgramadorAgenda> getProgramadorAgendaByEmail(String email) {
        return progradorRepository.findProgramadorAgendaByEmail(email);
    }

    @Override
    public ProgramadorAgenda createProgramadorAgenda(ProgramadorAgenda programadorAgenda) {
        String encryptedPassword = passwordEncoder.encode(programadorAgenda.getPassword());
        programadorAgenda.setPassword(encryptedPassword);
        return progradorRepository.save(programadorAgenda);
    }

    @Override
    public ProgramadorAgenda updateProgramadorAgenda(ProgramadorAgenda programadorAgenda) {
        if(programadorAgenda.getPassword() != null){
            String encryptedPassword = passwordEncoder.encode(programadorAgenda.getPassword());
            programadorAgenda.setPassword(encryptedPassword);
        }
        return progradorRepository.save(programadorAgenda);
    }

    @Override
    public Optional<ProgramadorAgenda> deleteProgramadorAgendaById(Long id) {
        Optional<ProgramadorAgenda> programadorAgenda = progradorRepository.findById(id);
        if(programadorAgenda.isPresent()){
            progradorRepository.deleteById(id);
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateProgramadorAgenda(AuthCredentials programdorCredentials) {
        String email = programdorCredentials.getEmail();
        String password = programdorCredentials.getPassword();

        if(getProgramadorAgendaByEmail(email).isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.PROGRAMADOR_AGENDA, password)
            );

            var programadorAgenda = progradorRepository.findProgramadorAgendaByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Programador de agenda no encontrado"));

            Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(programadorAgenda.getRol().name()));
            UserDetails userDetails = new UserDetailsImpl(programadorAgenda.getEmail() + USER_ROLE_SEPARATOR + UserRoles.PROGRAMADOR_AGENDA, programadorAgenda.getPassword(), grantedAuthority);

            var jwtToken = jwtService.generateToken(userDetails);
            programadorAgenda.setPassword(null);

            return ResponseEntity.ok().body(
                    new AuthenticationResponse(true, programadorAgenda, jwtToken, "Login exitoso")
            );
        }
        else{
            return ResponseEntity.ok().body(
                    new AuthenticationResponse(false, null, null, "El programador de agenda no está registrado")
            );
        }
    }
}
