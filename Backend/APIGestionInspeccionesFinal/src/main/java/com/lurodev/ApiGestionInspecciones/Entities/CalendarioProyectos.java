package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "calendario_proyectos")
public class CalendarioProyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate fechaInspeccion;
    @Column(nullable = false)
    private Short numeroVisita;
    @ManyToOne
    @JoinColumn(name = "inspector_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"agendaProyectosInspector"})
    private Inspector inspector;
    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"calendarioProyectos"})
    private Proyecto proyecto;
    @OneToOne(mappedBy = "agendaProyecto")
    @JsonIgnoreProperties({"agendaProyecto", "proyecto"})
    private ActaInspeccion actaInspeccion;

    //CONSTRUCTORS
    public CalendarioProyectos() {
    }
    public CalendarioProyectos(LocalDate fechaInspeccion, Short numeroVisita, Inspector inspector, Proyecto proyecto) {
        this.fechaInspeccion = fechaInspeccion;
        this.numeroVisita = numeroVisita;
        this.inspector = inspector;
        this.proyecto = proyecto;
    }
    public CalendarioProyectos(Long id) {
        this.id = id;
    }
    public CalendarioProyectos(Long id, LocalDate fechaInspeccion, Short numeroVisita, Inspector inspector, Proyecto proyecto) {
        this.id = id;
        this.fechaInspeccion = fechaInspeccion;
        this.numeroVisita = numeroVisita;
        this.inspector = inspector;
        this.proyecto = proyecto;
    }
    public CalendarioProyectos(Long id, LocalDate fechaInspeccion, Short numeroVisita, Inspector inspector, Proyecto proyecto, ActaInspeccion actaInspeccion) {
        this.id = id;
        this.fechaInspeccion = fechaInspeccion;
        this.numeroVisita = numeroVisita;
        this.inspector = inspector;
        this.proyecto = proyecto;
        this.actaInspeccion = actaInspeccion;
    }

    //GETTES AND SETTERS
    public Long getId() {
        return id;
    }

    public ActaInspeccion getActaInspeccion() {
        return actaInspeccion;
    }

    public LocalDate getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(LocalDate fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

    public Short getNumeroVisita() {
        return numeroVisita;
    }

    public void setNumeroVisita(Short numeroVisita) {
        this.numeroVisita = numeroVisita;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
