package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Caso;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.CasoRepository;
import cl.ufro.dci.epiws.repository.PacienteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class CasoService {

    @Autowired
    private CasoRepository casoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * Método que guarda en la base de datos un caso generado
     * @param nuevoCaso caso a guardar
     */
    public Caso save(Caso nuevoCaso) {
        if(Optional.ofNullable(nuevoCaso).isPresent()){ //Verifica que el objeto Caso a guardar no sea nulo.
            if(Optional.ofNullable(nuevoCaso.getPaciente()).isPresent()){ //Verifica que el objeto Paciente de Caso no sea nulo.
                if(pacienteRepository.existsById(nuevoCaso.getPaciente().getPacRut())) { //Verifica que el rut del paciente se encuentre en la base de datos.
                    Caso casoBD = casoRepository.save(nuevoCaso);
                    log.info("El caso fue agregado correctamente");
                    return casoBD;
                }else{
                    log.info("El paciente no esta almacenado en la base de datos");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encuentra tal paciente para agregar un caso");
                }
            }else{
                log.info("El objeto Paciente que quiere agregar es nulo");
                throw new NullPointerException("El objeto Paciente es nulo");
            }
        }else {
            log.info("El objeto Caso que quiere agregar es nulo");
            throw new NullPointerException("El objeto Caso es nulo");
        }
    }

    /**
     * Método que borra un caso de la case de datos.
     * @param id id del caso a borrar.
     */
    public void deleteById(long id) {
        if(casoRepository.existsById(id)){
            casoRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Caso no encontrado, id: "+id);
        }
    }

    /**
     * Método que edita un caso de la base de datos.
     * @param casoPost caso con los parámetros editados.
     * @param id id del caso a modificar.
     */
    public Caso editCaso(Caso casoPost, Long id) {
        Caso casoBD = casoRepository.findById(id).get();
        casoBD.setCasFechaNotificacion(casoPost.getCasFechaNotificacion());
        casoBD.setCasAsintomatico(casoPost.getCasAsintomatico());
        casoBD.setCasFechaPrimerosSintomas(casoPost.getCasFechaPrimerosSintomas());
        casoBD.setCasSemanaEpidemiologica(casoPost.getCasSemanaEpidemiologica());
        casoBD.setCasSintomas(casoPost.getCasSintomas());
        casoBD.setCasRazonSospecha(casoPost.getCasRazonSospecha());
        casoBD.setCasClasificacionFinal(casoPost.getCasClasificacionFinal());
        casoBD.setCasHospitalizacion(casoPost.getCasHospitalizacion());
        Paciente pacienteBD = pacienteRepository.getOne(casoPost.getPaciente().getPacRut());
        casoBD.setPaciente(pacienteBD);
        return casoRepository.save(casoBD);
    }


    /**
     * Método que encuentra un elemento a través de la clase.
     * @param id ide del caso a buscar.
     * @return caso buscado.
     */
    public Caso findById(long id) {
        if(casoRepository.existsById(id)){
            log.info("Se encontró correctamente el caso");
            return casoRepository.findById(id).get();
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Caso no encontrado, id: "+id);
        }
    }

    /**
     * Método que extrae el conjunto de casos guardados en la base de datos.
     * @return ArrayList con los casos.
     */
    public Iterable<Caso> obtenerCasos(){
        return casoRepository.findAll();
    }

    /**
     * Método que confirma la existencia de un caso por su id.
     * @param id id del caso a confirmar.
     * @return boleano de confirmación.
     */
    public boolean existById(Long id){
        return casoRepository.existsById(id);
    }

    private static final Logger log = LoggerFactory.getLogger(CasoService.class);

}
