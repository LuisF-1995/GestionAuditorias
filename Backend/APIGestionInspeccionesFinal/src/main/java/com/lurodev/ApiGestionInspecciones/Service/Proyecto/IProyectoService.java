package com.lurodev.ApiGestionInspecciones.Service.Proyecto;

import com.lurodev.ApiGestionInspecciones.Entities.Proyecto;
import com.lurodev.ApiGestionInspecciones.Entities.dto.ProjectPreview;

import java.util.List;
import java.util.Optional;

public interface IProyectoService {
    List<Proyecto> getAllProyectos();
    List<ProjectPreview> getAllProjectPreview();
    Optional<Proyecto> getProyectoByID(Long IdProyecto);
    Optional<Proyecto> getProyectoByNumeroProforma(String numeroProforma);
    Optional<Proyecto> getProyectoByNombreProyecto(String nombreProyecto);
    Optional<Proyecto> getProyectoByNumeroCotizacion(String numeroCotizacion);
    Optional<Proyecto> getProyectoByNumeroInspeccion(String numeroInspeccion);
    Optional<Proyecto> getProyectoByEstadoProyecto(String estadoProyecto);
    Proyecto postProyecto(Proyecto proyecto);
    Proyecto updateProyecto(Proyecto proyecto);
    void deleteProyecto(Long IdProyectoDelete);
}
