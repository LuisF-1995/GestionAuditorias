package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Inspector;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.Inspector.InterfaceInspectorService;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inspectores")
public class InspectorController {
    private InterfaceInspectorService inspectorService;

    public InspectorController(InterfaceInspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @GetMapping
    public ResponseEntity<List<Inspector>> getListInspectores() {
        return ResponseEntity.ok(inspectorService.getAllInspectores());
    }

    @GetMapping("/id/{id-inspector}")
    public ResponseEntity<Inspector> getInspectorById(@PathVariable("id-inspector") Long id){
        Optional<Inspector> inspectorBuscado= inspectorService.getInspectorById(id);
        if(inspectorBuscado.isPresent()){
            return ResponseEntity.ok(inspectorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroDocumento/{documento-inspector}")
    public ResponseEntity<Inspector> getInspectorByNumeroDocumento(@PathVariable("documento-inspector") Long numeroDocumento){
        Optional<Inspector> inspectorBuscado= inspectorService.getInspectorByNumeroDocumento(numeroDocumento);
        if(inspectorBuscado.isPresent()){
            return ResponseEntity.ok(inspectorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/matriculaProfesional/{mp-inspector}")
    public ResponseEntity<Inspector> getInspectorByMatriculaProfesional(@PathVariable("mp-inspector") String matriculaProfesional){
        Optional<Inspector> inspectorBuscado= inspectorService.getInspectorByMatriculaProfesional(matriculaProfesional);
        if(inspectorBuscado.isPresent()){
            return ResponseEntity.ok(inspectorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombres/{nombres-inspector}")
    public ResponseEntity<Inspector> getInspectorByNombres(@PathVariable("nombres-inspector") String nombres){
        Optional<Inspector> inspectorBuscado= inspectorService.getInspectorByNombres(nombres);
        if(inspectorBuscado.isPresent()){
            return ResponseEntity.ok(inspectorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/apellidos/{apellidos-inspector}")
    public ResponseEntity<Inspector> getInspectorByApellidos(@PathVariable("apellidos-inspector") String apellidos){
        Optional<Inspector> inspectorBuscado= inspectorService.getInspectorByApellidos(apellidos);
        if(inspectorBuscado.isPresent()){
            return ResponseEntity.ok(inspectorBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Inspector> postNewInspector(@RequestBody Inspector inspector){
        return ResponseEntity.ok(inspectorService.postInspector(inspector));
    }

    @PutMapping("/update")
    public ResponseEntity<Inspector> updateInspector(@RequestBody Inspector inspector){
        Optional<Inspector> inspectorActualizar= inspectorService.getInspectorById(inspector.getId());
        if(inspectorActualizar.isPresent()){
            return ResponseEntity.ok(inspectorService.updateInspector(inspector));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-inspector}")
    public ResponseEntity<String> deleteInspectorById(@PathVariable("id-inspector") Long idInspector) throws ResourceNotFoundException {
        Optional<Inspector> inspectorDelete= inspectorService.getInspectorById(idInspector);
        if (inspectorDelete.isPresent()){
            inspectorService.deleteInspector(idInspector);
            return ResponseEntity.ok("El inspector con ID="+ idInspector + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el inspector con " +
                    "id="+idInspector+". Error al ingresar el ID");
        }
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return inspectorService.authenticateInspector(authCredentials);
    }

}
