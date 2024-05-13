package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombreProyecto;
    @Column
    @Enumerated(EnumType.STRING)
    private ProjectType tipoProyecto;
    @Column
    private String numeroProforma;
    @Column
    private String numeroCotizacion;
    @Column
    private String numeroInspeccion;
    @Column
    private String alcance;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStates estadoProyecto;
    @Column(nullable = false)
    private String direccionProyecto;
    @Column(nullable = false)
    private Short visitasCotizadas;
    @Column
    private Short visitasRealizadas;
    @ManyToOne
    @JoinColumn(name = "asesor_id", referencedColumnName="id")
    @JsonIgnoreProperties({"proyectosAsesor"})
    private AsesorComercial asesorComercial;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName="id")
    @JsonIgnoreProperties({"proyectosCliente"})
    private Cliente cliente;
    @ManyToMany(mappedBy = "proyectosConstructor")
    @JsonIgnoreProperties("proyectosConstructor")
    private Set<Constructor> constructores = new HashSet<>();
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"proyecto"})
    private Set<CalendarioProyectos> calendarioProyectos = new HashSet<>();
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ActaInspeccion> actasProyecto = new HashSet<>();
    @Column
    private LocalDate fechaCierreProyecto; //Determina la fecha de cierre del proceso de inspección, es decir cuando ya se va a cerrar el expediente


    //CONSTRUCTORS
    public Proyecto() {
    }
    public Proyecto(Long id) {
        this.id = id;
    }
    public Proyecto(String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas) {
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
    }
    public Proyecto(String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial) {
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
    }
    public Proyecto(String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, Cliente cliente) {
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.cliente = cliente;
    }
    public Proyecto(String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial, Cliente cliente, Set<Constructor> constructores, Set<CalendarioProyectos> calendarioProyectos, ProjectType tipoProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
        this.cliente = cliente;
        this.constructores = constructores;
        this.calendarioProyectos = calendarioProyectos;
        this.tipoProyecto = tipoProyecto;
    }

    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
    }
    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
    }
    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, Cliente cliente) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.cliente = cliente;
    }
    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial, Cliente cliente) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
        this.cliente = cliente;
    }
    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial, Cliente cliente, Set<Constructor> constructores, Set<CalendarioProyectos> calendarioProyectos) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
        this.cliente = cliente;
        this.constructores = constructores;
        this.calendarioProyectos = calendarioProyectos;
    }
    public Proyecto(Long id, String nombreProyecto, String numeroProforma, String numeroCotizacion, String numeroInspeccion, String alcance, ProjectStates estadoProyecto, String direccionProyecto, Short visitasCotizadas, Short visitasRealizadas, AsesorComercial asesorComercial, Cliente cliente, Set<Constructor> constructores, Set<CalendarioProyectos> calendarioProyectos, LocalDate fechaCierreProyecto, ProjectType tipoProyecto) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.numeroProforma = numeroProforma;
        this.numeroCotizacion = numeroCotizacion;
        this.numeroInspeccion = numeroInspeccion;
        this.alcance = alcance;
        this.estadoProyecto = estadoProyecto;
        this.direccionProyecto = direccionProyecto;
        this.visitasCotizadas = visitasCotizadas;
        this.visitasRealizadas = visitasRealizadas;
        this.asesorComercial = asesorComercial;
        this.cliente = cliente;
        this.constructores = constructores;
        this.calendarioProyectos = calendarioProyectos;
        this.fechaCierreProyecto = fechaCierreProyecto;
        this.tipoProyecto = tipoProyecto;
    }


    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }
    public Set<ActaInspeccion> getActasProyecto() {
        return actasProyecto;
    }
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getNumeroProforma() {
        return numeroProforma;
    }

    public void setNumeroProforma(String numeroProforma) {
        this.numeroProforma = numeroProforma;
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

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
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

    public AsesorComercial getAsesorComercial() {
        return asesorComercial;
    }

    public void setAsesorComercial(AsesorComercial asesorComercial) {
        this.asesorComercial = asesorComercial;
    }

    public Set<Constructor> getConstructores() {
        return constructores;
    }

    public void setConstructores(Set<Constructor> constructores) {
        this.constructores = constructores;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDireccionProyecto() {
        return direccionProyecto;
    }

    public void setDireccionProyecto(String direccionProyecto) {
        this.direccionProyecto = direccionProyecto;
    }

    public Short getVisitasRealizadas() {
        return visitasRealizadas;
    }

    public void setVisitasRealizadas(Short visitasRealizadas) {
        this.visitasRealizadas = visitasRealizadas;
    }

    public Set<CalendarioProyectos> getCalendarioProyectos() {
        return calendarioProyectos;
    }

    public void setCalendarioProyectos(Set<CalendarioProyectos> calendarioProyectos) {
        this.calendarioProyectos = calendarioProyectos;
    }

    public LocalDate getFechaCierreProyecto() {
        return fechaCierreProyecto;
    }

    public void setFechaCierreProyecto(LocalDate fechaCierreProyecto) {
        this.fechaCierreProyecto = fechaCierreProyecto;
    }

    public ProjectType getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(ProjectType tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }
}
