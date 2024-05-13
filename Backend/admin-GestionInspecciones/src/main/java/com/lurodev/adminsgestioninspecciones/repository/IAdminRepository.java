package com.lurodev.adminsgestioninspecciones.repository;

import com.lurodev.adminsgestioninspecciones.models.Admin;
import com.lurodev.adminsgestioninspecciones.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByDni(String dni);
    List<Admin> findAdminByStatus(Status adminStatus);

}
