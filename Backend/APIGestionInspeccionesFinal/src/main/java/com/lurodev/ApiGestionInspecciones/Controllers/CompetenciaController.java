package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.Competencia;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.Competencia.ICompetenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competencias")
public class CompetenciaController {
    private ICompetenciaService competenciaService;

    public CompetenciaController(ICompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Competencia>> getAllCompetencias() {
        return ResponseEntity.ok(competenciaService.getAllCompetencias());
    }
    @GetMapping("/{id-competencia}")
    public ResponseEntity<Competencia> getCompetenciaById(@PathVariable("id-competencia") Long id){
        Optional<Competencia> competenciaBuscada= competenciaService.getCompetenciaById(id);
        if(competenciaBuscada.isPresent()){
            return ResponseEntity.ok(competenciaBuscada.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Competencia> postNewCompetencia(@RequestBody Competencia competencia){
        return ResponseEntity.ok(competenciaService.postCompetencia(competencia));
    }

    @PutMapping("/update")
    public ResponseEntity<Competencia> updateCompetencia(@RequestBody Competencia competencia){
        Optional<Competencia> competenciaUpdate= competenciaService.getCompetenciaById(competencia.getId());
        if(competenciaUpdate.isPresent()){
            return ResponseEntity.ok(competenciaService.updateCompetencia(competencia));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id-competencia}")
    public ResponseEntity<String> deleteCompetenciaById(@PathVariable("id-competencia") Long idCompetencia) throws ResourceNotFoundException {
        Optional<Competencia> competenciaDelete= competenciaService.getCompetenciaById(idCompetencia);
        if (competenciaDelete.isPresent()){
            competenciaService.deleteCompetencia(idCompetencia);
            return ResponseEntity.ok("La competencia con ID="+idCompetencia + " fue eliminada con éxito. !!");
        }
        else{
            //estoy en el caso de no encontrar el ID en la base de datos
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró la competencia con " +
                    "id="+idCompetencia+". Error al ingresar el ID");
        }
    }
}
