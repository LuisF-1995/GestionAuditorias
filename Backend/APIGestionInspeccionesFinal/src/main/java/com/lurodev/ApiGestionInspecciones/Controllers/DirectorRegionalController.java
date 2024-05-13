package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorRegional;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.DirectorRegional.IDirectorRegionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directores-regional")
public class DirectorRegionalController {
    private final IDirectorRegionalService directorRegionalService;

    public DirectorRegionalController(IDirectorRegionalService directorRegionalService) {
        this.directorRegionalService = directorRegionalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DirectorRegional>> getListDirectoresRegional() {
        return ResponseEntity.ok(directorRegionalService.getAllDirectoresRegional());
    }

    @GetMapping("/id/{id-director-regional}")
    public ResponseEntity<DirectorRegional> getDirectorRegionalById(@PathVariable("id-director-regional") Long id){
        Optional<DirectorRegional> directorRegionalBuscado= directorRegionalService.getDirectorRegionalById(id);
        if(directorRegionalBuscado.isPresent()){
            return ResponseEntity.ok(directorRegionalBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombres/{nombres-directorRegional}")
    public ResponseEntity<DirectorRegional> getDirectorRegionalByNames(@PathVariable("nombres-directorRegional") String nombreDirector){
        Optional<DirectorRegional> directorRegionalBuscado= directorRegionalService.getDirectorRegionalByNombres(nombreDirector);
        if(directorRegionalBuscado.isPresent()){
            return ResponseEntity.ok(directorRegionalBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/apellidos/{apellidos-directorRegional}")
    public ResponseEntity<DirectorRegional> getDirectorRegionalByApellidos(@PathVariable("apellidos-directorRegional") String apellidos){
        Optional<DirectorRegional> directorRegionalBuscado= directorRegionalService.getDirectorRegionalByApellidos(apellidos);
        if(directorRegionalBuscado.isPresent()){
            return ResponseEntity.ok(directorRegionalBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroDocumento/{documento-directorRegional}")
    public ResponseEntity<DirectorRegional> getDirectorRegionalByDocumentNumber(@PathVariable("documento-directorRegional") Long numeroDocumento){
        Optional<DirectorRegional> directorRegionalBuscado= directorRegionalService.getDirectorRegionalByNumeroDocumento(numeroDocumento);
        if(directorRegionalBuscado.isPresent()){
            return ResponseEntity.ok(directorRegionalBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DirectorRegional> postNewDirectorRegional(@RequestBody DirectorRegional directorRegional){
        return ResponseEntity.ok(directorRegionalService.postDirectorRegional(directorRegional));
    }

    @PutMapping("/update")
    public ResponseEntity<DirectorRegional> updateDirectorRegional(@RequestBody DirectorRegional directorRegional){
        Optional<DirectorRegional> directorRegionalUpdate= directorRegionalService.getDirectorRegionalById(directorRegional.getId());
        if(directorRegionalUpdate.isPresent()){
            return ResponseEntity.ok(directorRegionalService.updateDirectorRegional(directorRegional));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-director-regional}")
    public ResponseEntity<String> deleteDirectorRegionalById(@PathVariable("id-director-regional") Long idDirectorRegional) throws ResourceNotFoundException {
        Optional<DirectorRegional> directorRegionalDelete= directorRegionalService.getDirectorRegionalById(idDirectorRegional);
        if (directorRegionalDelete.isPresent()){
            directorRegionalService.deleteDirectorRegionalById(idDirectorRegional);
            return ResponseEntity.ok("El director de regional con ID="+ idDirectorRegional + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el director de regional con " +
                    "id="+idDirectorRegional+". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return directorRegionalService.authenticateDirectorRegional(authCredentials);
    }
}
