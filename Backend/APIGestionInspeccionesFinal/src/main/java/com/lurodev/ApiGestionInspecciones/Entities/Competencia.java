package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "competencias")
public class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "tinyint unsigned"*/)
    private Long id;
    @Column(unique = true)
    private String competencia;
    @ManyToMany(mappedBy = "competencias")
    @JsonIgnoreProperties({"competencias", "agendaProyectosInspector", "firmaInspector"})
    private Set<Inspector> inspectores = new HashSet<>();
    @ManyToMany(mappedBy = "competenciasFirmaDictamenDt")
    @JsonIgnoreProperties({"competenciasFirmaDictamenDt", "firmaDirectorTecnico"})
    private Set<DirectorTecnico> directoresTecnicos = new HashSet<>();


    // CONSTRUCTORES
    public Competencia() {
    }
    public Competencia(Long id) {
        this.id = id;
    }
    public Competencia(String competencia) {
        this.competencia = competencia;
    }
    public Competencia(String competencia, Set<Inspector> inspectores) {
        this.competencia = competencia;
        this.inspectores = inspectores;
    }
    public Competencia(String competencia, Set<Inspector> inspectores, Set<DirectorTecnico> directoresTecnicos) {
        this.competencia = competencia;
        this.inspectores = inspectores;
        this.directoresTecnicos = directoresTecnicos;
    }
    public Competencia(Long id, String competencia) {
        this.id = id;
        this.competencia = competencia;
    }
    public Competencia(Long id, String competencia, Set<Inspector> inspectores) {
        this.id = id;
        this.competencia = competencia;
        this.inspectores = inspectores;
    }
    public Competencia(Long id, String competencia, Set<Inspector> inspectores, Set<DirectorTecnico> directoresTecnicos) {
        this.id = id;
        this.competencia = competencia;
        this.inspectores = inspectores;
        this.directoresTecnicos = directoresTecnicos;
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }
    public String getCompetencia() {
        return competencia;
    }
    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
    public Set<Inspector> getInspectores() {
        return inspectores;
    }
    public void setInspectores(Set<Inspector> inspectores) {
        this.inspectores = inspectores;
    }

    public Set<DirectorTecnico> getDirectoresTecnicos() {
        return directoresTecnicos;
    }

    public void setDirectoresTecnicos(Set<DirectorTecnico> directoresTecnicos) {
        this.directoresTecnicos = directoresTecnicos;
    }
}
