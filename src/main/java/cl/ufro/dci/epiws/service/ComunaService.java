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
     * Método que busca una comuna.
     * @param nombre
     * @return String con mensaje si es que se agrega
     */
    public Comuna findByNombre(String nombre){
        return comunaRepository.findByComNombre(nombre);
    }

    public Optional<Comuna> find (Long idComuna){
        return comunaRepository.findById(idComuna);
    }

    public Comuna findById(Long id){
        return comunaRepository.findById(id).get();
    }

    /** Método para guardar comuna.
     * @param comuna
     */
    public void guardar(Comuna comuna){
        comunaRepository.save(comuna);
    }

    /**
     *  Método para eliminar comuna especifica.
     * @param id
     */
    public void eliminar(Long id){
        comunaRepository.deleteById(id);
    }

}
