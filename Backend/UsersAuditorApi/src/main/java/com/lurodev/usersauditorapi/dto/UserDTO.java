package com.lurodev.usersauditorapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lurodev.usersauditorapi.models.Competence;
import com.lurodev.usersauditorapi.models.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String lastName;
    private String dni; // Es la cedula en Colombia o el numero de documento de identificacion
    private String email;
    //private Short tenantId;
    private String phone;
    private Short regionalId;
    @JsonIgnoreProperties({"user", "tenantId"})
    private Set<Rol> roles = new HashSet<>();
    private String professionalCardNumber; // Es el numero de la matricula profesional, solo aplica para ingenieros
    @JsonIgnoreProperties({"users"})
    private Set<Competence> competences = new HashSet<>();
    private Byte[] sign;
}
