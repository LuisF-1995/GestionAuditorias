package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Cliente;
import com.lurodev.ApiGestionInspecciones.Entities.Regional;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.Regional.IRegionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regionales")
public class RegionalController {
    private IRegionalService regionalService;

    public RegionalController(IRegionalService regionalService) {
        this.regionalService = regionalService;
    }

    @GetMapping
    public ResponseEntity<List<Regional>> getRegionales() {
        return ResponseEntity.ok(regionalService.getAllRegionales());
    }

    @GetMapping("/id/{id-regional}")
    public ResponseEntity<Regional> getRegionalById(@PathVariable("id-regional") Long id){
        Optional<Regional> regionalBuscada= regionalService.getRegionalByID(id);
        if(regionalBuscada.isPresent()){
            return ResponseEntity.ok(regionalBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Regional> postNewRegional(@RequestBody Regional regional){
        return ResponseEntity.ok(regionalService.postRegional(regional));
    }

    @PutMapping("/update")
    public ResponseEntity<Regional> updateRegional(@RequestBody Regional regional){
        Optional<Regional> regionalActualizar= regionalService.getRegionalByID(regional.getId());
        if(regionalActualizar.isPresent()){
            return ResponseEntity.ok(regionalService.updateRegional(regional));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-regional}")
    public ResponseEntity<String> deleteRegionalById(@PathVariable("id-regional") Long idRegional) throws ResourceNotFoundException {
        Optional<Regional> regionalDelete= regionalService.getRegionalByID(idRegional);
        if (regionalDelete.isPresent()){
            regionalService.deleteRegional(idRegional);
            return ResponseEntity.ok("La regional con ID="+idRegional + " fue eliminada con éxito. !!");
        }
        else{
            //estoy en el caso de no encontrar el ID en la base de datos
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró la regional con " +
                    "id="+idRegional+". Error al ingresar el ID");
        }
    }
}
