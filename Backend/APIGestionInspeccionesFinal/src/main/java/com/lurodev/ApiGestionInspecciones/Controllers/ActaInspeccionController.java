package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.ActaInspeccion;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.ActaInspeccion.ActaInspeccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/actas-inspeccion")
public class ActaInspeccionController {
    private ActaInspeccionService actaInspeccionService;

    public ActaInspeccionController(ActaInspeccionService actaInspeccionService) {
        this.actaInspeccionService = actaInspeccionService;
    }

    @GetMapping
    public ResponseEntity<List<ActaInspeccion>> getListActasInspeccion() {
        return ResponseEntity.ok(actaInspeccionService.getAllActasInspeccion());
    }

    @GetMapping("/id/{id-actaInspeccion}")
    public ResponseEntity<ActaInspeccion> getActaInspeccionById(@PathVariable("id-actaInspeccion") Long id){
        Optional<ActaInspeccion> actaInspeccionBuscada= actaInspeccionService.getActaInspeccionById(id);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/ciudad/{ciudadInspeccion}")
    public ResponseEntity<Set<ActaInspeccion>> getActasInspeccionByCiudad(@PathVariable("ciudadInspeccion") String ciudad){
        Optional<Set<ActaInspeccion>> actaInspeccionBuscada = actaInspeccionService.getActaInspeccionByCiudad(ciudad);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/fecha/{fechaInspeccion}")
    public ResponseEntity<Set<ActaInspeccion>> getActaInspeccionByFechaInspeccion(@PathVariable("fechaInspeccion") LocalDate fechaInspeccion){
        Optional<Set<ActaInspeccion>> actaInspeccionBuscada= actaInspeccionService.getActaInspeccionByFechaInspeccion(fechaInspeccion);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/empresafirmante/{empresaFirmante}")
    public ResponseEntity<Set<ActaInspeccion>> getActasInspeccionByEmpresaFirmante(@PathVariable("empresaFirmante") String empresa){
        Optional<Set<ActaInspeccion>> actaInspeccionBuscada = actaInspeccionService.getActaInspeccionByEmpresaFirmante(empresa);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombresfirmante/{nombresFirmante}")
    public ResponseEntity<Set<ActaInspeccion>> getActasInspeccionByNombresFirmante(@PathVariable("nombresFirmante") String nombres){
        Optional<Set<ActaInspeccion>> actaInspeccionBuscada = actaInspeccionService.getActaInspeccionByNombresFirmante(nombres);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/cedulafirmante/{cedulaFirmante}")
    public ResponseEntity<Set<ActaInspeccion>> getActasInspeccionByCedulaFirmante(@PathVariable("cedulaFirmante") Long cedula){
        Optional<Set<ActaInspeccion>> actaInspeccionBuscada = actaInspeccionService.getActaInspeccionByCedulaFirmante(cedula);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ActaInspeccion> postNewActaInspeccion(@RequestBody ActaInspeccion actaInspeccion){
        return ResponseEntity.ok(actaInspeccionService.postActaInspeccion(actaInspeccion));
    }

    @PutMapping("/update")
    public ResponseEntity<ActaInspeccion> updateActaInspeccion(@RequestBody ActaInspeccion actaInspeccion){
        Optional<ActaInspeccion> asesorComercialUpdate= actaInspeccionService.getActaInspeccionById(actaInspeccion.getId());
        if(asesorComercialUpdate.isPresent()){
            return ResponseEntity.ok(actaInspeccionService.updateActaInspeccion(actaInspeccion));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-actaInspeccion}")
    public ResponseEntity<String> deleteActaInspeccionById(@PathVariable("id-actaInspeccion") Long idActaInspeccion) throws ResourceNotFoundException {
        Optional<ActaInspeccion> asesorComercialDelete= actaInspeccionService.getActaInspeccionById(idActaInspeccion);
        if (asesorComercialDelete.isPresent()){
            actaInspeccionService.deleteActaInspeccionById(idActaInspeccion);
            return ResponseEntity.ok("El acta con ID="+ idActaInspeccion + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el acta con " +
                    "id="+idActaInspeccion+". Error al ingresar el ID");
        }
    }
}
