package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void save(Region nuevaRegion){
        regionRepository.save(nuevaRegion);
    }

    public Region findById(Long id){
        return regionRepository.findById(id).get();
    }

    public Region findByNombre(String nombre){
        return regionRepository.findByRgnNombre(nombre);
    }

    public Optional<Region> find(Long id){
        return regionRepository.findById(id);
    }

    public void eliminar(Long id){
        regionRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return regionRepository.existsById(id);
    }

    public void editarNombre(long id, String nombre){
        regionRepository.findById(id).get().setRgnNombre(nombre);
    }
}
