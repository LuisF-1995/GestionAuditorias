package com.lurodev.ApiGestionInspecciones.SecurityConfig;

import com.lurodev.ApiGestionInspecciones.Entities.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https
                .csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user-roles").permitAll()

                // APARTADO DE PERMISOS DE ACCESO ADMINISTRADOR
                // ADMINISTRACION ASESORES COMERCIALES
                .requestMatchers(HttpMethod.POST,"/asesores-comerciales/register").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/asesores-comerciales/delete/{id-asesorcomercial}").hasAuthority(UserRoles.ADMIN.name())
                // ADMINISTRACION PROGRAMDORES DE AGENDA
                .requestMatchers(HttpMethod.POST,"/programador-agenda/register").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/programador-agenda/delete/{idProgramadorAgenda}").hasAuthority(UserRoles.ADMIN.name())
                // ADMINISTRACION DIRECTORES DE REGIONAL
                .requestMatchers(HttpMethod.POST,"/directores-regional/register").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/directores-regional/delete/{id-director-regional}").hasAuthority(UserRoles.ADMIN.name())
                // ADMINISTRACION DIRECTORES TECNICOS
                .requestMatchers(HttpMethod.POST,"/directores-tecnicos/register").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/directores-tecnicos/delete/{id-director-tecnico}").hasAuthority(UserRoles.ADMIN.name())
                // ADMINISTRACION INSPECTORES
                .requestMatchers(HttpMethod.POST,"/inspectores/register").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/inspectores/delete/{id-inspector}").hasAuthority(UserRoles.ADMIN.name())

                // APARTADO DE PERMISOS DE ACCESO PARA ADMINS
                .requestMatchers(HttpMethod.GET, "/admin/status-options").permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/user-roles", "/admin/all").authenticated()
                .requestMatchers(HttpMethod.GET, "/admin/id/{adminId}", "/admin/document/{adminDocument}").hasAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.POST,"/admin/register", "/admin/login").permitAll()
                .requestMatchers(HttpMethod.PUT,"/admin/update").permitAll()

                // APARTADO DE PERMISOS DE ACCESO ASESORES COMERCIALES
                .requestMatchers(HttpMethod.POST,"/asesores-comerciales/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/asesores-comerciales/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name())
                .requestMatchers(HttpMethod.GET, "/asesores-comerciales/**")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_REGIONAL.name())

                // APARTADO DE PERMISOS DE ACCESO PROGRAMADORES DE AGENDA
                .requestMatchers(HttpMethod.POST,"/programador-agenda/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/programador-agenda/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.PROGRAMADOR_AGENDA.name())
                .requestMatchers(HttpMethod.GET, "/programador-agenda/**")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.PROGRAMADOR_AGENDA.name(), UserRoles.DIRECTOR_REGIONAL.name())

                //APARTADO DE PERMISOS DE ACCESO CLIENTES
                .requestMatchers(HttpMethod.POST,"/clientes/register", "/clientes/login", "/clientes/verifyByEmail").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes/all")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.INSPECTOR.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(HttpMethod.GET, "/clientes/id/{id-cliente}", "/clientes/nombre/{nombre-cliente}")
                    .hasAnyAuthority(UserRoles.CLIENTE.name(), UserRoles.ADMIN.name(), UserRoles.INSPECTOR.name())
                .requestMatchers(HttpMethod.POST, "/clientes/searchByEmail")
                    .hasAnyAuthority(UserRoles.CLIENTE.name(), UserRoles.ADMIN.name(), UserRoles.INSPECTOR.name())
                .requestMatchers(HttpMethod.PUT, "/clientes/update")
                    .hasAnyAuthority(UserRoles.CLIENTE.name(), UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/clientes/delete/{id-cliente}")
                    .hasAnyAuthority(UserRoles.CLIENTE.name(), UserRoles.ADMIN.name())

                //APARTADO DE PERMISOS DE ACCESO CONSTRUCTORES
                .requestMatchers(HttpMethod.POST,"/constructores/register", "/constructores/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/constructores/all")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.INSPECTOR.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(
                        HttpMethod.GET,
                        "/constructores/id/{id-constructor}", "/constructores/nombres/{nombres-constructor}",
                        "/constructores/apellidos/{apellidos-constructor}", "/constructores/numeroDocumento/{numeroDocumento-constructor}",
                        "/constructores/matriculaProfesional/{matriculaProfesional-constructor}")
                    .hasAnyAuthority(UserRoles.CONSTRUCTOR.name(), UserRoles.ADMIN.name(), UserRoles.INSPECTOR.name())
                .requestMatchers(HttpMethod.PUT, "/constructores/update")
                    .hasAnyAuthority(UserRoles.CONSTRUCTOR.name(), UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/constructores/delete/{id-constructor}")
                    .hasAnyAuthority(UserRoles.CONSTRUCTOR.name(), UserRoles.ADMIN.name())

                //APARTADO DE PERMISOS DE ACCESO DIRECTORES REGIONAL
                .requestMatchers(HttpMethod.POST,"/directores-regional/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/directores-regional/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(HttpMethod.GET, "/directores-regional/**")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_REGIONAL.name())

                //APARTADO DE PERMISOS DE ACCESO DIRECTORES TECNICOS
                .requestMatchers(HttpMethod.POST,"/directores-tecnicos/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/directores-tecnicos/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_TECNICO.name())
                .requestMatchers(HttpMethod.GET, "/directores-tecnicos/**")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_REGIONAL.name(),UserRoles.DIRECTOR_TECNICO.name(), UserRoles.INSPECTOR.name(), UserRoles.CONSTRUCTOR.name())

                // APARTADO DE PERMISOS DE ACCESO INSPECTORES
                .requestMatchers(HttpMethod.POST,"/inspectores/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/inspectores/update")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/inspectores/**")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(), UserRoles.DIRECTOR_TECNICO.name(),
                            UserRoles.DIRECTOR_REGIONAL.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.PROGRAMADOR_AGENDA.name())


                // PERMISOS PARA ACTAS DE INSPECCION
                .requestMatchers(HttpMethod.POST, "/actas-inspeccion/new").hasAuthority(UserRoles.INSPECTOR.name())
                .requestMatchers(HttpMethod.PUT, "/actas-inspeccion/update")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(), UserRoles.CONSTRUCTOR.name())
                .requestMatchers(HttpMethod.GET, "/actas-inspeccion")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(),
                            UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_TECNICO.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(
                        HttpMethod.GET,
                        "/actas-inspeccion/cedulafirmante/{cedulaFirmante}", "/actas-inspeccion/id/{id-actaInspeccion}",
                        "/actas-inspeccion/ciudad/{ciudadInspeccion}", "/actas-inspeccion/fecha/{fechaInspeccion}",
                        "/actas-inspeccion/empresafirmante/{empresaFirmante}", "/actas-inspeccion/nombresfirmante/{nombresFirmante}",
                        "/actas-inspeccion/cedulafirmante/{cedulaFirmante}")
                    .authenticated()
                .requestMatchers(HttpMethod.DELETE, "/actas-inspeccion/delete/{id-actaInspeccion}").hasAuthority(UserRoles.ADMIN.name())

                // PERMISOS PARA CALENDARIO DE PROYECTOS o AGENDA
                .requestMatchers(HttpMethod.POST, "/agenda/add")
                    .hasAnyAuthority(UserRoles.ASESOR_COMERCIAL.name(), UserRoles.ADMIN.name(),
                                     UserRoles.DIRECTOR_REGIONAL.name(), UserRoles.PROGRAMADOR_AGENDA.name())
                .requestMatchers(HttpMethod.PUT, "/agenda/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name(),
                                     UserRoles.ASESOR_COMERCIAL.name(), UserRoles.PROGRAMADOR_AGENDA.name())
                .requestMatchers(HttpMethod.GET, "/agenda/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/agenda/delete/{idAgenda}")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name(), UserRoles.PROGRAMADOR_AGENDA.name())

                // PERMISOS PARA COMPETENCIAS
                .requestMatchers(HttpMethod.POST, "/competencias/add")
                    .hasAnyAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/competencias/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/competencias/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/competencias/delete/{id-competencia}")
                    .hasAnyAuthority(UserRoles.ADMIN.name())

                // PERMISOS PARA NO CONFORMIDADES
                .requestMatchers(HttpMethod.POST, "/no-conformidades/add").hasAuthority(UserRoles.INSPECTOR.name())
                .requestMatchers(HttpMethod.PUT, "/no-conformidades/update")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(), UserRoles.CONSTRUCTOR.name())
                .requestMatchers(HttpMethod.GET, "/no-conformidades")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(),
                            UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_TECNICO.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(
                        HttpMethod.GET,
                        "/no-conformidades/descripcion/{descripcionNoConformidad}", "/no-conformidades/id/{idNoConformidad}",
                        "/no-conformidades/articulo-norma/{articuloNormativo}", "/no-conformidades/populares")
                    .authenticated()
                .requestMatchers(HttpMethod.DELETE, "/no-conformidades/delete/{id-noConformidad}").hasAuthority(UserRoles.ADMIN.name())

                // PERMISOS PARA PROYECTOS
                .requestMatchers(HttpMethod.POST, "/proyectos/new")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name(), UserRoles.ASESOR_COMERCIAL.name())
                .requestMatchers(HttpMethod.PUT, "/proyectos/update")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name(), UserRoles.ASESOR_COMERCIAL.name())
                .requestMatchers(HttpMethod.GET, "/proyectos", "/proyectos/preview")
                    .hasAnyAuthority(UserRoles.INSPECTOR.name(), UserRoles.ADMIN.name(),
                        UserRoles.ASESOR_COMERCIAL.name(), UserRoles.DIRECTOR_TECNICO.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(
                        HttpMethod.GET,
                        "/proyectos/id/{id-proyecto}", "/proyectos/numeroProforma/{proforma-proyecto}",
                        "/proyectos/nombre/{nombre-proyecto}", "/proyectos/numeroCotizacion/{numeroCotizacion-proyecto}",
                        "/proyectos/numeroInspeccion/{numeroInspeccion-proyecto}", "/proyectos/estado/{estado-proyecto}")
                    .authenticated()
                .requestMatchers(HttpMethod.DELETE, "/proyectos/delete/{id-proyecto}")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.ASESOR_COMERCIAL.name())

                // PERMISOS PARA REGIONALES
                .requestMatchers(HttpMethod.POST, "/regionales/create")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(HttpMethod.PUT, "/regionales/update")
                    .hasAnyAuthority(UserRoles.ADMIN.name(), UserRoles.DIRECTOR_REGIONAL.name())
                .requestMatchers(HttpMethod.GET, "/regionales/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/regionales/delete/{id-regional}")
                    .hasAnyAuthority(UserRoles.ADMIN.name())

                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .clearAuthentication(true).deleteCookies("Authorization")
                .and()
                .httpBasic();

        return https.build();
    }

}
