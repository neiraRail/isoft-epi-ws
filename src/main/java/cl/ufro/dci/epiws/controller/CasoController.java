package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.*;
import cl.ufro.dci.epiws.service.CasoService;
import cl.ufro.dci.epiws.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheableOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/caso")
public class CasoController {

    @Autowired
    private CasoService casoService;
    @Autowired
    private PacienteService pacienteService;

    /**
     * Método que se encarga de agregar casos a travez de parámetros dados por la interfaz (Usa una clase)
     * @param casSintomas Descripción de los sintomas del caso en específico.
     * @param casFecha Fecha en la cual fue registrado el caso.
     * @param casAsintomatico Especifica si es asintomatico o no.
     * @param paciente Paciente al cual corresponde el caso.
     * @return Intancia especifica del caso.
     */
    @PostMapping(path="/agregar/{casSintomas}/{casFecha}/{casAsintomatico}")
    @ResponseBody
    public Caso agregar(@PathVariable("casSintomas") String casSintomas,
                        @PathVariable("casFecha") String casFecha,
                        @PathVariable("casAsintomatico") Boolean casAsintomatico,
                        @RequestBody Paciente paciente) {
        Caso caso = new Caso(casSintomas,casFecha,casAsintomatico,paciente);
        casoService.save(caso);
        return caso;
    }

    /**
     * Método que se encarga de agregar casos a travez de parámetros dados por la interfaz. (Usa id del paciente para
     * anexarlo)
     * @param casSintomas Descripción de los sintomas del caso en específico.
     * @param casFecha Fecha en la cual fue registrado el caso.
     * @param casAsintomatico Especifica si es asintomatico o no.
     * @param idPaciente Id del Paciente al cual corresponde el caso.
     * @return Intancia especifica del caso.
     */
    @PostMapping(path="/agregar/{casSintomas}/{casFecha}/{casAsintomatico}/{idPaciente}")
    @ResponseBody
    public Caso agregar(@PathVariable("casSintomas") String casSintomas,
                        @PathVariable("casFecha") String casFecha,
                        @PathVariable("casAsintomatico") Boolean casAsintomatico,
                        @PathVariable("idPaciente") Long idPaciente) {
        Paciente paciente = pacienteService.buscarPaciente(idPaciente).get();
        Caso caso = new Caso(casSintomas,casFecha,casAsintomatico,paciente);
        casoService.save(caso);
        return caso;
    }

    /**
     * Método que borra un caso a travez de su id.
     * @param id Id del caso que se quiere borrar.
     * @return Boleano que describe si hizo o no la acción.
     */
    @DeleteMapping("/borrarCaso/{id}")
    public boolean borrarCaso(@PathVariable("id") Long id) {
        if (casoService.existById(id)){
            casoService.deleteById(id);
        return true;
        }
        return false;
    }

    /**
     * Método que dandole una caso y una id que lo identifica, cambia todos los atributos de este objeto. Para mantener
     * un atributo intacto se debe poner la misma entrada que esta actualmente.
     * @param id Id del caso a editar
     * @param newCasSintomas Nueva descripción de los sintomas del caso.
     * @param newCasFecha Nueva fecha de registro de caso.
     * @param newCasAsintomatico Nuevo valor del si es asintomatico o no.
     * @param newCasPaciente Nuevo paciente al cual pertenece el caso.
     * @return Instancia del caso modificado.
     */
    @PutMapping("/Editar/{id}/{newCasSintomas}/{newCasFecha}/{newCasAsintomatico}")
    public Caso EditarCaso(@PathVariable("id") Long id,
                           @PathVariable("newCasSintomas") String newCasSintomas,
                           @PathVariable("newCasFecha") String newCasFecha,
                           @PathVariable("newCasAsintomatico") Boolean newCasAsintomatico,
                           @RequestBody Paciente newCasPaciente){
        Caso caso = casoService.findById(id);
        caso.setCasSintomas(newCasSintomas);
        caso.setCasFecha(newCasFecha);
        caso.setCasAsintomatico(newCasAsintomatico);
        caso.setPaciente(newCasPaciente);
        casoService.save(caso);
        return caso;
    }

    /**
     * Método que dandole una caso y una id que lo identifica, cambia todos los atributos de este objeto. Para mantener
     * un atributo intacto se debe poner la misma entrada que esta actualmente.
     * @param id Id del caso a editar
     * @param newCasSintomas Nueva descripción de los sintomas del caso.
     * @param newCasFecha Nueva fecha de registro de caso.
     * @param newCasAsintomatico Nuevo valor del si es asintomatico o no.
     * @param idPaciente Nuevo paciente al cual pertenece el caso.
     * @return Instancia del caso modificado.
     */
    @PutMapping("/Editar/{id}/{newCasSintomas}/{newCasFecha}/{newCasAsintomatico}/{idPaciente}")
    public Caso EditarCaso(@PathVariable("id") Long id,
                           @PathVariable("newCasSintomas") String newCasSintomas,
                           @PathVariable("newCasFecha") String newCasFecha,
                           @PathVariable("newCasAsintomatico") Boolean newCasAsintomatico,
                           @PathVariable("idPaciente") Long idPaciente){
        Caso caso = casoService.findById(id);
        Paciente newCasPaciente = pacienteService.buscarPaciente(idPaciente).get();
        caso.setCasSintomas(newCasSintomas);
        caso.setCasFecha(newCasFecha);
        caso.setCasAsintomatico(newCasAsintomatico);
        caso.setPaciente(newCasPaciente);
        casoService.save(caso);
        return caso;
    }

    /**
     * Método que busca a travez de la id un caso especifico.
     * @param id del caso requerido
     * @return caso buscado o nulo si este no existe.
     */
    @GetMapping("/buscarCaso/{id}")
    public Caso buscarCaso(@PathVariable("id") Long id){
        if(casoService.existById(id)){
            return casoService.findById(id);
        }
        return null;
    }

}
