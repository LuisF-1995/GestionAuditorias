package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.NoConformidad;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actas_inspeccion")
public class ActaInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String procesosAsociados;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String alcanceInspeccion;
    @Column
    private LocalDate fechaInspeccion;
    @Column
    private LocalDate fechaCierreActa;
    @Column(nullable = false)
    private String empresaFirmante;
    @Column(name = "nombres_apellidosfirmante", nullable = false)
    private String nombresApellidosFirmante;
    @Column(nullable = false)
    private String cargoFirmante;
    @Column(nullable = false)
    private Long cedulaFirmante;
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="firma_aperturafirmante", /*columnDefinition="BLOB",*/ nullable = false)
    private String firmaAperturaFirmante; //firma de quien recibe la inspeccion ya sea constructor o delegado de inspeccion
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="foto_aperturafirmante"/*, columnDefinition="BLOB"*/)
    private String fotoAperturaFirmante;
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="firma_cierrefirmante"/*, columnDefinition="BLOB", nullable = false*/)
    private String firmaCierreFirmante; //misma firma de quien recibe la inspeccion ya sea constructor o delegado de inspeccion
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="foto_cierrefirmante"/*, columnDefinition="BLOB"*/)
    private String fotoCierreFirmante;
    @Column
    private Boolean apruebaInspeccion; //Es true en caso de que cumpla o false en caso que no cumpla
    @OneToOne
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"actaInspeccion", "proyecto"})
    private CalendarioProyectos agendaProyecto;
    @ManyToOne
    @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"calendarioProyectos", "actasProyecto"})
    private Proyecto proyecto;
    @OneToMany(mappedBy = "actaInspeccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"actaInspeccion"})
    private Set<NoConformidad> noConformidades = new HashSet<>();
    @Column
    private String observaciones;

    //CONSTRUCTORS
    public ActaInspeccion() {
    }
    public ActaInspeccion(Long id) {
        this.id = id;
    }
    public ActaInspeccion(String procesosAsociados, String ciudad, String alcanceInspeccion, LocalDate fechaInspeccion, LocalDate fechaCierreActa, String empresaFirmante, String nombresApellidosFirmante, String cargoFirmante, Long cedulaFirmante, String firmaAperturaFirmante, String fotoAperturaFirmante, String firmaCierreFirmante, String fotoCierreFirmante, Boolean apruebaInspeccion, CalendarioProyectos agendaProyecto, Proyecto proyecto, String observaciones) {
        this.procesosAsociados = procesosAsociados;
        this.ciudad = ciudad;
        this.alcanceInspeccion = alcanceInspeccion;
        this.fechaInspeccion = fechaInspeccion;
        this.fechaCierreActa = fechaCierreActa;
        this.empresaFirmante = empresaFirmante;
        this.nombresApellidosFirmante = nombresApellidosFirmante;
        this.cargoFirmante = cargoFirmante;
        this.cedulaFirmante = cedulaFirmante;
        this.firmaAperturaFirmante = firmaAperturaFirmante;
        this.fotoAperturaFirmante = fotoAperturaFirmante;
        this.firmaCierreFirmante = firmaCierreFirmante;
        this.fotoCierreFirmante = fotoCierreFirmante;
        this.apruebaInspeccion = apruebaInspeccion;
        this.agendaProyecto = agendaProyecto;
        this.proyecto = proyecto;
        this.observaciones = observaciones;
    }
    public ActaInspeccion(Long id, String procesosAsociados, String ciudad, String alcanceInspeccion, LocalDate fechaInspeccion, LocalDate fechaCierreActa, String empresaFirmante, String nombresApellidosFirmante, String cargoFirmante, Long cedulaFirmante, String firmaAperturaFirmante, String fotoAperturaFirmante, String firmaCierreFirmante, String fotoCierreFirmante, Boolean apruebaInspeccion, CalendarioProyectos agendaProyecto, Proyecto proyecto, String observaciones) {
        this.id = id;
        this.procesosAsociados = procesosAsociados;
        this.ciudad = ciudad;
        this.alcanceInspeccion = alcanceInspeccion;
        this.fechaInspeccion = fechaInspeccion;
        this.fechaCierreActa = fechaCierreActa;
        this.empresaFirmante = empresaFirmante;
        this.nombresApellidosFirmante = nombresApellidosFirmante;
        this.cargoFirmante = cargoFirmante;
        this.cedulaFirmante = cedulaFirmante;
        this.firmaAperturaFirmante = firmaAperturaFirmante;
        this.fotoAperturaFirmante = fotoAperturaFirmante;
        this.firmaCierreFirmante = firmaCierreFirmante;
        this.fotoCierreFirmante = fotoCierreFirmante;
        this.apruebaInspeccion = apruebaInspeccion;
        this.agendaProyecto = agendaProyecto;
        this.proyecto = proyecto;
        this.observaciones = observaciones;
    }


    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public String getProcesosAsociados() {
        return procesosAsociados;
    }

    public void setProcesosAsociados(String procesosAsociados) {
        this.procesosAsociados = procesosAsociados;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAlcanceInspeccion() {
        return alcanceInspeccion;
    }

    public void setAlcanceInspeccion(String alcanceInspeccion) {
        this.alcanceInspeccion = alcanceInspeccion;
    }

    public LocalDate getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(LocalDate fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

    public LocalDate getFechaCierreActa() {
        return fechaCierreActa;
    }

    public void setFechaCierreActa(LocalDate fechaCierreActa) {
        this.fechaCierreActa = fechaCierreActa;
    }

    public String getEmpresaFirmante() {
        return empresaFirmante;
    }

    public void setEmpresaFirmante(String empresaFirmante) {
        this.empresaFirmante = empresaFirmante;
    }

    public String getNombresApellidosFirmante() {
        return nombresApellidosFirmante;
    }

    public void setNombresApellidosFirmante(String nombresApellidosFirmante) {
        this.nombresApellidosFirmante = nombresApellidosFirmante;
    }

    public String getCargoFirmante() {
        return cargoFirmante;
    }

    public void setCargoFirmante(String cargoFirmante) {
        this.cargoFirmante = cargoFirmante;
    }

    public Long getCedulaFirmante() {
        return cedulaFirmante;
    }

    public void setCedulaFirmante(Long cedulaFirmante) {
        this.cedulaFirmante = cedulaFirmante;
    }

    public String getFirmaAperturaFirmante() {
        return firmaAperturaFirmante;
    }

    public void setFirmaAperturaFirmante(String firmaAperturaFirmante) {
        this.firmaAperturaFirmante = firmaAperturaFirmante;
    }

    public String getFotoAperturaFirmante() {
        return fotoAperturaFirmante;
    }

    public void setFotoAperturaFirmante(String fotoAperturaFirmante) {
        this.fotoAperturaFirmante = fotoAperturaFirmante;
    }

    public String getFirmaCierreFirmante() {
        return firmaCierreFirmante;
    }

    public void setFirmaCierreFirmante(String firmaCierreFirmante) {
        this.firmaCierreFirmante = firmaCierreFirmante;
    }

    public String getFotoCierreFirmante() {
        return fotoCierreFirmante;
    }

    public void setFotoCierreFirmante(String fotoCierreFirmante) {
        this.fotoCierreFirmante = fotoCierreFirmante;
    }

    public Boolean getApruebaInspeccion() {
        return apruebaInspeccion;
    }

    public void setApruebaInspeccion(Boolean apruebaInspeccion) {
        this.apruebaInspeccion = apruebaInspeccion;
    }

    public CalendarioProyectos getAgendaProyecto() {
        return agendaProyecto;
    }

    public void setAgendaProyecto(CalendarioProyectos agendaProyecto) {
        this.agendaProyecto = agendaProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Set<NoConformidad> getNoConformidades() {
        return noConformidades;
    }

    public void setNoConformidades(Set<NoConformidad> noConformidades) {
        this.noConformidades = noConformidades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
