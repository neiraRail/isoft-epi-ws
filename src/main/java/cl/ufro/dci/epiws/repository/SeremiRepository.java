package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Seremi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeremiRepository extends CrudRepository<Seremi, Long> {

    public Seremi findBySerNombre(String serNombre);

}
