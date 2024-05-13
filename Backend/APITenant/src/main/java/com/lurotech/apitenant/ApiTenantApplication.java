package com.lurotech.apitenant;

import com.lurotech.apitenant.models.ScriptNames;
import com.lurotech.apitenant.services.ExecutionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTenantApplication.class, args);
    }

}
