package com.lurodev.ApiGestionInspecciones.Service.Competencia;

import com.lurodev.ApiGestionInspecciones.Entities.Competencia;
import java.util.List;
import java.util.Optional;

public interface ICompetenciaService {
    List<Competencia> getAllCompetencias();
    Optional<Competencia> getCompetenciaById(Long id);
    Competencia postCompetencia(Competencia competencia);
    Competencia updateCompetencia(Competencia competencia);
    void deleteCompetencia(Long id);
}
