package com.lurodev.ApiGestionInspecciones.Entities.admin;

import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
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
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String telefono;
    @Column
    private String companyName;
    @Column
    private String companyId;
    @Column
    private String country;
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.ADMIN;
    @Enumerated(EnumType.STRING)
    private AdminStatus status = null;


    //CONSTRUCTOR
    public Admin() {
    }
    public Admin(Short id) {
        this.id = id;
    }
    public Admin(Short id, String nombres, String apellidos, Long numeroDocumento, String email, String password, String telefono, String companyName, String companyId, String country, AdminStatus status) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.companyName = companyName;
        this.companyId = companyId;
        this.country = country;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AdminStatus getStatus() {
        return status;
    }

    public void setStatus(AdminStatus status) {
        this.status = status;
    }
}
