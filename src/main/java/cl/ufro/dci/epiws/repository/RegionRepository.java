package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

    Optional<Region> findByRgnNombre(String rgnNombre);
}
