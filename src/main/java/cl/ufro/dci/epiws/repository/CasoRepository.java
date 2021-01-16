package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Caso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasoRepository extends CrudRepository<Caso, Long> {
}
