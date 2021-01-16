package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    Region findByRgnNombre(String rgnNombre);
}
