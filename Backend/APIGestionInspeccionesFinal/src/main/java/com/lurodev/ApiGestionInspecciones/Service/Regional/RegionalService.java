package com.lurodev.ApiGestionInspecciones.Service.Regional;

import com.lurodev.ApiGestionInspecciones.Entities.Regional;
import com.lurodev.ApiGestionInspecciones.Repository.RegionalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegionalService implements IRegionalService{
    private RegionalRepository regionalRepository;

    public RegionalService(RegionalRepository regionalRepository) {
        this.regionalRepository = regionalRepository;
    }

    @Override
    public List<Regional> getAllRegionales() {
        return regionalRepository.findAll();
    }

    @Override
    public Optional<Regional> getRegionalByID(Long IdRegional) {
        return regionalRepository.findById(IdRegional);
    }

    @Override
    public Regional postRegional(Regional regional) {
        return regionalRepository.save(regional);
    }

    @Override
    public Regional updateRegional(Regional regional) {
        return regionalRepository.save(regional);
    }

    @Override
    public void deleteRegional(Long IdRegionalDelete) {
        regionalRepository.deleteById(IdRegionalDelete);
    }
}
