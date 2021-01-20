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

    /** Método para guardar una comuna.
     * @param comuna
     */
    public void guardar(Comuna comuna){
        comunaRepository.save(comuna);
    }

    /**
     * Método que busca una comuna.
     * @param nombre
     * @return String con mensaje si es que se agrega
     */
    public Comuna findByNombre(String nombre){
        return comunaRepository.findByComNombre(nombre).get();
    }

    public Optional<Comuna> find (Long idComuna){
        return comunaRepository.findById(idComuna);
    }

    public Iterable<Comuna> listarTodo(){
        return comunaRepository.findAll();
    }

    /**
     *  Método para eliminar una communa por su id.
     * @param id
     */
    public void eliminar(Long id){
        comunaRepository.deleteById(id);
    }

    /** Metodo para editar el nombre de una comuna.
     * @param id
     * @param nombre
     */
    public void editarNombre(Long id,String nombre){
        Comuna comunaModificada = comunaRepository.findById(id).get();
        comunaModificada.setComNombre(nombre);
        comunaRepository.save(comunaModificada);
    }

    /** Metodo que verifica que exista el id de una comuna.
     * @param id
     *
     */
    public boolean existById(Long id) {
        return comunaRepository.existsById(id);
    }

}
