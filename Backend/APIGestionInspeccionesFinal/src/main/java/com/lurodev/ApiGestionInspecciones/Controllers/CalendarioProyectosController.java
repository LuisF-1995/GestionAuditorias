package com.lurodev.ApiGestionInspecciones.Controllers;

import com.lurodev.ApiGestionInspecciones.Entities.CalendarioProyectos;
import com.lurodev.ApiGestionInspecciones.Exceptions.ResourceNotFoundException;
import com.lurodev.ApiGestionInspecciones.Service.Calendario.ICalendarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("agenda")
public class CalendarioProyectosController {
    private ICalendarioService calendarioService;

    public CalendarioProyectosController(ICalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }

    @GetMapping
    public ResponseEntity<List<CalendarioProyectos>> getFullCalendar() {
        return ResponseEntity.ok(calendarioService.getFullCalendar());
    }

    @GetMapping("/consultarPorId/{id-agenda}")
    public ResponseEntity<CalendarioProyectos> getAgendaProyectoById(@PathVariable("id-agenda") Long id){
        Optional<CalendarioProyectos> agendaProyectoBuscar= calendarioService.getAgendaProyectoById(id);
        if(agendaProyectoBuscar.isPresent()){
            return ResponseEntity.ok(agendaProyectoBuscar.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/consultarPorFecha/{fecha}") //La fecha ingresada debe ser AÑO-MES-DIA
    public ResponseEntity<CalendarioProyectos> getAgendaProyectoByDate(@PathVariable("fecha") LocalDate date){
        Optional<CalendarioProyectos> agendaProyectoBuscar= calendarioService.getAgendaProyectoByDate(date);
        if(agendaProyectoBuscar.isPresent()){
            return ResponseEntity.ok(agendaProyectoBuscar.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CalendarioProyectos> agendarNuevoProyecto(@RequestBody CalendarioProyectos calendarioProyectos){
        try{
            return ResponseEntity.ok(calendarioService.agendarNuevoProyecto(calendarioProyectos));
        }
        catch (Exception e){
            return (ResponseEntity<CalendarioProyectos>) ResponseEntity.badRequest();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CalendarioProyectos> updateAgendaProyecto(@RequestBody CalendarioProyectos calendarioProyectos){
        Optional<CalendarioProyectos> agendaProyectoUpdate= calendarioService.getAgendaProyectoById(calendarioProyectos.getId());
        if(agendaProyectoUpdate.isPresent()){
            return ResponseEntity.ok(calendarioService.updateAgendaProyecto(calendarioProyectos));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{idAgenda}")
    public ResponseEntity<String> deleteAgendaProyecto(@PathVariable("idAgenda") Long idAgenda) throws ResourceNotFoundException {
        Optional<CalendarioProyectos> agendaDelete= calendarioService.getAgendaProyectoById(idAgenda);
        if (agendaDelete.isPresent()){
            calendarioService.deleteAgendaProyecto(idAgenda);
            return ResponseEntity.ok("La agenda con ID="+ idAgenda + " fue eliminada con éxito. !!");
        }
        else{
            throw new ResourceNotFoundException("!Error al eliminar¡ No se encontró la agenda con " +
                    "id="+idAgenda+". Error al ingresar el ID");
        }
    }
}
