package com.lurodev.ApiGestionInspecciones.Entities.noConformidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "evidencias_nc")
public class EvidenciaNC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombreDocumento;
    @Column
    private String tipo;
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(nullable = false /*columnDefinition="MEDIUMBLOB"*/)
    private byte[] contenido;
    @ManyToOne
    @JoinColumn(name = "evidencia_id", referencedColumnName = "id")
    private NoConformidad noConformidad;


    public EvidenciaNC() {
    }
    public EvidenciaNC(Long id) {
        this.id = id;
    }
    public EvidenciaNC(Long id, String nombreDocumento, String tipo, byte[] contenido, NoConformidad noConformidad) {
        this.id = id;
        this.nombreDocumento = nombreDocumento;
        this.tipo = tipo;
        this.contenido = contenido;
        this.noConformidad = noConformidad;
    }

    public Long getId() {
        return id;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public NoConformidad getNoConformidad() {
        return noConformidad;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public void setNoConformidad(NoConformidad noConformidad) {
        this.noConformidad = noConformidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
