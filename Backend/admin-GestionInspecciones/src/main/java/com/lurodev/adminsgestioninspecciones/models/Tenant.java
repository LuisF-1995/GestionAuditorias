package com.lurodev.adminsgestioninspecciones.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tenants")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Setter
    @Column(unique = true, nullable = false)
    private String companyName;

    @Setter
    @Column(unique = true, nullable = false)
    private String companyId; // Es el mismo NIT en colombia

    @Setter
    @Column(nullable = false)
    private String country;

    @Setter
    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"company"})
    private Admin tenantAdmin;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column(nullable = false)
    private Status status;

    @Setter
    @Column(nullable = false)
    private Boolean isolated;

    @Setter
    @Column(nullable = false)
    private Date registrationDate = new Date(System.currentTimeMillis());

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationPlans plan;

    @Setter
    @Column
    private Integer tenantPort;
}
