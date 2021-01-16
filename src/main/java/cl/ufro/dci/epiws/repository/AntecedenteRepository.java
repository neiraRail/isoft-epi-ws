package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Antecedente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedenteRepository extends CrudRepository<Antecedente, Long> {
}
