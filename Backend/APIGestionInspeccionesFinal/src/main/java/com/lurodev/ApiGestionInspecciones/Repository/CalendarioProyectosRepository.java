package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.CalendarioProyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CalendarioProyectosRepository extends JpaRepository<CalendarioProyectos, Long> {
    Optional<CalendarioProyectos> findCalendarioProyectosByFechaInspeccion(LocalDate fechaInspeccion);
}
