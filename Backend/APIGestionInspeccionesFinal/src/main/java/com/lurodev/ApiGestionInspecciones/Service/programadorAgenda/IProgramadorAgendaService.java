package com.lurodev.ApiGestionInspecciones.Service.programadorAgenda;

import com.lurodev.ApiGestionInspecciones.Entities.ProgramadorAgenda;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProgramadorAgendaService {
    List<ProgramadorAgenda> getAllProgramadoresAgenda();
    Optional<ProgramadorAgenda> getProgramadorAgendaById(Long id);
    Optional<ProgramadorAgenda> getProgramadorAgendaByEmail(String email);
    ProgramadorAgenda createProgramadorAgenda(ProgramadorAgenda programadorAgenda);
    ProgramadorAgenda updateProgramadorAgenda(ProgramadorAgenda programadorAgenda);
    Optional<ProgramadorAgenda> deleteProgramadorAgendaById(Long id);
    ResponseEntity<AuthenticationResponse> authenticateProgramadorAgenda(AuthCredentials programdorCredentials);
}
