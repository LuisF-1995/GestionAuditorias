package com.lurodev.adminsgestioninspecciones.repository;

import com.lurodev.adminsgestioninspecciones.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITenantRepository extends JpaRepository<Tenant, Short> {
    Optional<Tenant> findTenantByCompanyName(String companyName);
    Optional<Tenant> findTenantByCompanyId(String companyId);
    @Query("SELECT DISTINCT tenant FROM Tenant tenant WHERE tenant.status = 'APPROVED'")
    List<Tenant> findApprovedCompanies();
}
