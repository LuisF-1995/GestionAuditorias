package com.lurodev.ApiGestionInspecciones.Service.Admin;

import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;
import com.lurodev.ApiGestionInspecciones.Repository.IAdminRepository;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.JwtService;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.UserDetailsImpl;
import com.lurodev.ApiGestionInspecciones.constants.Constants;
import com.lurodev.ApiGestionInspecciones.globalService.ExternalApiRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class AdminService implements IAdminService {
    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Constants constants = new Constants();

    public AdminService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Short id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public Optional<Admin> getAdminByDocument(Long documentNumber) {
        return adminRepository.findAdminByNumeroDocumento(documentNumber);
    }

    @Override
    public RegistrationResponse registerAdmin(Admin admin){
        RegistrationResponse response = new RegistrationResponse();
        Optional<Admin> adminByDocumentNumber = adminRepository.findAdminByNumeroDocumento(admin.getNumeroDocumento());
        Optional<Admin> adminByEmail = adminRepository.findAdminByEmail(admin.getEmail());

        if(adminByDocumentNumber.isEmpty() && adminByEmail.isEmpty()){
            /* ==========> Validacion previa en URI/api/gestion-inspecciones/admin/register <======================*/
            String validateAdminEndpoint = constants.getApiAdminBaseUrl() + "/register";
            String jsonAdmin = new ExternalApiRequest().convertObjectToJson(admin);
            boolean adminExists = (boolean) new ExternalApiRequest().postDataToApi(validateAdminEndpoint, jsonAdmin);
            /* ==================================================================================================== */

            String encryptedPassword = null;
            if(admin.getPassword() != null){
                encryptedPassword = passwordEncoder.encode(admin.getPassword());
                admin.setPassword(encryptedPassword);
            }
            admin.setStatus(AdminStatus.PENDING_APROVAL);
            adminRepository.save(admin);

            admin.setPassword(null);
            response.setStatus(AdminStatus.PENDING_APROVAL);
            response.setResponse(admin);
            response.setUserAlreadyExists(adminExists);
        }
        else{
            response.setUserAlreadyExists(true);
            response.setStatus(adminByDocumentNumber.get().getStatus());
        }
        return response;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Admin adminUpdate = null;
        Optional<Admin> adminOpt = Optional.empty();

        if (admin.getId() != null){
            adminOpt = adminRepository.findById(admin.getId());
        }
        else if (admin.getNumeroDocumento() != null) {
            adminOpt = adminRepository.findAdminByNumeroDocumento(admin.getNumeroDocumento());
        }
        else if (admin.getEmail() != null) {
            adminOpt = adminRepository.findAdminByEmail(admin.getEmail());
        }

        if(adminOpt.isPresent()){
            adminUpdate = adminOpt.get();
            Field[] adminFields = admin.getClass().getDeclaredFields();
            for(Field field: adminFields){
                try {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(admin);

                    if(value != null){
                        switch (fieldName) {
                            case "nombres"-> adminUpdate.setNombres(value.toString());
                            case "apellidos"-> adminUpdate.setApellidos(value.toString());
                            case "password"-> {
                                String encryptedPassword = passwordEncoder.encode(value.toString());
                                adminUpdate.setPassword(encryptedPassword);
                            }
                            case "telefono"-> adminUpdate.setTelefono(value.toString());
                            case "companyName"-> adminUpdate.setCompanyName(value.toString());
                            case "companyId"-> adminUpdate.setCompanyId(value.toString());
                            case "status"-> adminUpdate.setStatus((AdminStatus) value);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            adminUpdate = adminRepository.save(adminUpdate);
        }

        if (adminUpdate != null) {
            adminUpdate.setPassword(null);
        }

        return adminUpdate;
    }

    @Override
    public boolean deleteAdminById(Short id) {
        boolean deleteStatus = false;
        Optional<Admin> adminOpt = adminRepository.findById(id);

        if(adminOpt.isPresent()){
            adminRepository.deleteById(id);
            deleteStatus = true;
        }

        return deleteStatus;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(AuthCredentials adminCredentials) {
        String email = adminCredentials.getEmail();
        String password = adminCredentials.getPassword();
        AuthenticationResponse authenticationResponse = null;
        Optional<Admin> optLocalAdmin = this.getAdminByDocument(adminCredentials.getNumeroDocumento());

        if(optLocalAdmin.isPresent()){
            Admin localAdmin = optLocalAdmin.get();

            /* ==========> Validación previa en URI/api/gestion-inspecciones/admin/login <======================*/
            String authorizeAdminEndpoint = constants.getApiAdminBaseUrl() + "/login";
            adminCredentials.setCountry(localAdmin.getCountry());
            String jsonAdmin = new ExternalApiRequest().convertObjectToJson(adminCredentials);
            boolean authorizedAdmin = (boolean) new ExternalApiRequest().postDataToApi(authorizeAdminEndpoint, jsonAdmin);
            /* =========================================================================================================== */

            if(authorizedAdmin){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email + USER_ROLE_SEPARATOR + UserRoles.ADMIN, password)
                );

                var admin = adminRepository.findAdminByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Admin no encontrado"));

                Collection<GrantedAuthority> adminAuthority = Collections.singleton(new SimpleGrantedAuthority(admin.getRol().name()));
                UserDetails adminDetails = new UserDetailsImpl(admin.getEmail() + USER_ROLE_SEPARATOR + UserRoles.ADMIN, admin.getPassword(), adminAuthority);

                var jwtToken = jwtService.generateToken(adminDetails);
                admin.setPassword(null);

                authenticationResponse = new AuthenticationResponse(true, admin, jwtToken, "Login exitoso");
            }
            else{
                localAdmin.setPassword(null);
                authenticationResponse = new AuthenticationResponse(false, localAdmin, null, "Administrador no autorizado, favor contactarse con el administrador de la aplicación o validar credenciales");
            }
        }
        else{
            authenticationResponse = new AuthenticationResponse(false, null, null, "El usuario no se encuentra registrado aún, favor registrarse.");
        }

        return ResponseEntity.ok().body(authenticationResponse);
    }
}
