package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto, Long> {
    Optional<Proyecto> findProyectoByNumeroProforma(String numeroProforma);
    Optional<Proyecto> findProyectoByNombreProyecto(String nombreProyecto);
    Optional<Proyecto> findProyectoByNumeroCotizacion(String cotizacion);
    Optional<Proyecto> findProyectoByNumeroInspeccion(String numeroInspeccion);
    Optional<Proyecto> findProyectoByEstadoProyecto(String estadoProyecto);
}
