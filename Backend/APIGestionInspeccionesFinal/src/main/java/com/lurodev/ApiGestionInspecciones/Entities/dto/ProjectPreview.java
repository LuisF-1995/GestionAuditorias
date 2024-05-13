package com.lurodev.ApiGestionInspecciones.Entities.dto;

import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import com.lurodev.ApiGestionInspecciones.Entities.ProjectStates;
import com.lurodev.ApiGestionInspecciones.Entities.ProjectType;
import com.lurodev.ApiGestionInspecciones.Entities.Proyecto;

public class ProjectPreview {
    private Long id;
    private String nombreProyecto;
    private String numeroCotizacion;
    private String numeroInspeccion;
    private ProjectStates estadoProyecto;
    private Short visitasCotizadas;
    private Short visitasRealizadas;
    private Cliente cliente;
    private ProjectType tipoProyecto;

    // CONSTRUCTORS
    public ProjectPreview() {
    }
    public ProjectPreview(Long id, String nombreProyecto, String numeroCotizacion, String numeroInspeccion, ProjectStates estadoProyecto, Short visitasCotizadas, Short visitasRealizadas, Cliente cliente, ProjectType tipoProyecto) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.estadoProyecto = estadoProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.cliente = cliente;
        this.tipoProyecto = tipoProyecto;
    }


    public static ProjectPreview fromProyecto(Proyecto proyecto) {
        ProjectPreview projectPreview = new ProjectPreview();
        projectPreview.setId(proyecto.getId());
        projectPreview.setNombreProyecto(proyecto.getNombreProyecto());
        projectPreview.setNumeroCotizacion(proyecto.getNumeroCotizacion());
        projectPreview.setNumeroInspeccion(proyecto.getNumeroInspeccion());
        projectPreview.setEstadoProyecto(proyecto.getEstadoProyecto());
        projectPreview.setVisitasCotizadas(proyecto.getVisitasCotizadas());
        projectPreview.setVisitasRealizadas(proyecto.getVisitasRealizadas());
        projectPreview.setCliente(proyecto.getCliente());
        projectPreview.setTipoProyecto(proyecto.getTipoProyecto());
        return projectPreview;
    }


    // GETTERS AND SETTERS

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(String numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getNumeroInspeccion() {
        return numeroInspeccion;
    }

    public void setNumeroInspeccion(String numeroInspeccion) {
        this.numeroInspeccion = numeroInspeccion;
    }

    public ProjectStates getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(ProjectStates estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public Short getVisitasCotizadas() {
        return visitasCotizadas;
    }

    public void setVisitasCotizadas(Short visitasCotizadas) {
        this.visitasCotizadas = visitasCotizadas;
    }

    public Short getVisitasRealizadas() {
        return visitasRealizadas;
    }

    public void setVisitasRealizadas(Short visitasRealizadas) {
        this.visitasRealizadas = visitasRealizadas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProjectType getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(ProjectType tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }
}
