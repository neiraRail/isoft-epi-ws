package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Caso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CasoRepository extends CrudRepository<Caso, Long> {
}