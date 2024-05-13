package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.ActaInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ActaInspeccionRepository extends JpaRepository<ActaInspeccion, Long> {
    Optional<Set<ActaInspeccion>> findActaInspeccionByCiudad(String ciudad);
    Optional<Set<ActaInspeccion>> findActaInspeccionByFechaInspeccion(LocalDate fechaInspeccion);
    Optional<Set<ActaInspeccion>> findActaInspeccionByEmpresaFirmante(String empresaFirmante);
    Optional<Set<ActaInspeccion>> findActaInspeccionByNombresApellidosFirmante(String nombreApellidosFirmante);
    Optional<Set<ActaInspeccion>> findActaInspeccionByCedulaFirmante(Long cedulaFirmante);

}
