package com.lurodev.usersauditorapi.repositories;

import com.lurodev.usersauditorapi.models.Rol;
import com.lurodev.usersauditorapi.models.User;
import com.lurodev.usersauditorapi.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findAllByTenantIdAndRol(@NonNull Short tenantId, UserRoles rol);
    List<Rol> findAllByTenantIdAndUser(@NonNull Short tenantId, User user);
    List<Rol> findAllByTenantId(@NonNull Short tenantId);
    Optional<Rol> findRolByTenantIdAndRol(@NonNull Short tenantId, UserRoles rol);
    Optional<Rol> findRolByTenantIdAndId(@NonNull Short tenantId, Long id);
}
