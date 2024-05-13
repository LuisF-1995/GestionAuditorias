package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.dto.UserLogin;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface IAuthService {
    RequestResponse authenticateUser(UserLogin userLogin);
    Boolean validateToken(@NonNull String tenantId, String token, UUID userId);
    Boolean sameTenantId(Object object, Short tenantId);
}
