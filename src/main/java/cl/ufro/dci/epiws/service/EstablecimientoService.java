package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstablecimientoService {


    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    /** Metodo para guardar un nuevo establecimiento.
     * @param nuevaEstablecimiento
     */
    public void guardar(Establecimiento nuevaEstablecimiento){
        establecimientoRepository.save(nuevaEstablecimiento);
    }

    /** Metodo para eliminar un establecimiento por su id.
     * @param id
     */
    public void eliminar(Long id){
        establecimientoRepository.deleteById(id);
    }

    /** Metodo para buscar un establecimiento por su id.
     * @param id
     * @return
     */
    public Optional<Establecimiento> find(Long id){
        return establecimientoRepository.findById(id);
    }

    public Iterable<Establecimiento> listarTodo(){
        return establecimientoRepository.findAll();
    }

    /** Metodo para editar la comuna de un establecimiento.
     * @param id
     * @param comuna
     */
    public void editarComuna(Long id, Comuna comuna){
        Establecimiento establecimientoModificado = establecimientoRepository.findById(id).get();
        establecimientoModificado.setComuna(comuna);
        establecimientoRepository.save(establecimientoModificado);
    }

    /** Metodo para editar el nombre del establecimiento.
     * @param id
     * @param nombre
     */
    public void editarNombre(Long id,String nombre){
        Establecimiento establecimientoModificado = establecimientoRepository.findById(id).get();
        establecimientoModificado.setEstNombre(nombre);
        establecimientoRepository.save(establecimientoModificado);
    }

    /** Metodo para editar la direccion del establecimiento.
     * @param id
     * @param direccion
     */
    public void editarDireccion(Long id, String direccion){
        Establecimiento establecimientoModificado = establecimientoRepository.findById(id).get();
        establecimientoModificado.setEstDireccion(direccion);
        establecimientoRepository.save(establecimientoModificado);
    }


    /** Metodo para editar las lista de pacientes de un establecimiento.
     * @param id
     * @param lista
     */
    public void editarListaPacientes(Long id, List<Paciente> lista){
        establecimientoRepository.findById(id).get().setPacienteList(lista);
    }

    /** Metodo que verifica que exista el id.
     * @param id
     *
     */
    public boolean existById(Long id) {
        return establecimientoRepository.existsById(id);
    }

    /**
     *  Metodo que busca establecimiento por nombre.
     * @param nombre
     * @return Establecimiento
     */
    public Establecimiento findByNombre(String nombre){
        return establecimientoRepository.findByEstNombre(nombre);
    }
}
