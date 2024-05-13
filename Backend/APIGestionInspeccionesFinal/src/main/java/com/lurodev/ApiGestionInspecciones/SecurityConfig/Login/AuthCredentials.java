package com.lurodev.ApiGestionInspecciones.SecurityConfig.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthCredentials {
    private String email;
    private String password;
    private Long numeroDocumento;
    private String privateKey;
    private String secret;
    private String country;

    // CONSTRUCTORS
    public AuthCredentials() {
    }
    public AuthCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public AuthCredentials(String email, String password, Long numeroDocumento, String privateKey, String secret, String country) {
        this.email = email;
        this.password = password;
        this.numeroDocumento = numeroDocumento;
        this.privateKey = privateKey;
        this.secret = secret;
        this.country = country;
    }


    // GETTERS AND SETTERS
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

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
