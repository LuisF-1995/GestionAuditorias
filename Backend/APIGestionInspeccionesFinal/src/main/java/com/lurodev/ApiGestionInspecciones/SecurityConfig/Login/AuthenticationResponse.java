package com.lurodev.ApiGestionInspecciones.SecurityConfig.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationResponse {
    private boolean authenticationSuccess;
    private Object userInfo;
    private String jwtToken;
    private String aditionalInfo;

    public AuthenticationResponse(boolean authenticationSuccess, Object userInfo, String jwtToken) {
        this.authenticationSuccess = authenticationSuccess;
        this.userInfo = userInfo;
        this.jwtToken = jwtToken;
    }
    public AuthenticationResponse(boolean authenticationSuccess, Object userInfo, String jwtToken, String aditionalInfo) {
        this.authenticationSuccess = authenticationSuccess;
        this.userInfo = userInfo;
        this.jwtToken = jwtToken;
        this.aditionalInfo = aditionalInfo;
    }

    //GETTERS AND SETTERS

    public boolean isAuthenticationSuccess() {
        return authenticationSuccess;
    }

    public void setAuthenticationSuccess(boolean authenticationSuccess) {
        this.authenticationSuccess = authenticationSuccess;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getAuthInfo() {
        return aditionalInfo;
    }

    public void setAuthInfo(String aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }
}
