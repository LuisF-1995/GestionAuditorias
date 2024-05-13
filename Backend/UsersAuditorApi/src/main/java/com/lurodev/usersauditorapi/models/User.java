package com.lurodev.usersauditorapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    private String tenantId;

    @Setter
    @Column
    private String phone;

    @Setter
    @Column
    private Short regionalId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"user"})
    @Column
    private Set<Rol> roles = new HashSet<>();

    @Setter
    @Column(unique = true)
    private String professionalCardNumber; // Es el numero de la matricula profesional, solo aplica para ingenieros

    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_competences",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id", referencedColumnName="id")
    )
    @JsonIgnoreProperties({"users"})
    private Set<Competence> competences = new HashSet<>();

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column
    private Byte[] sign;


    // ==============================> USER DETAILS METHODS <===================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol : roles){
            GrantedAuthority collection = new SimpleGrantedAuthority(rol.getRol().name());
            authorities.add(collection);
        }

        return authorities;
    }

    @Override
    public @NonNull String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
