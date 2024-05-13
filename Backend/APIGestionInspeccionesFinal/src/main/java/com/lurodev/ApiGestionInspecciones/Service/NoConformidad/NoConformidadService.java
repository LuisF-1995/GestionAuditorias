package com.lurodev.ApiGestionInspecciones.Service.NoConformidad;

import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.EvidenciaNC;
import com.lurodev.ApiGestionInspecciones.Entities.noConformidades.NoConformidad;
import com.lurodev.ApiGestionInspecciones.Repository.noConformidadRepo.EvidenciaNcRepository;
import com.lurodev.ApiGestionInspecciones.Repository.noConformidadRepo.NoConformidadRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NoConformidadService implements INoConformidadService{
    private final NoConformidadRepository noConformidadRepository;
    private final EvidenciaNcRepository evidenciaRepository;

    public NoConformidadService(NoConformidadRepository noConformidadRepository, EvidenciaNcRepository evidenciaRepository) {
        this.noConformidadRepository = noConformidadRepository;
        this.evidenciaRepository = evidenciaRepository;
    }

    @Override
    public List<NoConformidad> getAllNoConformidades() {
        return noConformidadRepository.findAll();
    }

    @Override
    public List<NoConformidad> getAllNoConformidadesOrderedByOcurrencias() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return noConformidadRepository.findTop10ByOrderByNumeroOcurrenciasDesc(pageRequest);
    }

    @Override
    public Optional<NoConformidad> getNoConformidadById(Long id) {
        Optional<NoConformidad> noConformidadBuscada = noConformidadRepository.findById(id);
        if(noConformidadBuscada.isPresent()){
            Long numeroOcurrencias = noConformidadBuscada.get().getNumeroOcurrencias();
            if(numeroOcurrencias == null){
                numeroOcurrencias = 1L;
            }
            else{
                numeroOcurrencias ++;
            }
            noConformidadBuscada.get().setNumeroOcurrencias(numeroOcurrencias);
            noConformidadRepository.save(noConformidadBuscada.get());
            return noConformidadRepository.findById(id);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Set<NoConformidad>> getNoConformidadByDescripcion(String descripcionNoConformidad) {
        return noConformidadRepository.findNoConformidadByDescripcionNoConformidad(descripcionNoConformidad);
    }

    @Override
    public Optional<Set<NoConformidad>> getNoConformidadByArticuloNormativo(String articuloNorma) {
        return noConformidadRepository.findNoConformidadByArticuloNormativo(articuloNorma);
    }

    @Override
    public NoConformidad postNoConformidad(NoConformidad noConformidad) {
        List<EvidenciaNC> evidenciasNC = noConformidad.getEvidenciaNoConformidad();
        for (EvidenciaNC evidenciaChange: evidenciasNC){
            evidenciaChange.setNoConformidad(noConformidad);
        }
        noConformidad.setEvidenciaNoConformidad(evidenciasNC);
        NoConformidad noConformidadAdded = noConformidadRepository.save(noConformidad);
        Optional<NoConformidad> getNoConformidadAddedById = this.getNoConformidadById(noConformidadAdded.getId());

        /*if(getNoConformidadAddedById.isPresent() && !evidenciasNC.isEmpty()) {
            for (EvidenciaNC evidencia : evidenciasNC) {
                EvidenciaNC evidenciaEntity = new EvidenciaNC();
                evidenciaEntity.setNombreDocumento(evidencia.getNombreDocumento());
                evidenciaEntity.setTipo(evidencia.getTipo());
                evidenciaEntity.setContenido(evidencia.getContenido());
                evidenciaEntity.setNoConformidad(getNoConformidadAddedById.get());
                evidenciaRepository.save(evidenciaEntity);
            }
        }*/

        return getNoConformidadAddedById.orElse(null);
    }

    @Override
    public NoConformidad updateNoConformidad(NoConformidad noConformidad) {
        return noConformidadRepository.save(noConformidad);
    }

    @Override
    public void deleteNoConformidadById(Long id) {
        noConformidadRepository.deleteById(id);
    }
}
