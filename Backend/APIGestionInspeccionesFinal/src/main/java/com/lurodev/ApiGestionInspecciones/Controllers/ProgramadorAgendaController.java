package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.ProgramadorAgenda;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthCredentials;
import com.lurodev.ApiGestionInspecciones.SecurityConfig.Login.AuthenticationResponse;
import com.lurodev.ApiGestionInspecciones.Service.programadorAgenda.IProgramadorAgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programador-agenda")
public class ProgramadorAgendaController {
    private final IProgramadorAgendaService programadorAgendaService;

    public ProgramadorAgendaController(IProgramadorAgendaService programadorAgendaService) {
        this.programadorAgendaService = programadorAgendaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProgramadorAgenda>> getListProgramadoresAgenda() {
        return ResponseEntity.ok(programadorAgendaService.getAllProgramadoresAgenda());
    }

    @GetMapping("/id/{idProgramadorAgenda}")
    public ResponseEntity<ProgramadorAgenda> getProgramadorAgendaById(@PathVariable("idProgramadorAgenda") Long id){
        Optional<ProgramadorAgenda> programadorAgenda= programadorAgendaService.getProgramadorAgendaById(id);
        return programadorAgenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/register")
    public ResponseEntity<ProgramadorAgenda> createProgramadorAgenda(@RequestBody ProgramadorAgenda programadorAgenda){
        return ResponseEntity.ok(programadorAgendaService.createProgramadorAgenda(programadorAgenda));
    }

    @PutMapping("/update")
    public ResponseEntity<ProgramadorAgenda> updateProgramadorAgenda(@RequestBody ProgramadorAgenda programadorAgenda){
        Optional<ProgramadorAgenda> programadorAgendaOptional= programadorAgendaService.getProgramadorAgendaById(programadorAgenda.getId());
        if(programadorAgendaOptional.isPresent()){
            return ResponseEntity.ok(programadorAgendaService.updateProgramadorAgenda(programadorAgenda));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{idProgramadorAgenda}")
    public ResponseEntity<String> deleteAsesorComercialById(@PathVariable("idProgramadorAgenda") Long id) throws ResourceNotFoundException {
        Optional<ProgramadorAgenda> programadorAgenda= programadorAgendaService.deleteProgramadorAgendaById(id);
        if (programadorAgenda.isPresent()){
            return ResponseEntity.ok("El programador de agenda con ID="+ id + " fue eliminado con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró el programador con " +
                    "id="+ id +". Error al ingresar el ID");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthCredentials authCredentials){
        return programadorAgendaService.authenticateProgramadorAgenda(authCredentials);
    }
}
