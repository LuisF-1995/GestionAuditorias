package com.lurodev.usersauditorapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "competences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Setter
    @Column(nullable = false)
    private String competence;

    @Setter
    @ManyToMany(mappedBy = "competences")
    @JsonIgnoreProperties({"competences", "name", "lastName", "dni", "email", "phone", "regionalId", "professionalCardNumber", "enabled", "password", "isActive", "sign", "authorities", "tenantId"})
    private Set<User> users = new HashSet<>();

    @Setter
    @NonNull
    @Column(nullable = false)
    private Short tenantId;
}
