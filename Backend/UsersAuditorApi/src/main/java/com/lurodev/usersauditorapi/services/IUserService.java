package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.dto.UserDTO;
import com.lurodev.usersauditorapi.dto.UserLogin;
import com.lurodev.usersauditorapi.models.User;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> getActiveUsers(Boolean isActive);
    List<UserDTO> getUsersByRegionalId(Short regionalId);
    UserDTO getUserById(UUID id);
    UserDTO getUserByName(String name);
    UserDTO getUserByDni(String dni);
    UserDTO getUserByEmail(String email);
    UserDTO getUserByProfessionalCardNumber(String professionalCardNumber);
    RequestResponse registerUser(User user);
    RequestResponse updateUser(User user);
    RequestResponse deleteUserById(UUID userId);
}
