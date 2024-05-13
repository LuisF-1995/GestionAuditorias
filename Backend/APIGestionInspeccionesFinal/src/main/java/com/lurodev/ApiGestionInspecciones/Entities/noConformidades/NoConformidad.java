package com.lurodev.ApiGestionInspecciones.Entities.noConformidades;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lurodev.ApiGestionInspecciones.Entities.ActaInspeccion;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "no_conformidades")
public class NoConformidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "acta_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"procesosAsociados", "ciudad", "alcanceInspeccion", "agendaProyecto", "empresaFirmante",
            "nombresApellidosFirmante", "cargoFirmante", "cedulaFirmante", "firmaAperturaFirmante", "fotoAperturaFirmante",
            "firmaCierreFirmante", "fotoCierreFirmante", "noConformidades"})
    private ActaInspeccion actaInspeccion;
    @Column(nullable = false)
    private Short numeroNoConformidad;
    @Column(nullable = false)
    private String descripcionNoConformidad;
    @Column(nullable = false)
    private Boolean cumpleNocumple; // Marcacion sobre estado de aprobacion de la no conformidad (true=cumple, false=no cumple)
    @Column
    private String articuloNormativo;
    @Column
    private Long numeroOcurrencias; //numero con el cual se sabra cual es la no conformidad mas usada o mas recurrente y asi dar sugerencias mejores en un filtro
    @Column
    private LocalDate fechaCierreNc; // fecha en la cual se marcó la no conformidad como aprobada o cumpleNocumple en true (cumple)

    //@Lob @Basic(fetch=FetchType.LAZY)
    @OneToMany(mappedBy = "noConformidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name="evidencias_nc")
    @JsonIgnoreProperties({"noConformidad"})
    private List<EvidenciaNC> evidenciaNoConformidad = new ArrayList<>();


    //CONSTRUCTORS
    public NoConformidad() {
    }
    public NoConformidad(Long id) {
        this.id = id;
    }
    public NoConformidad(ActaInspeccion actaInspeccion, Short numeroNoConformidad, String descripcionNoConformidad, Boolean cumpleNocumple, String articuloNormativo, LocalDate fechaCierreNc, List<EvidenciaNC> evidenciaNoConformidad) {
        this.actaInspeccion = actaInspeccion;
        this.numeroNoConformidad = numeroNoConformidad;
        this.descripcionNoConformidad = descripcionNoConformidad;
        this.cumpleNocumple = cumpleNocumple;
        this.articuloNormativo = articuloNormativo;
        this.fechaCierreNc = fechaCierreNc;
        this.evidenciaNoConformidad = evidenciaNoConformidad;
    }
    public NoConformidad(Long id, ActaInspeccion actaInspeccion, Short numeroNoConformidad, String descripcionNoConformidad, Boolean cumpleNocumple, String articuloNormativo, LocalDate fechaCierreNc, List<EvidenciaNC> evidenciaNoConformidad) {
        this.id = id;
        this.actaInspeccion = actaInspeccion;
        this.numeroNoConformidad = numeroNoConformidad;
        this.descripcionNoConformidad = descripcionNoConformidad;
        this.cumpleNocumple = cumpleNocumple;
        this.articuloNormativo = articuloNormativo;
        this.fechaCierreNc = fechaCierreNc;
        this.evidenciaNoConformidad = evidenciaNoConformidad;
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public ActaInspeccion getActaInspeccion() {
        return actaInspeccion;
    }

    public void setActaInspeccion(ActaInspeccion actaInspeccion) {
        this.actaInspeccion = actaInspeccion;
    }

    public Short getNumeroNoConformidad() {
        return numeroNoConformidad;
    }

    public void setNumeroNoConformidad(Short numeroNoConformidad) {
        this.numeroNoConformidad = numeroNoConformidad;
    }

    public String getDescripcionNoConformidad() {
        return descripcionNoConformidad;
    }

    public void setDescripcionNoConformidad(String descripcionNoConformidad) {
        this.descripcionNoConformidad = descripcionNoConformidad;
    }

    public Boolean getCumpleNocumple() {
        return cumpleNocumple;
    }

    public void setCumpleNocumple(Boolean cumpleNocumple) {
        this.cumpleNocumple = cumpleNocumple;
    }

    public String getArticuloNormativo() {
        return articuloNormativo;
    }

    public void setArticuloNormativo(String articuloNormativo) {
        this.articuloNormativo = articuloNormativo;
    }

    public LocalDate getFechaCierreNc() {
        return fechaCierreNc;
    }

    public void setFechaCierreNc(LocalDate fechaCierreNc) {
        this.fechaCierreNc = fechaCierreNc;
    }

    public Long getNumeroOcurrencias() {
        return numeroOcurrencias;
    }

    public void setNumeroOcurrencias(Long numeroOcurrencias) {
        this.numeroOcurrencias = numeroOcurrencias;
    }

    public List<EvidenciaNC> getEvidenciaNoConformidad() {
        return evidenciaNoConformidad;
    }

    public void setEvidenciaNoConformidad(List<EvidenciaNC> evidenciaNoConformidad) {
        this.evidenciaNoConformidad = evidenciaNoConformidad;
    }
}
