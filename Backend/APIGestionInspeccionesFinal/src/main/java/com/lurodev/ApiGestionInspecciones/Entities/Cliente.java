package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String telefono;
    @Column
    private String clientId; // Puede ser el NIT o numero de identificacion del cliente
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"cliente"})
    private Set<Proyecto> proyectosCliente = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.CLIENTE;


    public Cliente() {
    }
    public Cliente(Long id) {
        this.id = id;
    }
    public Cliente(Long id, String password) {
        this.id = id;
        this.password = password;
    }
    public Cliente(Long id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    public Cliente(Long id, String nombre, String telefono, String email, String password, String clientId) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.clientId = clientId;
    }
    public Cliente(String nombre, String telefono, String email, String password, String clientId) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.clientId = clientId;
    }

    public Long getID() {
        return id;
    }

    public UserRoles getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Proyecto> getProyectosCliente() {
        return proyectosCliente;
    }

    public void setProyectosCliente(Set<Proyecto> proyectosCliente) {
        this.proyectosCliente = proyectosCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
