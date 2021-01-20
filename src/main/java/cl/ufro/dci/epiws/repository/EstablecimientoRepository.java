package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Establecimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablecimientoRepository extends CrudRepository<Establecimiento, Long> {

    public Establecimiento findByEstNombre(String estNombre);
}
