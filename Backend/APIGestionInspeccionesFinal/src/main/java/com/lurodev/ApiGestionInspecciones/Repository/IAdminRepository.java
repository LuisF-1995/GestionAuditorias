package com.lurodev.ApiGestionInspecciones.Repository;

import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Short> {
    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByNumeroDocumento(Long documentoAdmin);
}
