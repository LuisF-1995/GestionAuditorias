package com.lurodev.usersauditorapi.multitenancy.tenantdetails;

import org.springframework.lang.Nullable;

import java.util.List;

public interface TenantDetailsService {
    List<TenantDetails> getAllTenants();
    @Nullable
    TenantDetails getTenantByIdentifier(String identifier);
}
