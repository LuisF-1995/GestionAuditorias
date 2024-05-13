package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.Cliente.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAllClients() {
        return ResponseEntity.ok(clienteService.getAllClients());
    }

    @GetMapping("/id/{id-cliente}")
    public ResponseEntity<Cliente> getClientById(@PathVariable("id-cliente") Long id){
        Optional<Cliente> clienteBuscado= clienteService.getClientById(id);
        if(clienteBuscado.isPresent()){
            return ResponseEntity.ok(clienteBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombre/{nombre-cliente}")
    public ResponseEntity<Cliente> getClientByName(@PathVariable("nombre-cliente") String nombre){
        Optional<Cliente> clienteBuscado= clienteService.getClientByName(nombre);
        if(clienteBuscado.isPresent()){
            return ResponseEntity.ok(clienteBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/getByEmail")
    public ResponseEntity<Cliente> getClientByEmail(@RequestBody Cliente cliente){
        Optional<Cliente> clienteBuscado= clienteService.getClientByEmail(cliente.getEmail());
        if(clienteBuscado.isPresent()){
            return ResponseEntity.ok(clienteBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/verifyByEmail")
    public ResponseEntity<Boolean> verifyClientByEmail(@RequestBody Cliente cliente){
        Optional<Cliente> clienteBuscado= clienteService.getClientByEmail(cliente.getEmail());
        if(clienteBuscado.isPresent()){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerNewClient(@RequestBody Cliente client){
        return ResponseEntity.ok(clienteService.postClient(client));
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> updateClient(@RequestBody Cliente cliente){
        Optional<Cliente> clienteActualizar= clienteService.getClientById(cliente.getID());
        if(clienteActualizar.isPresent()){
            return ResponseEntity.ok(clienteService.updateClient(cliente));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-cliente}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id-cliente") Long idCliente) throws ResourceNotFoundException {
        Optional<Cliente> clienteEliminar= clienteService.getClientById(idCliente);
        if (clienteEliminar.isPresent()){
            clienteService.deleteClient(idCliente);
            return ResponseEntity.ok("Se eliminó al cliente con ID="+idCliente);
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró al cliente con " +
                    "id="+idCliente+". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return clienteService.authenticateClient(authCredentials);
    }
}
