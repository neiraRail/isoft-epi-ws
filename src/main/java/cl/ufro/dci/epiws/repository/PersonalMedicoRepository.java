package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.model.PersonalMedico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalMedicoRepository extends CrudRepository<PersonalMedico, Long> {
}
