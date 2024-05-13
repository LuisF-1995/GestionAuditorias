package com.lurodev.ApiGestionInspecciones.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "inspectores")
public class Inspector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "smallint unsigned"*/)
    private Long id;
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
    @ManyToOne
    @JoinColumn(name = "regional_id", referencedColumnName="id", nullable = false)
    @JsonIgnoreProperties({"inspectores", "asesoresComerciales", "directorRegional"})
    private Regional regional;
    @ManyToMany
    @JoinTable(
        name = "inspector_competencia",
        joinColumns = @JoinColumn(name = "inspector_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id", referencedColumnName="id")
    )
    @JsonIgnoreProperties({"inspectores", "directoresTecnicos"})
    private Set<Competencia> competencias = new HashSet<>();
    @OneToMany(mappedBy = "inspector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"inspector"})
    private Set<CalendarioProyectos> agendaProyectosInspector = new HashSet<>();
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="firma"/*, columnDefinition="BLOB"*/)
    private String firmaInspector;
    @Enumerated(EnumType.STRING)
    private UserRoles rol = UserRoles.INSPECTOR;


    // CONSTRUCTORES
    public Inspector() {
    }
    public Inspector(Long id) {
        this.id = id;
    }
    public Inspector(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
    }
    public Inspector(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional, String firmaInspector) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.firmaInspector = firmaInspector;
    }
    public Inspector(String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional, Set<Competencia> competencias, Set<CalendarioProyectos> agendaProyectosInspector) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.competencias = competencias;
        this.agendaProyectosInspector = agendaProyectosInspector;
    }
    public Inspector(Long id, Regional regional) {
        this.id = id;
        this.regional = regional;
    }
    public Inspector(Long id, Set<Competencia> competencias) {
        this.id = id;
        this.competencias = competencias;
    }
    public Inspector(Long id, String firmaInspector) {
        this.id = id;
        this.firmaInspector = firmaInspector;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional, Set<Competencia> competencias) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.competencias = competencias;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, String firmaInspector) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.firmaInspector = firmaInspector;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional, Set<Competencia> competencias, Set<CalendarioProyectos> agendaProyectosInspector) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.competencias = competencias;
        this.agendaProyectosInspector = agendaProyectosInspector;
    }
    public Inspector(Long id, String nombres, String apellidos, Long numeroDocumento, String matriculaProfesional, String email, String password, String telefono, Regional regional, String firmaInspector) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.matriculaProfesional = matriculaProfesional;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.firmaInspector = firmaInspector;
    }

    //GETTERS AND SETTERS
    public Long getId() {
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
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Set<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CalendarioProyectos> getAgendaProyectosInspector() {
        return agendaProyectosInspector;
    }

    public void setAgendaProyectosInspector(Set<CalendarioProyectos> agendaProyectosInspector) {
        this.agendaProyectosInspector = agendaProyectosInspector;
    }

    public String getFirmaInspector() {
        return firmaInspector;
    }

    public void setFirmaInspector(String firmaInspector) {
        this.firmaInspector = firmaInspector;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

}

