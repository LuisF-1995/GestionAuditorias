package com.lurodev.adminsgestioninspecciones.services;

import com.lurodev.adminsgestioninspecciones.models.Tenant;
import com.lurodev.adminsgestioninspecciones.repository.ITenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService implements ITenantService{
    private final ITenantRepository tenantRepository;

    public TenantService(ITenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public List<Tenant> getApprovedCompanies() {
        return tenantRepository.findApprovedCompanies();
    }

    @Override
    public Optional<Tenant> getTenantById(Short id) {
        return tenantRepository.findById(id);
    }

    @Override
    public Optional<Tenant> getTenantByName(String companyName) {
        return tenantRepository.findTenantByCompanyName(companyName);
    }

    @Override
    public Optional<Tenant> getTenantByNit(String companyId) {
        return tenantRepository.findTenantByCompanyId(companyId);
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant updateTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public boolean deleteTenantById(Short id) {
        boolean success = false;
        Optional<Tenant> company = tenantRepository.findById(id);

        if(company.isPresent()){
            success = true;
            tenantRepository.deleteById(id);
        }

        return success;
    }
}
