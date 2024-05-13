package com.lurodev.adminsgestioninspecciones.services;


import com.lurodev.adminsgestioninspecciones.models.Admin;
import com.lurodev.adminsgestioninspecciones.models.Status;
import com.lurodev.adminsgestioninspecciones.models.Tenant;
import com.lurodev.adminsgestioninspecciones.repository.IAdminRepository;
import com.lurodev.adminsgestioninspecciones.globalServices.ExternalApiRequest;
import com.lurodev.adminsgestioninspecciones.globalServices.SecureKeyGenerator;
import com.lurodev.adminsgestioninspecciones.models.EmailModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import static com.lurodev.adminsgestioninspecciones.constants.Constants.*;
import static com.lurodev.adminsgestioninspecciones.globalServices.SecureKeyGenerator.*;

@Service
public class AdminService implements IAdminService{
    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecureKeyGenerator keyGenerator;

    public AdminService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder, SecureKeyGenerator keyGenerator) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }


    @Override
    public Optional<Admin> getAdminByDocument(String dni) {
        return adminRepository.findAdminByDni(dni);
    }

    @Override
    public List<Admin> getAdminsByStatus(Status status) {
        return adminRepository.findAdminByStatus(status);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        Optional<Admin> adminOptByDocument = this.adminRepository.findAdminByDni(admin.getDni());
        Admin adminResponse = null;

        if(adminOptByDocument.isEmpty()){
            try {
                KeyPair keyPair = generateKeyPair();
                PrivateKey privateKey = keyPair.getPrivate();
                PublicKey publicKey = keyPair.getPublic();
                String encodedPrivateKey = encodePrivateKey(privateKey);
                String encodedSecret = generateEncodedSecret();

                String encryptedPrivateKey = passwordEncoder.encode(encodedPrivateKey);
                String encryptedSecret = passwordEncoder.encode(encodedSecret);
                admin.setPrivateKey(encryptedPrivateKey);
                admin.setSecret(encryptedSecret);
                adminResponse = adminRepository.save(admin);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return adminResponse;
    }

    @Override
    public boolean registerAdminRequest(Admin admin) {
        boolean adminExists = true;
        Optional<Admin> adminOptByDocument = this.adminRepository.findAdminByDni(admin.getDni());

        if(adminOptByDocument.isEmpty()){
            admin.setStatus(Status.PENDING_APROVAL);
            adminRepository.save(admin);
            adminExists = false;
        }

        return adminExists;
    }

    @Override
    public Admin approveAdmin(Admin admin){
        admin.setStatus(Status.APPROVED);
        Admin adminUpdated = this.updateAdmin(admin);

        EmailModel emailModel = new EmailModel();
        emailModel.setServer(MAIL_SERVER);
        emailModel.setUsername(COMPANY_MAIL);
        emailModel.setPassword(COMPANYMAIL_PASSWORD);
        emailModel.setRecipient(adminUpdated.getEmail());
        emailModel.setSubject("Aprobación acceso administrador");
        String bodyMail = "<html>" +
                    "<h4>Bienvenid@ " + adminUpdated.getName() + "</h4><br>" +
                    "<p>La solicitud para obtener acceso como administrador ha sido <b>aprobada</b>, para ingresar al portal del administrador, " +
                    "favor dirigirse al login del administrador e ingresar las siguientes credenciales:</p><br>" +
                    "<strong>Private key = <i>" + adminUpdated.getPrivateKey() + "</i></strong><br>" +
                    "<strong>Secret = <i>" + adminUpdated.getSecret() + "</i></strong><br>" +
                "</html>";
        emailModel.setMessage(bodyMail);

        boolean mailSent = this.sendEmail(emailModel);
        if(!mailSent){
            return null;
        }
        return adminUpdated;
    }

    @Override
    public Admin rejectAdmin(Admin admin){
        admin.setStatus(Status.APPROVAL_REJECTED);
        Admin adminUpdated = this.updateAdmin(admin);

        EmailModel emailModel = new EmailModel();
        emailModel.setServer(MAIL_SERVER);
        emailModel.setUsername(COMPANY_MAIL);
        emailModel.setPassword(COMPANYMAIL_PASSWORD);
        emailModel.setRecipient(adminUpdated.getEmail());
        emailModel.setSubject("Rechazo acceso administrador");
        String bodyMail = "<html>" +
                "<h4>Hola " + adminUpdated.getName() + "</h4><br>" +
                "<p>La solicitud para obtener acceso como administrador ha sido <b>rechazada</b>." +
                "</html>";
        emailModel.setMessage(bodyMail);
        boolean mailSent = this.sendEmail(emailModel);

        if(!mailSent){
            return null;
        }

        return adminUpdated;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Admin adminUpdate = null;
        Optional<Admin> adminOptById = adminRepository.findById(admin.getId());

        if(adminOptById.isPresent()){
            adminUpdate = adminOptById.get();
            Field[] adminFields = admin.getClass().getDeclaredFields();

            /* ================================================================================
            *Generación de privatekeys y secret de forma dinamica e independiente
            * ================================================================================*/
            PrivateKey privateKey = null;
            if(admin.getStatus().equals(Status.APPROVED)){
                try {
                    KeyPair keyPair = generateKeyPair();
                    privateKey = keyPair.getPrivate();

                    String securePassword = generateSecurePassword();
                    String encryptedPassword = null;
                    if(admin.getPassword() == null){
                        encryptedPassword = passwordEncoder.encode(securePassword);
                    }
                    encryptedPassword = passwordEncoder.encode(securePassword);
                    adminUpdate.setPassword(encryptedPassword);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                adminUpdate.setPassword(null);
            }
            /* ================================================================================*/

            for(Field field: adminFields){
                try {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(admin);

                    if(value != null || fieldName.equals("privateKey") || fieldName.equals("secret")){
                        switch (fieldName) {
                            case "nombres"-> {
                                adminUpdate.setName(value.toString());
                                break;
                            }
                            case "apellidos"-> {
                                adminUpdate.setLastname(value.toString());
                                break;
                            }
                            case "telefono"-> {
                                adminUpdate.setPhone(value.toString());
                                break;
                            }
                            case "privateKey"-> {
                                if(admin.getStatus().equals(Status.APPROVED) && privateKey != null){
                                    String encodedPrivateKey = encodePrivateKey(privateKey);
                                    String encryptedPrivateKey = passwordEncoder.encode(encodedPrivateKey);
                                    adminUpdate.setPrivateKey(encryptedPrivateKey);
                                }
                                else{
                                    adminUpdate.setPrivateKey(null);
                                }
                                break;
                            }
                            case "secret"-> {
                                if(admin.getStatus().equals(Status.APPROVED)) {
                                    String encodedSecret = generateEncodedSecret();
                                    String encryptedSecret = passwordEncoder.encode(encodedSecret);
                                    adminUpdate.setSecret(encryptedSecret);
                                }
                                else{
                                    adminUpdate.setSecret(null);
                                }
                                break;
                            }
                            case "tenantId"-> {
                                adminUpdate.setTenantId((Short) value);
                                break;
                            }
                            case "status"-> {
                                adminUpdate.setStatus((Status) value);
                                break;
                            }
                            default -> {
                                break;
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            adminUpdate = adminRepository.save(adminUpdate);
        }

        return adminUpdate;
    }

    @Override
    public boolean deleteAdminById(Long id) {
        adminRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean authorizeAdmin(Admin adminInfo) {
        Optional<Admin> optAdminDB = adminRepository.findAdminByDni(adminInfo.getDni());
        boolean authState = false;
        if(optAdminDB.isPresent()){
            Admin adminDB = optAdminDB.get();
            if(
                adminDB.getStatus().equals(Status.APPROVED) &&
                adminDB.getDni().equals(adminInfo.getDni()) &&
                adminDB.getEmail().equals(adminInfo.getEmail()) &&
                //passwordEncoder.matches(adminInfo.getPassword(), adminDB.getPassword()) &&
                adminDB.getPrivateKey().equals(adminInfo.getPrivateKey()) &&
                adminDB.getSecret().equals(adminInfo.getSecret())
            ){
                authState = true;
            }
        }
        return authState;
    }

    private boolean sendEmail(EmailModel email){
        boolean success = false;

        String jsonEmail = new ExternalApiRequest().convertObjectToJson(email);
        Object emailResponse = new ExternalApiRequest().postDataToApi(API_EMAIL, jsonEmail);

        if(emailResponse != null){
            success = true;
        }

        return success;
    }
}
