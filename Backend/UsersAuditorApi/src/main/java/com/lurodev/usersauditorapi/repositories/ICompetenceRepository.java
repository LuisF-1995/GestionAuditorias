package com.lurodev.usersauditorapi.repositories;

import com.lurodev.usersauditorapi.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICompetenceRepository extends JpaRepository<Competence, Short> {
    Optional<Competence> findByCompetenceAndTenantId(String competence, @NonNull Short tenantId);
    List<Competence> findAllByTenantId(Short tenantId);
}
