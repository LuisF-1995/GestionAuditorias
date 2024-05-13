package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.models.Competence;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ICompetenceService {
    List<Competence> getCompetencesByTenantId(@NonNull Short tenantId);
    Competence getCompetenceById(@NonNull Short tenantId, Short id);
    Competence getCompetenceByName(@NonNull Short tenantId, String name);
    Competence registerCompetence(@NonNull Short tenantId,Competence competence);
    Competence updateCompetence(@NonNull Short tenantId, Competence competence);
    Boolean deleteCompetenceById(@NonNull Short tenantId, Short id);
}
