package com.lurodev.ApiGestionInspecciones.Service.Calendario;

import com.lurodev.ApiGestionInspecciones.Entities.CalendarioProyectos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICalendarioService {
    List<CalendarioProyectos> getFullCalendar();
    Optional<CalendarioProyectos> getAgendaProyectoById(Long id);
    Optional<CalendarioProyectos> getAgendaProyectoByDate(LocalDate date);
    CalendarioProyectos agendarNuevoProyecto(CalendarioProyectos agendaProyecto);
    CalendarioProyectos updateAgendaProyecto(CalendarioProyectos agendaProyecto);
    void deleteAgendaProyecto(Long id);
}
