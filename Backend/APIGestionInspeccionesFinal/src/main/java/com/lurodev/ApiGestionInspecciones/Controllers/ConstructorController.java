package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Constructor;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.Constructor.IConstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/constructores")
public class ConstructorController {
    private final IConstructorService constructorService;

    public ConstructorController(IConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Constructor>> getListConstructores() {
        return ResponseEntity.ok(constructorService.getAllConstructores());
    }

    @GetMapping("/id/{id-constructor}")
    public ResponseEntity<Constructor> getConstructorById(@PathVariable("id-constructor") Long id){
        Optional<Constructor> constructorBuscado= constructorService.getConstructorById(id);
        if(constructorBuscado.isPresent()){
            return ResponseEntity.ok(constructorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombres/{nombres-constructor}")
    public ResponseEntity<Constructor> getConstructorByNombres(@PathVariable("nombres-constructor") String nombres){
        Optional<Constructor> constructorBuscado= constructorService.getConstructorByNombres(nombres);
        if(constructorBuscado.isPresent()){
            return ResponseEntity.ok(constructorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/apellidos/{apellidos-constructor}")
    public ResponseEntity<Constructor> getConstructorByApellidos(@PathVariable("apellidos-constructor") String apellidos){
        Optional<Constructor> constructorBuscado= constructorService.getConstructorByApellidos(apellidos);
        if(constructorBuscado.isPresent()){
            return ResponseEntity.ok(constructorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroDocumento/{numeroDocumento-constructor}")
    public ResponseEntity<Constructor> getConstructorByNumeroDocumento(@PathVariable("numeroDocumento-constructor") Long numeroDocumento){
        Optional<Constructor> constructorBuscado= constructorService.getConstructorByNumeroDocumento(numeroDocumento);
        if(constructorBuscado.isPresent()){
            return ResponseEntity.ok(constructorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/matriculaProfesional/{matriculaProfesional-constructor}")
    public ResponseEntity<Constructor> getConstructorByMatriculaProfesional(@PathVariable("matriculaProfesional-constructor") String matriculaProfesional){
        Optional<Constructor> constructorBuscado= constructorService.getConstructorByMatriculaProfesional(matriculaProfesional);
        if(constructorBuscado.isPresent()){
            return ResponseEntity.ok(constructorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> postNewConstructor(@RequestBody Constructor constructor){
        return ResponseEntity.ok(constructorService.postConstructor(constructor));
    }

    @PutMapping("/update")
    public ResponseEntity<Constructor> updateConstructor(@RequestBody Constructor constructor){
        Optional<Constructor> constructorUpdate= constructorService.getConstructorById(constructor.getId());
        if(constructorUpdate.isPresent()){
            return ResponseEntity.ok(constructorService.updateConstructor(constructor));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-constructor}")
    public ResponseEntity<String> deleteConstructorById(@PathVariable("id-constructor") Long idConstructor) throws ResourceNotFoundException {
        Optional<Constructor> constructorDelete= constructorService.getConstructorById(idConstructor);
        if (constructorDelete.isPresent()){
            constructorService.deleteConstructorById(idConstructor);
            return ResponseEntity.ok("El constructor con ID="+ idConstructor + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el constructor con " +
                    "id=" + idConstructor + ". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return constructorService.authenticateConstructor(authCredentials);
    }
}
