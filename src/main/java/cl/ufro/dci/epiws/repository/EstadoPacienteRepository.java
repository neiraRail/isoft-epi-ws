package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.EstadoPaciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPacienteRepository extends CrudRepository<EstadoPaciente, Long> {
}
