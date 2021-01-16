package cl.ufro.dci.epiws.service;
import cl.ufro.dci.epiws.model.Caso;
import cl.ufro.dci.epiws.repository.CasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CasoService {

    @Autowired
    private CasoRepository casoRepository;

    /**
     * Método que guarda en la base de datos un caso generado
     * @param nuevoCaso caso a guardar
     */
    public void save(Caso nuevoCaso){
        casoRepository.save(nuevoCaso);
    }

    /**
     * Método que borra un caso de la case de datos.
     * @param id id del caso a borrar.
     */
    public void deleteById(long id){
        casoRepository.deleteById(id);
    }

    /**
     * Método que encuentra un elemento a traves de la clase.
     * @param id ide del caso a buscar.
     * @return caso buscado.
     */
    public Caso findById(long id){
        return casoRepository.findById(id).get();
    }

    /**
     * Método que extrae el conjunto de casos guardados en la base de datos.
     * @return ArrayList con los casos.
     */
    public ArrayList<Caso> obtenerCasos(){
        return (ArrayList<Caso>) casoRepository.findAll();
    }

    /**
     * Método que confirma la existencia de un caso por su id.
     * @param id id del caso a confirmar.
     * @return boleano de confirmación.
     */
    public boolean existById(Long id){
        return casoRepository.existsById(id);
    }


}
