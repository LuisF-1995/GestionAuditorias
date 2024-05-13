package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Proyecto;
import com.lurodev.ApiGestionInspecciones.Entities.dto.ProjectPreview;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.Proyecto.IProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    private IProyectoService proyectoService;

    public ProyectoController(IProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> getProyectos() {
        return ResponseEntity.ok(proyectoService.getAllProyectos());
    }

    @GetMapping("/preview")
    public ResponseEntity<List<ProjectPreview>> getProjectsPreview() {
        return ResponseEntity.ok(proyectoService.getAllProjectPreview());
    }

    @GetMapping("/id/{id-proyecto}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable("id-proyecto") Long id){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByID(id);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroProforma/{proforma-proyecto}")
    public ResponseEntity<Proyecto> getProyectoByNumeroProforma(@PathVariable("proforma-proyecto") String numeroProforma){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByNumeroProforma(numeroProforma);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/nombre/{nombre-proyecto}")
    public ResponseEntity<Proyecto> getProyectoByNombre(@PathVariable("nombre-proyecto") String nombre){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByNombreProyecto(nombre);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroCotizacion/{numeroCotizacion-proyecto}")
    public ResponseEntity<Proyecto> getProyectoByNumeroCotizacion(@PathVariable("numeroCotizacion-proyecto") String numeroCotizacion){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByNumeroCotizacion(numeroCotizacion);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/numeroInspeccion/{numeroInspeccion-proyecto}")
    public ResponseEntity<Proyecto> getProyectoByNumeroInspeccion(@PathVariable("numeroInspeccion-proyecto") String numeroInspeccion){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByNumeroInspeccion(numeroInspeccion);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/estado/{estado-proyecto}")
    public ResponseEntity<Proyecto> getProyectoByEstadoProyecto(@PathVariable("estado-proyecto") String estado){
        Optional<Proyecto> proyectoBuscada= proyectoService.getProyectoByEstadoProyecto(estado);
        if(proyectoBuscada.isPresent()){
            return ResponseEntity.ok(proyectoBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Proyecto> postNewProyecto(@RequestBody Proyecto proyecto){
        return ResponseEntity.ok(proyectoService.postProyecto(proyecto));
    }

    @PutMapping("/update")
    public ResponseEntity<Proyecto> updateProyecto(@RequestBody Proyecto proyecto){
        Optional<Proyecto> proyectoActualizar= proyectoService.getProyectoByID(proyecto.getId());
        if(proyectoActualizar.isPresent()){
            return ResponseEntity.ok(proyectoService.updateProyecto(proyecto));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-proyecto}")
    public ResponseEntity<String> deleteProyectoById(@PathVariable("id-proyecto") Long idProyecto) throws ResourceNotFoundException {
        Optional<Proyecto> proyectoDelete= proyectoService.getProyectoByID(idProyecto);
        if (proyectoDelete.isPresent()){
            proyectoService.deleteProyecto(idProyecto);
            return ResponseEntity.ok("El proyecto con ID="+ idProyecto + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el proyecto con " +
                    "id=" + idProyecto + ". Error al ingresar el ID");
        }
    }

}
