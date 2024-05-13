package com.lurodev.ApiGestionInspecciones.Service.Calendario;

import com.lurodev.ApiGestionInspecciones.Entities.CalendarioProyectos;
import com.lurodev.ApiGestionInspecciones.Repository.CalendarioProyectosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService implements ICalendarioService{
    private CalendarioProyectosRepository calendarioRepository;

    public CalendarioService(CalendarioProyectosRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }


    @Override
    public List<CalendarioProyectos> getFullCalendar() {
        return calendarioRepository.findAll();
    }

    @Override
    public Optional<CalendarioProyectos> getAgendaProyectoById(Long id) {
        Optional<CalendarioProyectos> findAgendaProyecto = calendarioRepository.findById(id);
        if(findAgendaProyecto.isPresent()){
            return findAgendaProyecto;
        }
        else{
            return null;
        }
    }

    @Override
    public Optional<CalendarioProyectos> getAgendaProyectoByDate(LocalDate date) {
        Optional<CalendarioProyectos> findAgendaProyecto = calendarioRepository.findCalendarioProyectosByFechaInspeccion(date);
        if(findAgendaProyecto.isPresent()){
            return findAgendaProyecto;
        }
        else{
            return null;
        }
    }

    @Override
    public CalendarioProyectos agendarNuevoProyecto(CalendarioProyectos agendaProyecto) {
        return calendarioRepository.save(agendaProyecto);
    }

    @Override
    public CalendarioProyectos updateAgendaProyecto(CalendarioProyectos agendaProyecto) {
        return calendarioRepository.save(agendaProyecto);
    }

    @Override
    public void deleteAgendaProyecto(Long id) {
        calendarioRepository.deleteById(id);
    }
}
