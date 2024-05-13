package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import com.lurodev.ApiGestionInspecciones.Entities.admin.AdminStatus;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
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

    @GetMapping("/user-roles")
    public ResponseEntity<UserRoles[]> getListUserRoles(){
        return ResponseEntity.ok(UserRoles.values());
    }
}
