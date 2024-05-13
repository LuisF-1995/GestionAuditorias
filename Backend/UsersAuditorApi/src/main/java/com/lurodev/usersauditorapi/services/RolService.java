package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.models.Rol;
import com.lurodev.usersauditorapi.models.UserRoles;
import com.lurodev.usersauditorapi.repositories.IRolRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService{
    private final IRolRepository rolRepository;

    @Override
    public List<Rol> getRolesByTenantId(@NonNull Short tenantId) {
        return rolRepository.findAllByTenantId(tenantId);
    }

    @Override
    public Rol getRolById(@NonNull Short tenantId, Long id) {
        Optional<Rol> rolOpt = rolRepository.findRolByTenantIdAndId(tenantId, id);
        Rol rol = null;

        if(rolOpt.isPresent()){
            rol = rolOpt.get();
        }

        return rol;
    }

    @Override
    public List<Rol> getRolByName(@NonNull Short tenantId, UserRoles rol) {
        return rolRepository.findAllByTenantIdAndRol(tenantId, rol);
    }

    @Override
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol updateRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Boolean deleteRolById(@NonNull Short tenantId, Long id) {
        boolean deletionSuccess = false;
        Rol rol = this.getRolById(tenantId, id);

        if(rol != null){
            rolRepository.deleteById(id);
            deletionSuccess = true;
        }

        return deletionSuccess;
    }
}
