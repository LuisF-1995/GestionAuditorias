package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.DirectorTecnico;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.DirectorTecnico.IDirectorTecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/directores-tecnicos")
public class DirectorTecnicoController {
    private final IDirectorTecnicoService directorTecnicoService;

    public DirectorTecnicoController(IDirectorTecnicoService directorTecnicoService) {
        this.directorTecnicoService = directorTecnicoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DirectorTecnico>> getListDirectoresTecnicos() {
        return ResponseEntity.ok(directorTecnicoService.getAllDirectoresTecnicos());
    }

    @GetMapping("/id/{id-director-tecnico}")
    public ResponseEntity<DirectorTecnico> getDirectorTecnicoById(@PathVariable("id-director-tecnico") Short id){
        Optional<DirectorTecnico> directorTecnicoBuscado= directorTecnicoService.getDirectorTecnicoById(id);
        if(directorTecnicoBuscado.isPresent()){
            return ResponseEntity.ok(directorTecnicoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroDocumento/{documento-director-tecnico}")
    public ResponseEntity<DirectorTecnico> getDirectorTecnicoByNumeroDocumento(@PathVariable("documento-director-tecnico") Long numeroDocumento){
        Optional<DirectorTecnico> directorTecnicoBuscado= directorTecnicoService.getDirectorTecnicoByNumeroDocumento(numeroDocumento);
        if(directorTecnicoBuscado.isPresent()){
            return ResponseEntity.ok(directorTecnicoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/matriculaProfesional/{matricula-director-tecnico}")
    public ResponseEntity<DirectorTecnico> getDirectorTecnicoByMatriculaProfesional(@PathVariable("matricula-director-tecnico") String matriculaProfesional){
        Optional<DirectorTecnico> directorTecnicoBuscado= directorTecnicoService.getDirectorTecnicoByMatriculaProfesional(matriculaProfesional);
        if(directorTecnicoBuscado.isPresent()){
            return ResponseEntity.ok(directorTecnicoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DirectorTecnico> postNewDirectorTecnico(@RequestBody DirectorTecnico directorTecnico){
        try{
            return ResponseEntity.ok(directorTecnicoService.postDirectorTecnico(directorTecnico));
        }
        catch (Exception e){
            return (ResponseEntity<DirectorTecnico>) ResponseEntity.badRequest();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DirectorTecnico> updateDirectorTecnico(@RequestBody DirectorTecnico directorTecnico){
        Optional<DirectorTecnico> directorTecnicoUpdate= directorTecnicoService.getDirectorTecnicoById(directorTecnico.getId());
        if(directorTecnicoUpdate.isPresent()){
            return ResponseEntity.ok(directorTecnicoService.updateDirectorTecnico(directorTecnico));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-director-tecnico}")
    public ResponseEntity<String> deleteDirectorTecnicoById(@PathVariable("id-director-tecnico") Short idDirectorTecnico) throws ResourceNotFoundException {
        Optional<DirectorTecnico> directorTecnicoDelete= directorTecnicoService.getDirectorTecnicoById(idDirectorTecnico);
        if (directorTecnicoDelete.isPresent()){
            directorTecnicoService.deleteDirectorTecnico(idDirectorTecnico);
            return ResponseEntity.ok("El director tecnico con ID="+ idDirectorTecnico + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el director tecnico con " +
                    "id="+idDirectorTecnico+". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return directorTecnicoService.authenticateDirectorTecnico(authCredentials);
    }
}
