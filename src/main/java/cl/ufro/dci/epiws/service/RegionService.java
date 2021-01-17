package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    /** Metodo para guardar una nueva región.
     * @param nuevaRegion
     */
    public void save(Region nuevaRegion){
        regionRepository.save(nuevaRegion);
    }

    public Region findById(Long id){
        return regionRepository.findById(id).get();
    }

    public Region findByNombre(String nombre){
        return regionRepository.findByRgnNombre(nombre).get();
    }

    /** Metodo para buscar una región por su id.
     * @param id
     * @return
     */
    public Optional<Region> find(Long id){
        return regionRepository.findById(id);
    }

    public Iterable<Region> listarTodo(){
        return regionRepository.findAll();
    }

    /** Metodo para eliminar una región por su id.
     * @param id
     */
    public void eliminar(Long id){
        regionRepository.deleteById(id);
    }

    /** Metodo para editar el nombre de una región.
     * @param id
     * @param nombre
     */
    public void editarNombre(long id, String nombre){
        regionRepository.findById(id).get().setRgnNombre(nombre);
    }

    /** Metodo que verifica que exista el id de una región.
     * @param id
     * @return
     */
    public boolean existById(Long id) {
        return regionRepository.existsById(id);
    }

}
