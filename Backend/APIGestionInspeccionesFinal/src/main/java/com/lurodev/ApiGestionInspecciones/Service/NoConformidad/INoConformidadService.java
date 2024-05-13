package com.lurodev.ApiGestionInspecciones.Service.NoConformidad;

import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.NoConformidad;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface INoConformidadService {
    List<NoConformidad> getAllNoConformidades();
    List<NoConformidad> getAllNoConformidadesOrderedByOcurrencias();
    Optional<NoConformidad> getNoConformidadById(Long id);
    Optional<Set<NoConformidad>> getNoConformidadByDescripcion(String descripcionNoConformidad);
    Optional<Set<NoConformidad>> getNoConformidadByArticuloNormativo(String articuloNorma);
    NoConformidad postNoConformidad(NoConformidad noConformidad);
    NoConformidad updateNoConformidad(NoConformidad noConformidad);
    void deleteNoConformidadById(Long id);
}
