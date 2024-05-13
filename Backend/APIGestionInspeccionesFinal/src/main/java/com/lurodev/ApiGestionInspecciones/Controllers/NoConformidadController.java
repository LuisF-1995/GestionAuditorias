package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.NoConformidad;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.NoConformidad.INoConformidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/no-conformidades")
public class NoConformidadController {
    private INoConformidadService noConformidadService;

    public NoConformidadController(INoConformidadService noConformidadService) {
        this.noConformidadService = noConformidadService;
    }

    @GetMapping
    public ResponseEntity<List<NoConformidad>> getListNoConformidades() {
        return ResponseEntity.ok(noConformidadService.getAllNoConformidades());
    }

    @GetMapping("/id/{idNoConformidad}")
    public ResponseEntity<NoConformidad> getNoConformidadById(@PathVariable("idNoConformidad") Long id){
        Optional<NoConformidad> actaInspeccionBuscada= noConformidadService.getNoConformidadById(id);
        if(actaInspeccionBuscada.isPresent()){
            return ResponseEntity.ok(actaInspeccionBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/descripcion/{descripcionNoConformidad}")
    public ResponseEntity<Set<NoConformidad>> getNoConformidadesByDescripcion(@PathVariable("descripcionNoConformidad") String descripcion){
        Optional<Set<NoConformidad>> noConformidadBuscada = noConformidadService.getNoConformidadByDescripcion(descripcion);
        if(noConformidadBuscada.isPresent()){
            return ResponseEntity.ok(noConformidadBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/articulo-norma/{articuloNormativo}")
    public ResponseEntity<Set<NoConformidad>> getNoConformidadByArticuloNorma(@PathVariable("articuloNormativo") String articuloNormativo){
        Optional<Set<NoConformidad>> noConformidadBuscada= noConformidadService.getNoConformidadByArticuloNormativo(articuloNormativo);
        if(noConformidadBuscada.isPresent()){
            return ResponseEntity.ok(noConformidadBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/populares")
    public ResponseEntity<List<NoConformidad>> getNoConformidadesMasPopulares(){
        return ResponseEntity.ok(noConformidadService.getAllNoConformidadesOrderedByOcurrencias());
    }


    @PostMapping("/add")
    public ResponseEntity<NoConformidad> postNewNoConformidad(@RequestBody NoConformidad noConformidad){
        return ResponseEntity.ok(noConformidadService.postNoConformidad(noConformidad));
    }

    @PutMapping("/update")
    public ResponseEntity<NoConformidad> updateNoConformidad(@RequestBody NoConformidad noConformidad){
        Optional<NoConformidad> asesorComercialUpdate= noConformidadService.getNoConformidadById(noConformidad.getId());
        if(asesorComercialUpdate.isPresent()){
            return ResponseEntity.ok(noConformidadService.updateNoConformidad(noConformidad));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-noConformidad}")
    public ResponseEntity<String> deleteNoConformidadById(@PathVariable("id-noConformidad") Long idNoConformidad) throws ResourceNotFoundException {
        Optional<NoConformidad> asesorComercialDelete= noConformidadService.getNoConformidadById(idNoConformidad);
        if (asesorComercialDelete.isPresent()){
            noConformidadService.deleteNoConformidadById(idNoConformidad);
            return ResponseEntity.ok("La no conformidad con ID="+ idNoConformidad + " fue eliminada con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró la no conformidad con " +
                    "id="+idNoConformidad+". Error al ingresar el ID");
        }
    }
}
