package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.AsesorComercial;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.AsesorComercial.IAsesorComercial;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asesores-comerciales")
public class AsesorComercialController {
    private IAsesorComercial asesorComercialService;

    public AsesorComercialController(IAsesorComercial asesorComercialService) {
        this.asesorComercialService = asesorComercialService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AsesorComercial>> getListAsesoresComerciales() {
        return ResponseEntity.ok(asesorComercialService.getAllAsesoresComerciales());
    }

    @GetMapping("/id/{id-asesorcomercial}")
    public ResponseEntity<AsesorComercial> getAsesorComercialById(@PathVariable("id-asesorcomercial") Long id){
        Optional<AsesorComercial> asesorComercialBuscado= asesorComercialService.getAsesorComercialById(id);
        if(asesorComercialBuscado.isPresent()){
            return ResponseEntity.ok(asesorComercialBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AsesorComercial> postNewAsesorComercial(@RequestBody AsesorComercial asesorComercial){
        return ResponseEntity.ok(asesorComercialService.postAsesorComercial(asesorComercial));
    }

    @PutMapping("/update")
    public ResponseEntity<AsesorComercial> updateAsesorComercial(@RequestBody AsesorComercial asesorComercial){
        Optional<AsesorComercial> asesorComercialUpdate= asesorComercialService.getAsesorComercialById(asesorComercial.getId());
        if(asesorComercialUpdate.isPresent()){
            return ResponseEntity.ok(asesorComercialService.updateAsesorComercial(asesorComercial));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-asesorcomercial}")
    public ResponseEntity<String> deleteAsesorComercialById(@PathVariable("id-asesorcomercial") Long idAsesorComercial) throws ResourceNotFoundException {
        Optional<AsesorComercial> asesorComercialDelete= asesorComercialService.getAsesorComercialById(idAsesorComercial);
        if (asesorComercialDelete.isPresent()){
            asesorComercialService.deleteAsesorComercialById(idAsesorComercial);
            return ResponseEntity.ok("El asesor comercial con ID="+ idAsesorComercial + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el asesor comercial con " +
                    "id="+idAsesorComercial+". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return asesorComercialService.authenticateAsesorComercial(authCredentials);
    }
}
