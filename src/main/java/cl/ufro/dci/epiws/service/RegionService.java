package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void save(Region nuevaRegion){
        regionRepository.save(nuevaRegion);
    }
}
