package com.lurodev.ApiGestionInspecciones.Service.Regional;

import com.lurodev.ApiGestionInspecciones.Entities.Regional;

import java.util.List;
import java.util.Optional;

public interface IRegionalService {
    List<Regional> getAllRegionales();
    Optional<Regional> getRegionalByID(Long IdRegional);
    Regional postRegional(Regional regional);
    Regional updateRegional(Regional regional);
    void deleteRegional(Long IdRegionalDelete);
}
