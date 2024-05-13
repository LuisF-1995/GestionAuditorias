package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "directores_tecnicos")
public class DirectorTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "tinyint unsigned"*/)
    private Short id;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column(unique = true)
    private Long numeroDocumento;
    @Column
    private String matriculaProfesional;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String telefono;
    //VARIABLE Y COLUMNA PARA ALMACENAR CUALES COMPETENCIAS SON LAS QUE ESTA AUTORIZADO EL DIRECTOR PARA FIRMAR EL DICTAMEN
    @ManyToMany
    @JsonIgnoreProperties({"directoresTecnicos", "inspectores"})
    @JoinTable(
        name = "directortecnico_competenciafirma",
        joinColumns = @JoinColumn(name = "directortecnico_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id", referencedColumnName="id")
    )
    private Set<Competencia> competenciasFirmaDictamenDt = new HashSet<>();
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="firma"/*, columnDefinition="BLOB"*/)
    private String firmaDirectorTecnico;
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.DIRECTOR_TECNICO;


    //CONSTRUCTORES
    public DirectorTecnico() {
    }
    public DirectorTecnico(Short id) {
        this.id = id;
    }

    public DirectorTecnico(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public DirectorTecnico(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Set<Competencia> competenciasFirmaDictamenDt) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.competenciasFirmaDictamenDt = competenciasFirmaDictamenDt;
    }
    public DirectorTecnico(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, String firmaDirectorTecnico) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.firmaDirectorTecnico = firmaDirectorTecnico;
    }
    public DirectorTecnico(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Set<Competencia> competenciasFirmaDictamenDt, String firmaDirectorTecnico) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.competenciasFirmaDictamenDt = competenciasFirmaDictamenDt;
        this.firmaDirectorTecnico = firmaDirectorTecnico;
    }

    public DirectorTecnico(Short id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public DirectorTecnico(Short id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Set<Competencia> competenciasFirmaDictamenDt) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.competenciasFirmaDictamenDt = competenciasFirmaDictamenDt;
    }
    public DirectorTecnico(Short id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, String firmaDirectorTecnico) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.firmaDirectorTecnico = firmaDirectorTecnico;
    }
    public DirectorTecnico(Short id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Set<Competencia> competenciasFirmaDictamenDt, String firmaDirectorTecnico) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.competenciasFirmaDictamenDt = competenciasFirmaDictamenDt;
        this.firmaDirectorTecnico = firmaDirectorTecnico;
    }


    //GETTERS AND SETTERS
    public Short getId() {
        return id;
    }

    public UserRoles getRol() {
        return rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Competencia> getCompetenciasFirmaDictamenDt() {
        return competenciasFirmaDictamenDt;
    }

    public void setCompetenciasFirmaDictamenDt(Set<Competencia> competenciasFirmaDictamenDt) {
        this.competenciasFirmaDictamenDt = competenciasFirmaDictamenDt;
    }

    public String getFirmaDirectorTecnico() {
        return firmaDirectorTecnico;
    }

    public void setFirmaDirectorTecnico(String firmaDirectorTecnico) {
        this.firmaDirectorTecnico = firmaDirectorTecnico;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
