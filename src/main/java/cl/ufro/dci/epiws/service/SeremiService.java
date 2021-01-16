package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.model.Seremi;
import cl.ufro.dci.epiws.repository.RegionRepository;
import cl.ufro.dci.epiws.repository.SeremiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SeremiService {

    @Autowired
    private SeremiRepository seremiRepository;

    /** Metodo para guardar una nueva seremi.
     * @param nuevaSeremi
     */
    public void save(Seremi nuevaSeremi){
        seremiRepository.save(nuevaSeremi);
    }

    public Seremi findById(Long id){
        return seremiRepository.findById(id).get();
    }

    public Seremi findByNombre(String nombre){
        return seremiRepository.findBySerNombre(nombre);
    }

    /** Metodo para buscar una seremi por su id.
     * @param id
     * @return
     */
    public Optional<Seremi> find(Long id){
        return seremiRepository.findById(id);
    }

    public Iterable<Seremi> listarTodo(){
        return seremiRepository.findAll();
    }

    /** Metodo para eliminar una seremi por su id.
     * @param id
     */
    public void eliminar(Long id){
        seremiRepository.deleteById(id);
    }

    /** Metodo para editar el nombre de una seremi.
     * @param id
     * @param nombre
     */
    public void editarNombre(long id, String nombre){
        seremiRepository.findById(id).get().setSerNombre(nombre);
    }

    /** Metodo que verifica que exista el id de una seremi.
     * @param id
     * @return
     */
    public boolean existById(Long id) {
        return seremiRepository.existsById(id);
    }

}
