package com.lurodev.ApiGestionInspecciones.Service.ActaInspeccion;

import com.lurodev.ApiGestionInspecciones.Entities.ActaInspeccion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IActaInspeccionService {
    List<ActaInspeccion> getAllActasInspeccion();
    Optional<ActaInspeccion> getActaInspeccionById(Long id);
    Optional<Set<ActaInspeccion>> getActaInspeccionByCiudad(String ciudad);
    Optional<Set<ActaInspeccion>> getActaInspeccionByFechaInspeccion(LocalDate fechaInspeccion);
    Optional<Set<ActaInspeccion>> getActaInspeccionByEmpresaFirmante(String empresaFirmante);
    Optional<Set<ActaInspeccion>> getActaInspeccionByNombresFirmante(String nombresFirmante);
    Optional<Set<ActaInspeccion>> getActaInspeccionByCedulaFirmante(Long cedulaFirmante);

    ActaInspeccion postActaInspeccion(ActaInspeccion actaInspeccion);
    ActaInspeccion updateActaInspeccion(ActaInspeccion actaInspeccion);
    void deleteActaInspeccionById(Long id);
}
