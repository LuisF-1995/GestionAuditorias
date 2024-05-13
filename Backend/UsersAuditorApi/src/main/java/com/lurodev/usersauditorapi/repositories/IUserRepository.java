package com.lurodev.usersauditorapi.repositories;

import com.lurodev.usersauditorapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByTenantIdAndEmail(String tenantId, String email);
    Optional<User> findByName(String name);
    Optional<User> findByDni(String dni);
    Optional<User> findByEmail(String email);
    Optional<User> findByProfessionalCardNumber(String professionalCardNumber);
    List<User> findAllByIsActive(Boolean isActive);
    List<User> findAllByRegionalId(Short regionalId);
}
