package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.dto.UserAuthenticated;
import com.lurodev.usersauditorapi.dto.UserLogin;
import com.lurodev.usersauditorapi.models.User;
import com.lurodev.usersauditorapi.repositories.IUserRepository;
import com.lurodev.usersauditorapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RequestResponse authenticateUser(UserLogin userLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );

        User user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        UserAuthenticated userAuthenticated = new UserAuthenticated(user.getId(), jwtToken);
        return new RequestResponse(userAuthenticated, true, HttpStatus.OK.value(), "Authentication success");
    }

    @Override
    public Boolean validateToken(@NonNull String tenantId, String token, UUID userId) {
        boolean isValid = false;
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            isValid = jwtService.isValidToken(token, user.get(), tenantId);
        }

        return isValid;
    }

    @Override
    public Boolean sameTenantId(Object object, Short tokenTenantId) {
        boolean isSameTenantId = false;

        try {
            Method getterMethod = object.getClass().getMethod("getTenantId");
            Short tenantIdExtracted = (Short) getterMethod.invoke(object);
            isSameTenantId = tenantIdExtracted.equals(tokenTenantId);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }

        return isSameTenantId;
    }
}
