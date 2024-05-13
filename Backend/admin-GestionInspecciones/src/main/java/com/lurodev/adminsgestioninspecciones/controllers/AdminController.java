package com.lurodev.adminsgestioninspecciones.controllers;

import com.lurodev.adminsgestioninspecciones.models.Admin;
import com.lurodev.adminsgestioninspecciones.services.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin-tenant")
public class AdminController {
    private final IAdminService adminService;
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAdminList() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/id/{adminId}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable("adminId") Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<Optional<Admin>> getAdminByDocument(@PathVariable("document") String document) {
        return ResponseEntity.ok(adminService.getAdminByDocument(document));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> getAdminByEmail(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdminRequest(admin));
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> addNewAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @PutMapping("/approve")
    public ResponseEntity<Admin> approveAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.approveAdmin(admin));
    }

    @PutMapping("/reject")
    public ResponseEntity<Admin> rejectAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.rejectAdmin(admin));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAdminById(@PathVariable("id") Long id){
        return ResponseEntity.ok(adminService.deleteAdminById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticateAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.authorizeAdmin(admin));
    }

}
