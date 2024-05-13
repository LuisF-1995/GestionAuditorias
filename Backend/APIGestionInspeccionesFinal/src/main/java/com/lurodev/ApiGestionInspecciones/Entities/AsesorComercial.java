package com.lurodev.ApiGestionInspecciones.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asesores_comerciales")
public class AsesorComercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "smallint unsigned"*/)
    private Long id;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "regional_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"asesoresComerciales", "inspectores", "directorRegional"})
    private Regional regional;
    @OneToMany(mappedBy = "asesorComercial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"asesorComercial"})
    private Set<Proyecto> proyectosAsesor = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.ASESOR_COMERCIAL;
    @Column
    @Getter
    @Setter
    private String companyId;


    //CONSTRUCTORS
    public AsesorComercial(String nombres, String apellidos, String email, String password, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public AsesorComercial(String nombres, String apellidos, String email, String password, String telefono, Regional regional) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
    }
    public AsesorComercial(Long id) {
        this.id = id;
    }
    public AsesorComercial(Long id, String password) {
        this.id = id;
        this.password = password;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String password, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String telefono, Regional regional) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.regional = regional;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String password, String telefono, Regional regional) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String telefono, Set<Proyecto> proyectosAsesor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.proyectosAsesor = proyectosAsesor;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String telefono, Regional regional, Set<Proyecto> proyectosAsesor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.regional = regional;
        this.proyectosAsesor = proyectosAsesor;
    }
    public AsesorComercial(Long id, String nombres, String apellidos, String email, String password, String telefono, Regional regional, Set<Proyecto> proyectosAsesor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
        this.proyectosAsesor = proyectosAsesor;
    }

    //GETTERS AND SETTERS
    public Long getId() {
        return id;
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

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Set<Proyecto> getProyectosAsesor() {
        return proyectosAsesor;
    }

    public void setProyectosAsesor(Set<Proyecto> proyectosAsesor) {
        this.proyectosAsesor = proyectosAsesor;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRol() {
        return rol;
    }
}
