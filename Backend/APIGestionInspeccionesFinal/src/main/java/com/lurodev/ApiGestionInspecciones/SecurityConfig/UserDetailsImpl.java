package com.lurodev.ApiGestionInspecciones.SecurityConfig;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private String usernameUserDetails;
    private String passwordUserDetails;
    private Collection<? extends GrantedAuthority> authoritiesUserDetails;


    //CONSTRUCTOR
    public UserDetailsImpl(String usernameUserDetails, String passwordUserDetails, Collection<? extends GrantedAuthority> authoritiesUserDetails) {
        this.usernameUserDetails = usernameUserDetails;
        this.passwordUserDetails = passwordUserDetails;
        this.authoritiesUserDetails = authoritiesUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritiesUserDetails;
    }

    @Override
    public String getPassword() {
        return passwordUserDetails;
    }

    @Override
    public String getUsername() {
        return usernameUserDetails;
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
