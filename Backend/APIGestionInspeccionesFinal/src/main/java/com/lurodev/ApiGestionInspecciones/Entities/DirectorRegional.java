package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "directores_regional")
public class DirectorRegional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "tinyint unsigned"*/)
    private Long id;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column(unique = true)
    private Long numeroDocumento;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String telefono;
    @OneToOne
    @JoinColumn(name = "regional_id", referencedColumnName="id")
    @JsonIgnoreProperties({"directorRegional", "inspectores", "asesoresComerciales"})
    private Regional regional;
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.DIRECTOR_REGIONAL;


    //CONSTRUCTORES
    public DirectorRegional() {
    }
    public DirectorRegional(Long id) {
        this.id = id;
    }
    public DirectorRegional(String nombres, String apellidos, Long numeroDocumento, String email, String password, String telefono, Regional regional) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
    }
    public DirectorRegional(Long id, String nombres, String apellidos, Long numeroDocumento, String email, String password, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
    public DirectorRegional(Long id, String nombres, String apellidos, Long numeroDocumento, String email, String password, String telefono, Regional regional) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.regional = regional;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
