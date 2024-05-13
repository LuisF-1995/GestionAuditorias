package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regionales")
public class Regional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(/*columnDefinition = "tinyint unsigned"*/)
    private Long id;
    @Column
    private String ciudad;
    @Column
    private String direccion;
    @Column
    private String telefono;
    @OneToOne(mappedBy = "regional")
    @JsonIgnoreProperties("regional")
    private DirectorRegional directorRegional;
    @OneToMany(mappedBy = "regional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"regional", "competencias"})
    private Set<Inspector> inspectores = new HashSet<>();
    @OneToMany(mappedBy = "regional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"regional"})
    private Set<AsesorComercial> asesoresComerciales = new HashSet<>();
    @Column
    @Getter
    @Setter
    private String companyId;


    // CONSTRUCTORES
    public Regional(String ciudad) {
        this.ciudad = ciudad;
    }
    public Regional(DirectorRegional directorRegional, String ciudad, Set<Inspector> inspectores) {
        this.directorRegional = directorRegional;
        this.ciudad = ciudad;
        this.inspectores = inspectores;
    }
    public Regional(String ciudad, DirectorRegional directorRegional, Set<Inspector> inspectores, Set<AsesorComercial> asesoresComerciales) {
        this.ciudad = ciudad;
        this.directorRegional = directorRegional;
        this.inspectores = inspectores;
        this.asesoresComerciales = asesoresComerciales;
    }
    public Regional(Long id) {
        this.id = id;
    }
    public Regional(Long id, String ciudad) {
        this.id = id;
        this.ciudad = ciudad;
    }
    public Regional(Long id, DirectorRegional directorRegional, String ciudad, Set<Inspector> inspectores) {
        this.id = id;
        this.directorRegional = directorRegional;
        this.ciudad = ciudad;
        this.inspectores = inspectores;
    }
    public Regional(Long id, DirectorRegional directorRegional) {
        this.id = id;
        this.directorRegional = directorRegional;
    }
    public Regional(Long id, Set<AsesorComercial> asesoresComerciales) {
        this.id = id;
        this.asesoresComerciales = asesoresComerciales;
    }
    public Regional(Long id, String ciudad, String direccion, String telefono, DirectorRegional directorRegional, Set<Inspector> inspectores, Set<AsesorComercial> asesoresComerciales) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.directorRegional = directorRegional;
        this.inspectores = inspectores;
        this.asesoresComerciales = asesoresComerciales;
    }


    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public DirectorRegional getDirectorRegional() {
        return directorRegional;
    }

    public void setDirectorRegional(DirectorRegional directorRegional) {
        this.directorRegional = directorRegional;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Inspector> getInspectores() {
        return inspectores;
    }

    public void setInspectores(Set<Inspector> inspectores) {
        this.inspectores = inspectores;
    }
    public Set<AsesorComercial> getAsesoresComerciales() {
        return asesoresComerciales;
    }
    public void setAsesoresComerciales(Set<AsesorComercial> asesoresComerciales) {
        this.asesoresComerciales = asesoresComerciales;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
