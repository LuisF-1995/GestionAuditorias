package com.lurodev.usersauditorapi.multitenancy.resolver;

import org.springframework.lang.Nullable;

@FunctionalInterface
public interface TenantResolver<T> {
    @Nullable
    String resolveTenantIdentifier(T source);
}
