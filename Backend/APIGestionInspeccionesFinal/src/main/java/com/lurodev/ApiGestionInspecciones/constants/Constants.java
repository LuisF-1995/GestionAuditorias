package com.lurodev.ApiGestionInspecciones.constants;

public class Constants {
    private final String API_ADMIN_BASE_URL = "http://localhost:8090/api/gestion-inspecciones/admin";
    private final String FRONT_ALLOWED_ORIGIN_TEST = "http://localhost:3000";
    private final String FRONT_ALLOWED_ORIGIN_PRODUCTION = "https://gestion-inspecciones-clients.vercel.app";
    public static final String USER_ROLE_SEPARATOR = "-/USER_ROLE_SEPARATOR=/-";

    public String getApiAdminBaseUrl() {
        return this.API_ADMIN_BASE_URL;
    }
    public String getFrontAllowedOriginTest(){
        return this.FRONT_ALLOWED_ORIGIN_TEST;
    }
    public String getFrontAllowedOriginProduction(){
        return this.FRONT_ALLOWED_ORIGIN_PRODUCTION;
    }
}
