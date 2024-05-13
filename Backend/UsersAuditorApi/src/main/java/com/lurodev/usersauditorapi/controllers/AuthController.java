package com.lurodev.usersauditorapi.controllers;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.dto.UserLogin;
import com.lurodev.usersauditorapi.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping
    public ResponseEntity<RequestResponse> authenticate(@RequestBody UserLogin authUser){
        return ResponseEntity.ok(authService.authenticateUser(authUser));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam("tenantId") @NonNull String tenantId, @RequestParam("token") @NonNull String token, @RequestParam("userId") @NonNull UUID userId){
        return ResponseEntity.ok(authService.validateToken(tenantId, token, userId));
    }
}
