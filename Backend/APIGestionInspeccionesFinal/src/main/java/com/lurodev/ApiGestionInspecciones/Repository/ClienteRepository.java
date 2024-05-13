package com.lurodev.ApiGestionInspecciones.Repository;


import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findClienteByNombre(String nombre);
    Optional<Cliente> findClienteByEmail(String email);
}
