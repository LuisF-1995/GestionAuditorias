package com.lurodev.ApiGestionInspecciones.Service.Competencia;

import com.lurodev.ApiGestionInspecciones.Entities.Competencia;
import com.lurodev.ApiGestionInspecciones.Repository.CompetenciaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService implements ICompetenciaService{
    private final CompetenciaRepository competenciaRepository;

    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @Override
    public List<Competencia> getAllCompetencias() {
        return competenciaRepository.findAll();
    }

    @Override
    public Optional<Competencia> getCompetenciaById(Long id) {
        return competenciaRepository.findById(id);
    }

    @Override
    public Competencia postCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    @Override
    public Competencia updateCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    @Override
    public void deleteCompetencia(Long id) {
        competenciaRepository.deleteById(id);
    }


}
