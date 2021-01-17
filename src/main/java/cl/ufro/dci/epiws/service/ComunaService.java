package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    /**
     * Método que busca una comuna
     * @param nombre
     * @return String con mensaje si es que se agrega
     */
    public Optional<Comuna> find(String nombre){
        return comunaRepository.findByComNombre(nombre);
    }

}
