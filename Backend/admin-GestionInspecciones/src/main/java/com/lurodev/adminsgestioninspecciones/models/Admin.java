package com.lurodev.adminsgestioninspecciones.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admins")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    @Column(nullable = false)
    private String name;

    @Setter
    @Column
    private String lastname;

    @Setter
    @Column(unique = true)
    private String dni; // Es la cedula en Colombia o el numero de documento de identificacion

    @Setter
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @NonNull
    @Column(nullable = false)
    private String password;

    @Setter
    @NonNull
    @Column(nullable = false)
    private Boolean isActive;

    @Setter
    @NonNull
    @Column(nullable = false)
    private Short tenantId;

    @Setter
    @Column
    private String phone;

    @Setter
    @Column
    private String rol = "ADMIN";

    @Column
    @Setter
    private String privateKey;

    @Column
    @Setter
    private String secret;

    @Enumerated(EnumType.STRING)
    @Setter
    private Status status = null;
}
