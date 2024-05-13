package com.lurodev.adminsgestioninspecciones.controllers;

import com.lurodev.adminsgestioninspecciones.models.Tenant;
import com.lurodev.adminsgestioninspecciones.services.ITenantService;
import jakarta.annotation.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    private final ITenantService tenantService;

    public TenantController(ITenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants(){
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Tenant>> getApprovedTenants(){
        return ResponseEntity.ok(tenantService.getApprovedCompanies());
    }

    @GetMapping("/params")
    public ResponseEntity<Tenant> getTenantByParameters(
            @RequestParam("id") @Nullable Short id,
            @RequestParam("name") @Nullable String name,
            @RequestParam("nit") @Nullable String nit){
        Optional<Tenant> tenant;

        if(id != null){
            tenant = tenantService.getTenantById(id);
        }
        else if(name != null){
            tenant = tenantService.getTenantByName(name);
        }
        else if (nit != null){
            tenant = tenantService.getTenantByNit(nit);
        }
        else{
            tenant = Optional.empty();
        }

        Tenant tenantFound = null;

        if(tenant.isPresent()){
            tenantFound = tenant.get();
        }

        return ResponseEntity.ok(tenantFound);
    }

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant){
        Tenant tenantCreated = tenantService.createTenant(tenant);
        return ResponseEntity.ok(tenantCreated);
    }

    @PutMapping
    public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant){
        Tenant tenantUpdated = tenantService.updateTenant(tenant);
        return ResponseEntity.ok(tenantUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTenantById(@PathVariable("id") Short id){
        return ResponseEntity.ok(tenantService.deleteTenantById(id));
    }
}
