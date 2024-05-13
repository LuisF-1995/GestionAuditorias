package com.lurodev.ApiGestionInspecciones.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="constructores")
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column
    private String tipoDocumento;
    @Column(unique = true)
    private Long numeroDocumento;
    @Column
    private String telefono;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String matriculaProfesional;
    @Column
    private String direccionDomicilio;
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="firma"/*, columnDefinition="BLOB"*/)
    private String firmaConstructor;
    @ManyToMany
    @JsonIgnoreProperties({"constructores"})
    @JoinTable(
        name = "proyecto_constructor",
        joinColumns = @JoinColumn(name = "constructor_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id")
    )
    private Set<Proyecto> proyectosConstructor = new HashSet<>();
    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name="qr_constructor"/*, columnDefinition="BLOB"*/)
    private String qrConstructor; //Codigo QR generado para cada constructor, con el cual se podrá validar su identidad
    @Enumerated(EnumType.STRING)
    private final UserRoles rol = UserRoles.CONSTRUCTOR;


    //CONSTRUCTORES
    public Constructor() {
    }
    public Constructor(Long id) {
        this.id = id;
    }
    public Constructor(String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
    }
    public Constructor(String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio, String firmaConstructor) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
        this.firmaConstructor = firmaConstructor;
    }
    public Constructor(String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio, String firmaConstructor, Set<Proyecto> proyectosConstructor) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
        this.firmaConstructor = firmaConstructor;
        this.proyectosConstructor = proyectosConstructor;
    }
    public Constructor(String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio, String firmaConstructor, Set<Proyecto> proyectosConstructor, String qrConstructor) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
        this.firmaConstructor = firmaConstructor;
        this.proyectosConstructor = proyectosConstructor;
        this.qrConstructor = qrConstructor;
    }

    public Constructor(Long id, String firmaConstructor) {
        this.id = id;
        this.firmaConstructor = firmaConstructor;
    }
    public Constructor(Long id, Set<Proyecto> proyectosConstructor) {
        this.id = id;
        this.proyectosConstructor = proyectosConstructor;
    }
    public Constructor(Long id, String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio, String firmaConstructor, Set<Proyecto> proyectosConstructor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
        this.firmaConstructor = firmaConstructor;
        this.proyectosConstructor = proyectosConstructor;
    }
    public Constructor(Long id, String nombres, String apellidos, String tipoDocumento, Long numeroDocumento, String telefono, String email, String password, String matriculaProfesional, String direccionDomicilio, String firmaConstructor, Set<Proyecto> proyectosConstructor, String qrConstructor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.matriculaProfesional = matriculaProfesional;
        this.direccionDomicilio = direccionDomicilio;
        this.firmaConstructor = firmaConstructor;
        this.proyectosConstructor = proyectosConstructor;
        this.qrConstructor = qrConstructor;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getFirmaConstructor() {
        return firmaConstructor;
    }

    public void setFirmaConstructor(String firmaConstructor) {
        this.firmaConstructor = firmaConstructor;
    }

    public Set<Proyecto> getProyectosConstructor() {
        return proyectosConstructor;
    }

    public void setProyectosConstructor(Set<Proyecto> proyectosConstructor) {
        this.proyectosConstructor = proyectosConstructor;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQrConstructor() {
        return qrConstructor;
    }

    public void setQrConstructor(String qrConstructor) {
        this.qrConstructor = qrConstructor;
    }
}
