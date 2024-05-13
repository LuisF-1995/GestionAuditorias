package com.lurodev.adminsgestioninspecciones.constants;

public class Constants {
    private final String API_BASE_URL = "http://localhost:8080/admin/gestion-inspecciones/admin";
    private final String FRONT_ALLOWED_ORIGIN_TEST = "http://localhost:4200";
    private final String FRONT_ALLOWED_ORIGIN_PRODUCTION = "https://gestion-inspecciones-clients.vercel.app";
    public static String COMPANY_MAIL = "luis-1995@live.com.ar";
    public static String COMPANYMAIL_PASSWORD = "pgprvqmvnbfvcjwt";
    public static String MAIL_SERVER = "hotmail";
    public static String API_EMAIL = "https://api-emailsender.azurewebsites.net/sendEmail";


    public String getApiAdminBaseUrl() {
        return this.API_BASE_URL;
    }
    public String getFrontAllowedOriginTest(){
        return this.FRONT_ALLOWED_ORIGIN_TEST;
    }
    public String getFrontAllowedOriginProduction(){
        return this.FRONT_ALLOWED_ORIGIN_PRODUCTION;
    }
}
