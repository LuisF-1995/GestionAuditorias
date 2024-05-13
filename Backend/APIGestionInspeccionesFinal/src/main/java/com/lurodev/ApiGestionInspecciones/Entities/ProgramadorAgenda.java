package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "programadores-agenda")
public class ProgramadorAgenda {
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
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.PROGRAMADOR_AGENDA;


    // CONSTRUCTOR
    public ProgramadorAgenda() {
    }
    public ProgramadorAgenda(Long id) {
        this.id = id;
    }
    public ProgramadorAgenda(Long id, String nombres, String apellidos, String email, String password, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
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

    public UserRoles getRol() {
        return rol;
    }
}
