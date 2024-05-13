package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.models.Rol;
import com.lurodev.usersauditorapi.models.UserRoles;
import org.springframework.lang.NonNull;

import java.util.List;

public interface IRolService {
    List<Rol> getRolesByTenantId(@NonNull Short tenantId);
    List<Rol> getRolByName(@NonNull Short tenantId, UserRoles rol);
    Rol getRolById(@NonNull Short tenantId, Long id);
    Rol createRol(Rol rol);
    Rol updateRol(Rol rol);
    Boolean deleteRolById(@NonNull Short tenantId, Long id);
}
