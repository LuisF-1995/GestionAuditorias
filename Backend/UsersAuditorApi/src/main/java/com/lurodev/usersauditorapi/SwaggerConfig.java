package com.lurodev.usersauditorapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Luis Fernando Rodriguez Ortiz",
                        email = "luis-1995@live.com.ar"
                ),
                description = "Microservicio para administrar usuarios de Auditor (Gestion inspecciones), seguridad de endpoints, creación y validación de JWT token ",
                title = "Auditor users API",
                version = "1.0",
                termsOfService = "Programa creado por Luis Rodriguez, cualquier uso deberá ser autorizado por el desarrollador"
        ),
        servers = {
                @Server(
                        description = "Local Dev ENV",
                        url = "http://localhost:1901/api/v1/auditor"
                ),
                @Server(
                        description = "Production ENV",
                        url = "http://server-ip:1901/api/v1/auditor"
                )
        }
)
public class SwaggerConfig {
}
