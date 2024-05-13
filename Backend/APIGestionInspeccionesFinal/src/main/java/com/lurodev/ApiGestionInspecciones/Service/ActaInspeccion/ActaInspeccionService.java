package com.lurodev.ApiGestionInspecciones.Service.ActaInspeccion;

import com.lurodev.ApiGestionInspecciones.Entities.ActaInspeccion;
import com.lurodev.ApiGestionInspecciones.Repository.ActaInspeccionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ActaInspeccionService implements IActaInspeccionService{
    private ActaInspeccionRepository actaInspeccionRepository;
    public ActaInspeccionService(ActaInspeccionRepository actaInspeccionRepository) {
        this.actaInspeccionRepository = actaInspeccionRepository;
    }


    @Override
    public List<ActaInspeccion> getAllActasInspeccion() {
        return actaInspeccionRepository.findAll();
    }

    @Override
    public Optional<ActaInspeccion> getActaInspeccionById(Long id) {
        return actaInspeccionRepository.findById(id);
    }

    @Override
    public Optional<Set<ActaInspeccion>> getActaInspeccionByCiudad(String ciudad) {
        return actaInspeccionRepository.findActaInspeccionByCiudad(ciudad);
    }

    @Override
    public Optional<Set<ActaInspeccion>> getActaInspeccionByFechaInspeccion(LocalDate fechaInspeccion) {
        return actaInspeccionRepository.findActaInspeccionByFechaInspeccion(fechaInspeccion);
    }

    @Override
    public Optional<Set<ActaInspeccion>> getActaInspeccionByEmpresaFirmante(String empresaFirmante) {
        return actaInspeccionRepository.findActaInspeccionByEmpresaFirmante(empresaFirmante);
    }

    @Override
    public Optional<Set<ActaInspeccion>> getActaInspeccionByNombresFirmante(String nombresFirmante) {
        return actaInspeccionRepository.findActaInspeccionByNombresApellidosFirmante(nombresFirmante);
    }

    @Override
    public Optional<Set<ActaInspeccion>> getActaInspeccionByCedulaFirmante(Long cedulaFirmante) {
        return actaInspeccionRepository.findActaInspeccionByCedulaFirmante(cedulaFirmante);
    }

    @Override
    public ActaInspeccion postActaInspeccion(ActaInspeccion actaInspeccion) {
        return actaInspeccionRepository.save(actaInspeccion);
    }

    @Override
    public ActaInspeccion updateActaInspeccion(ActaInspeccion actaInspeccion) {
        return actaInspeccionRepository.save(actaInspeccion);
    }

    @Override
    public void deleteActaInspeccionById(Long id) {
        actaInspeccionRepository.deleteById(id);
    }
}
