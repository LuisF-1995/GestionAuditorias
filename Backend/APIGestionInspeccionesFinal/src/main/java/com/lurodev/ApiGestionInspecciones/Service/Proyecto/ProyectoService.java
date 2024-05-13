package com.lurodev.ApiGestionInspecciones.Service.Proyecto;

import com.lurodev.ApiGestionInspecciones.Entities.Proyecto;
import com.lurodev.ApiGestionInspecciones.Entities.dto.ProjectPreview;
import com.lurodev.ApiGestionInspecciones.Repository.IProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService{
    private IProyectoRepository proyectoRepository;

    public ProyectoService(IProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }
    @Override
    public List<ProjectPreview> getAllProjectPreview() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos
            .stream()
            .map(ProjectPreview::fromProyecto) // Hacer casting a ProjectPreview
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Proyecto> getProyectoByID(Long IdProyecto) {
        return proyectoRepository.findById(IdProyecto);
    }

    @Override
    public Optional<Proyecto> getProyectoByNumeroProforma(String numeroProforma) {
        return proyectoRepository.findProyectoByNumeroProforma(numeroProforma);
    }

    @Override
    public Optional<Proyecto> getProyectoByNombreProyecto(String nombreProyecto) {
        return proyectoRepository.findProyectoByNombreProyecto(nombreProyecto);
    }

    @Override
    public Optional<Proyecto> getProyectoByNumeroCotizacion(String numeroCotizacion) {
        return proyectoRepository.findProyectoByNumeroCotizacion(numeroCotizacion);
    }

    @Override
    public Optional<Proyecto> getProyectoByNumeroInspeccion(String numeroInspeccion) {
        return proyectoRepository.findProyectoByNumeroInspeccion(numeroInspeccion);
    }

    @Override
    public Optional<Proyecto> getProyectoByEstadoProyecto(String estadoProyecto) {
        return proyectoRepository.findProyectoByEstadoProyecto(estadoProyecto);
    }

    @Override
    public Proyecto postProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto updateProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public void deleteProyecto(Long IdProyectoDelete) {
        proyectoRepository.deleteById(IdProyectoDelete);
    }
}
