package com.lurodev.adminsgestioninspecciones.services;

import com.lurodev.adminsgestioninspecciones.models.Tenant;

import java.util.List;
import java.util.Optional;

public interface ITenantService {
    List<Tenant> getAllTenants();
    List<Tenant> getApprovedCompanies();
    Optional<Tenant> getTenantById(Short id);
    Optional<Tenant> getTenantByName(String companyName);
    Optional<Tenant> getTenantByNit(String companyId);
    Tenant createTenant(Tenant tenant);
    Tenant updateTenant(Tenant tenant);
    boolean deleteTenantById(Short id);
}
