package com.lurotech.apitenant.controllers;

import com.lurotech.apitenant.models.ScriptNames;
import com.lurotech.apitenant.models.TenantResponse;
import com.lurotech.apitenant.services.ExecutionService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {
    private final ExecutionService service;

    public TenantController(ExecutionService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<TenantResponse> createTenant(@RequestParam("isolated") boolean isolatedTenant, @RequestParam("tenantId") int tenantId, @RequestParam("tenantName") String tenantName, @RequestParam("apiPort") @Nullable String apiPort){
        TenantResponse tenantResponse = new TenantResponse(false, HttpStatus.NOT_IMPLEMENTED.value(), "No se creó el tenant");

        if(isolatedTenant){
            String dbName = "db_" + tenantName;
            String dbAdmin = "admindb_" + tenantName;
            String scriptName = ScriptNames.createdb.name();
            tenantResponse = service.execScript(scriptName, dbName, dbAdmin);
        }

        return ResponseEntity.ok(tenantResponse);
    }
}
