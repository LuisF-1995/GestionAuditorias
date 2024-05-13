package com.lurodev.ApiGestionInspecciones.SecurityConfig;

import com.lurodev.ApiGestionInspecciones.Entities.*;
import com.lurodev.ApiGestionInspecciones.Entities.admin.Admin;
import com.lurodev.ApiGestionInspecciones.Repository.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.lurodev.ApiGestionInspecciones.constants.Constants.USER_ROLE_SEPARATOR;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {
    private final AsesorComercialRepository asesorComercialRepository;
    private final ClienteRepository clienteRepository;
    private final IConstructorRepository constructorRepository;
    private final DirectorRegionalRepository directorRegionalRepository;
    private final DirectorTecnicoRepository directorTecnicoRepository;
    private final InspectorRepository inspectorRepository;
    private final IAdminRepository adminRepository;
    private final ProgramadorAgendaRepository programadorAgendaRepository;

    public UsersDetailsServiceImpl(AsesorComercialRepository asesorComercialRepository, ClienteRepository clienteRepository, IConstructorRepository constructorRepository, DirectorRegionalRepository directorRegionalRepository, DirectorTecnicoRepository directorTecnicoRepository, InspectorRepository inspectorRepository, IAdminRepository adminRepository, ProgramadorAgendaRepository programadorAgendaRepository) {
        this.asesorComercialRepository = asesorComercialRepository;
        this.clienteRepository = clienteRepository;
        this.constructorRepository = constructorRepository;
        this.directorRegionalRepository = directorRegionalRepository;
        this.directorTecnicoRepository = directorTecnicoRepository;
        this.inspectorRepository = inspectorRepository;
        this.adminRepository = adminRepository;
        this.programadorAgendaRepository = programadorAgendaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = username.split(USER_ROLE_SEPARATOR)[0];
        String userRol = username.split(USER_ROLE_SEPARATOR)[1];

        if (userRol.equals(UserRoles.ASESOR_COMERCIAL.name())){
            Optional<AsesorComercial> asesorComercialFind = asesorComercialRepository.findAsesorComercialByEmail(email);
            if(asesorComercialFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(asesorComercialFind.get().getRol().name()));
                return new UserDetailsImpl(username, asesorComercialFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.CLIENTE.name())){
            Optional<Cliente> clienteFind = clienteRepository.findClienteByEmail(email);
            if(clienteFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(clienteFind.get().getRol().name()));
                return new UserDetailsImpl(username, clienteFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.CONSTRUCTOR.name())){
            Optional<Constructor> constructorFind = constructorRepository.findConstructorByEmail(email);
            if(constructorFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(constructorFind.get().getRol().name()));
                return new UserDetailsImpl(username, constructorFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.DIRECTOR_REGIONAL.name())){
            Optional<DirectorRegional> directorRegionalFind = directorRegionalRepository.findDirectorRegionalByEmail(email);
            if(directorRegionalFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(directorRegionalFind.get().getRol().name()));
                return new UserDetailsImpl(username, directorRegionalFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.DIRECTOR_TECNICO.name())){
            Optional<DirectorTecnico> directorTecnicoFind = directorTecnicoRepository.findDirectorTecnicoByEmail(email);
            if(directorTecnicoFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(directorTecnicoFind.get().getRol().name()));
                return new UserDetailsImpl(username, directorTecnicoFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.INSPECTOR.name())){
            Optional<Inspector> inspectorFind = inspectorRepository.findInspectorByEmail(email);
            if(inspectorFind.isPresent()){
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(inspectorFind.get().getRol().name()));
                return new UserDetailsImpl(username, inspectorFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.ADMIN.name())){
            Optional<Admin> adminFind = adminRepository.findAdminByEmail(email);
            if(adminFind.isPresent()) {
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(adminFind.get().getRol().name()));
                return new UserDetailsImpl(username, adminFind.get().getPassword(), grantedAuthority);
            }
        }

        else if (userRol.equals(UserRoles.PROGRAMADOR_AGENDA.name())){
            Optional<ProgramadorAgenda> programadorAgendaOpt = programadorAgendaRepository.findProgramadorAgendaByEmail(email);
            if(programadorAgendaOpt.isPresent()){
                Collection<GrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(programadorAgendaOpt.get().getRol().name()));
                return new UserDetailsImpl(username, programadorAgendaOpt.get().getPassword(), grantedAuthority);
            }
        }

        else {
            throw new UsernameNotFoundException("Usuario con username: " + email + " no encontrado !!");
        }

        throw new UsernameNotFoundException("Usuario con username: " + email + " no encontrado !!");
    }
}
