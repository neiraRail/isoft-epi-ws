package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {

    Optional<Comuna> findByComNombre(String nombre);

}
