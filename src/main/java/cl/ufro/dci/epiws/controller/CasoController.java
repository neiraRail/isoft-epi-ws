package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Caso;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.service.CasoService;
import cl.ufro.dci.epiws.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/api/casos/")
public class CasoController {

    @Autowired
    private CasoService casoService;

    @Autowired
    private PacienteService pacienteService;

    /**
     * Método que entrega todos los objetos Caso almacenados en la base de datos
     * @return Todos los casos almacenados
     */
    @GetMapping("")
    public Iterable<Caso> getCasos() {
        return casoService.obtenerCasos();
    }

    /**
     * Un simple metodo para probar la eficacia de los controladores
     * @return Objeto caso que fue ingresado
     * @throws Exception por si las moscas
     */
    @GetMapping("agregar")
    public Caso agregarCasoDePrueba() throws Exception {
        Paciente paciente = new Paciente(199426010L,null,new ArrayList<>(),new ArrayList<>(),"lucho","silva","masculino","07-10-98",null,"chilena","mapuche","los trigales","+569 ...");
        Caso caso = new Caso("17-01-2021",true,"15-01-2021",2,"Diarrea y dolor de cabeza","Fuerte dolor de cabeza","Contagiado",true,paciente);
        pacienteService.save(paciente);
        return casoService.save(caso);
    }


    /**
     * Método que se encarga de agregar casos a través de parámetros dados por la interfaz.
     * @param caso Caso a agregar.
     * @return ResponseEntity<String> que retorna la instancia del caso agregado.
     */
    @PostMapping("")
    public ResponseEntity<Caso> postCaso(@RequestBody Caso caso) {
        return ResponseEntity.ok(casoService.save(caso));
    }

    /**
     * @param casoPost objeto con parámetros modificados
     * @param id id Id del caso a editar
     * @return ResponseEntity<Caso> que retorna la instancia del caso editado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Caso> editCaso(@RequestBody Caso casoPost, @PathVariable Long id) {
        return ResponseEntity.ok(casoService.editCaso(casoPost,id));
    }

    /**
     * Método que borra un caso a través de su id.
     * @param id Id del caso que se quiere borrar.
     * @return ResponseEntity<String> que describe si hizo o no la acción.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCaso(@PathVariable Long id) {
        casoService.deleteById(id);
        return ResponseEntity.ok("El caso fue removido");
    }

    /**
     * Método que busca a través de la id un caso especifico.
     * @param id del caso requerido
     * @return caso buscado si este no existe.
     */
    @GetMapping("/{id}")
    public Caso findCaso(@PathVariable("id") Long id) {
        return casoService.findById(id);

    }

}

