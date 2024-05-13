package com.lurodev.adminsgestioninspecciones.controllers;

import com.lurodev.adminsgestioninspecciones.models.Tenant;
import com.lurodev.adminsgestioninspecciones.services.IAdminService;
import com.lurodev.adminsgestioninspecciones.services.ITenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Root {
    private final ITenantService tenantService;

    public Root(ITenantService tenantService) {
        this.tenantService = tenantService;
    }
    @GetMapping
    public ResponseEntity<String> rootHome(){
        String home = "<!DOCTYPE html> " +
                "<html lang=\\es\\>" +
                "<head>" +
                "    <title>Gestion inspecciones</title>" +
                "    <meta charset=\\utf-8\\>" +
                "    <meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\">" +
                "    <link rel=\\\"stylesheet\\\" href=\\\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\\\">" +
                "    <script src=\\\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\\\"></script>" +
                "    <script src=\\\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\\\"></script>" +
                "    <script src=\\\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\\\"></script>" +
                "</head>" +
                "<body> " +
                "    <h1>API para gestión de inspecciones con fines de certificación</h1>" +
                "    <p>Para poder hacer uso de la API como cliente, o contructor se requiere hacer el registro por medio del siguiente endpoint:</p>" +
                "    <div>" +
                "<h4>Si es cliente:</h4>" +
                "<ol>" +
                "<li>Registro clientes: /clientes/register</li>" +
                "<li>Inicio sesion: /clientes/login" +
                "</ol>" +
                "    </div>" +
                "    <div>" +
                "       <h4>Si es constructor:</h4>" +
                "       <ol>" +
                "           <li>Registro constructor: /constructores/register</li>" +
                "           <li>Inicio sesion: /constructores/login" +
                "       </ol>" +
                "    </div>" +
                "    <p>Los demás usuarios serán gestionados por medio de un administrador, dentro de la compañia.</p>" +
                "</body>" +
                "</html>";

        return ResponseEntity.ok(home);
    }

    @GetMapping("api/gestion-inspecciones/getCompanies")
    public ResponseEntity<List<Tenant>> getListedCompanies(){
        return ResponseEntity.ok(tenantService.getApprovedCompanies());
    }
}
