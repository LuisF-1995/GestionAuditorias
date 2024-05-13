package com.lurodev.ApiGestionInspecciones.Service.Admin;

import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;

public class RegistrationResponse {
    private Admin response;
    private AdminStatus status;
    private boolean userAlreadyExists;

    //CONSTRUCTOR
    public RegistrationResponse() {
    }
    public RegistrationResponse(Admin response, AdminStatus status, boolean userAlreadyExists) {
        this.response = response;
        this.status = status;
        this.userAlreadyExists = userAlreadyExists;
    }


    // GETTERS AND SETTERS
    public Admin getResponse() {
        return response;
    }

    public void setResponse(Admin response) {
        this.response = response;
    }

    public AdminStatus getStatus() {
        return status;
    }

    public void setStatus(AdminStatus status) {
        this.status = status;
    }

    public boolean isUserAlreadyExists() {
        return userAlreadyExists;
    }

    public void setUserAlreadyExists(boolean userAlreadyExists) {
        this.userAlreadyExists = userAlreadyExists;
    }
}
