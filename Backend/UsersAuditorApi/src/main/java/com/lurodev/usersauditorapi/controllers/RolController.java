package com.lurodev.usersauditorapi.controllers;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.models.Rol;
import com.lurodev.usersauditorapi.services.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {
    private final IRolService rolService;

    @GetMapping
    public ResponseEntity<RequestResponse> getAllRolesByTenantId(@RequestParam("tenantId") @NonNull Short tenantId){
        List<Rol> roles = rolService.getRolesByTenantId(tenantId);
        RequestResponse response = new RequestResponse(roles, true, HttpStatus.OK.value(), "All Roles for tenant id: " + tenantId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getRolById(@RequestParam("tenantId") @NonNull Short tenantId, @PathVariable("id") Long id){
        Rol rol = rolService.getRolById(tenantId, id);
        RequestResponse response = new RequestResponse(null, false, HttpStatus.NOT_FOUND.value(), "Rol not found");

        if(rol != null){
            response = new RequestResponse(rol, true, HttpStatus.OK.value(), "Rol by id " + id);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol){
        return ResponseEntity.ok(rolService.createRol(rol));
    }

    @PutMapping
    public ResponseEntity<Rol> updateRol(@RequestBody Rol rol){
        return ResponseEntity.ok(rolService.createRol(rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRolById(@RequestParam("tenantId") @NonNull Short tenantId, @PathVariable("id") Long id){
        return ResponseEntity.ok(rolService.deleteRolById(tenantId, id));
    }
}
