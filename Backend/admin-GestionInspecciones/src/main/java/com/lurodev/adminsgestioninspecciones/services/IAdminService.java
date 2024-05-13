package com.lurodev.adminsgestioninspecciones.services;

import com.lurodev.adminsgestioninspecciones.models.Admin;
import com.lurodev.adminsgestioninspecciones.models.Status;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(Long id);
    boolean registerAdminRequest(Admin admin);
    Optional<Admin> getAdminByDocument(String dni);
    //List<Admin> getAdminsByCompanyName(String companyName);
    //List<Admin> getAdminsByCompanyId(String companyId);
    List<Admin> getAdminsByStatus(Status status);
    Admin createAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    Admin approveAdmin(Admin admin);
    Admin rejectAdmin(Admin admin);
    boolean deleteAdminById(Long id);
    boolean authorizeAdmin(Admin adminInfo);
    //List<Object[]> getListedCompanies();
}
