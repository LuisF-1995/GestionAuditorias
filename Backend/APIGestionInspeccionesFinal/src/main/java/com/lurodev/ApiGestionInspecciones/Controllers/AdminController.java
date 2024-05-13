package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.Admin.IAdminService;
import com.lurodev.ApiGestionInspecciones.Service.Admin.RegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
public class AdminController {
    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/status-options")
    public ResponseEntity<AdminStatus[]> getListAdminStatusOptions(){
        return ResponseEntity.ok(AdminStatus.values());
    }
    @GetMapping("/user-roles")
    public ResponseEntity<UserRoles[]> getListUserRolesOptions(){
        return ResponseEntity.ok(UserRoles.values());
    }
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAdminsList(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @GetMapping("/id/{adminId}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable("adminId") Short adminId){
        return ResponseEntity.ok(adminService.getAdminById(adminId));
    }
    @GetMapping("/document/{adminDocument}")
    public ResponseEntity<Optional<Admin>> getAdminByDocument(@PathVariable("adminDocument") Long adminDocument){
        return ResponseEntity.ok(adminService.getAdminByDocument(adminDocument));
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerAdmin(@RequestBody Admin admin) throws IOException {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginAdmin(@RequestBody AuthCredentials authCredentials){
        return adminService.authenticateAdmin(authCredentials);
    }
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin updateResponse = adminService.updateAdmin(admin);
        if(updateResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateResponse);
    }

}
