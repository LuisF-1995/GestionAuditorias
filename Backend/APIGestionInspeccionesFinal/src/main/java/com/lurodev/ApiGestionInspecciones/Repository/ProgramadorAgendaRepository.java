package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.ProgramadorAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramadorAgendaRepository extends JpaRepository<ProgramadorAgenda, Long> {
    Optional<ProgramadorAgenda> findProgramadorAgendaByEmail(String email);
}
