package com.lurodev.usersauditorapi.services;

import com.lurodev.usersauditorapi.dto.RequestResponse;
import com.lurodev.usersauditorapi.dto.UserDTO;
import com.lurodev.usersauditorapi.models.User;
import com.lurodev.usersauditorapi.multitenancy.context.TenantContext;
import com.lurodev.usersauditorapi.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${multitenancy.http.header-name}")
    String tenantIdHeaderName;


    private List<UserDTO> convertUserListToUserDTOList(List<User> users){
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users){
            UserDTO userDTO = this.convertUserToUserDTO(user);
            userDTOs.add(userDTO);
        }

        return  userDTOs;
    }

    private UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastname());
        userDTO.setDni(user.getDni());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRegionalId(user.getRegionalId());
        userDTO.setRoles(user.getRoles());
        userDTO.setProfessionalCardNumber(user.getProfessionalCardNumber());
        userDTO.setCompetences(user.getCompetences());
        userDTO.setSign(user.getSign());

        return userDTO;
    }

    /*
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return this.convertUserListToUserDTOList(users);
    }
    */

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return this.convertUserListToUserDTOList(users);
    }

    @Override
    public List<UserDTO> getActiveUsers(Boolean isActive) {
        List<User> users = userRepository.findAllByIsActive(isActive);
        return this.convertUserListToUserDTOList(users);
    }

    @Override
    public List<UserDTO> getUsersByRegionalId(Short regionalId) {
        List<User> users = userRepository.findAllByRegionalId(regionalId);
        return this.convertUserListToUserDTOList(users);
    }

    @Override
    public UserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return null;
        }

        return this.convertUserToUserDTO(user.get());
    }

    @Override
    public UserDTO getUserByName(String name) {
        Optional<User> user = userRepository.findByName(name);

        if(user.isEmpty()){
            return null;
        }

        return this.convertUserToUserDTO(user.get());
    }

    @Override
    public UserDTO getUserByDni(String dni) {
        Optional<User> user = userRepository.findByDni(dni);

        if(user.isEmpty()){
            return null;
        }

        return this.convertUserToUserDTO(user.get());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            return null;
        }

        return this.convertUserToUserDTO(user.get());
    }

    @Override
    public UserDTO getUserByProfessionalCardNumber(String professionalCardNumber) {
        Optional<User> user = userRepository.findByProfessionalCardNumber(professionalCardNumber);

        if(user.isEmpty()){
            return null;
        }

        return this.convertUserToUserDTO(user.get());
    }

    private RequestResponse validateUserStructure(User user){
        RequestResponse response = null;

        if(user.getName().isEmpty() || user.getName() == null){
            response = new RequestResponse(null, false, HttpStatus.BAD_REQUEST.value(), "User name is required");
        }else if(user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getEmail() == null){
            response = new RequestResponse(null, false, HttpStatus.BAD_REQUEST.value(), "User email is required");
        }else if(user.getPassword().isEmpty() || user.getPassword().isBlank() || user.getPassword() == null){
            response = new RequestResponse(null, false, HttpStatus.BAD_REQUEST.value(), "User password is required");
        }else if(user.getIsActive() == null){
            response = new RequestResponse(null, false, HttpStatus.BAD_REQUEST.value(), "isActive boolean value is required");
        } else if (TenantContext.getTenantId() == null) {
            response = new RequestResponse(null, false, HttpStatus.BAD_REQUEST.value(), tenantIdHeaderName + " value is missing from header");
        }

        return response;
    }

    private RequestResponse verifyUserExistence(User user){
        Optional<User> userExistentByDni = userRepository.findByDni(user.getDni());
        Optional<User> userExistentByEmail = userRepository.findByEmail(user.getEmail());
        //Optional<User> userExistsByProfessionalNumber = userRepository.findByProfessionalCardNumber(user.getProfessionalCardNumber());

        if(userExistentByDni.isPresent() || userExistentByEmail.isPresent())
            return new RequestResponse(null, false, HttpStatus.CONFLICT.value(), "User already exists");
        else
            return null;
    }

    @Override
    public RequestResponse registerUser(User user) {
        RequestResponse validateUserRequiredFields = this.validateUserStructure(user);
        if(validateUserRequiredFields != null)
            return validateUserRequiredFields;

        RequestResponse verifyUserExists = this.verifyUserExistence(user);
        if(verifyUserExists != null)
            return verifyUserExists;

        user.setTenantId(Objects.requireNonNull(TenantContext.getTenantId()));
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User userSaved = userRepository.save(user);
        UserDTO userDTO = this.convertUserToUserDTO(userSaved);
        return new RequestResponse(userDTO, true, HttpStatus.OK.value(), "User created");
    }

    @Override
    public RequestResponse updateUser(User user) {
        User userSaved = userRepository.save(user);
        UserDTO userDTO = this.convertUserToUserDTO(userSaved);
        return new RequestResponse(userDTO, true, HttpStatus.OK.value(), "User updated");
    }

    @Override
    public RequestResponse deleteUserById(UUID userId) {
        UserDTO user = this.getUserById(userId);
        RequestResponse response = new RequestResponse(
                null,
                false,
                HttpStatus.NOT_FOUND.value(),
                "El usuario con id: " + userId +" no fue eliminado, porque no fue encontrado"
        );

        if(user != null){
            response.setSuccess(true);
            response.setModel(null);
            response.setHttpStatus(HttpStatus.OK.value());
            response.setMessage("El usuario con id: " + userId + " fue satisfactoriamente eliminado");
        }

        return response;
    }
}
