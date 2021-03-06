package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.service.ComunaService;
import cl.ufro.dci.epiws.service.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

/**
 *  Clase controlador para administrar establecimientos.
 */
@RestController
@RequestMapping("/api/establecimiento")
@CrossOrigin
public class EstablecimientoController {

    @Autowired
    private EstablecimientoService establecimientoService;

    @Autowired
    private ComunaService comunaService;


    /**
     * Método que permite agregar registros de establecimientos.
     * @param nombre
     * @param direccion
     * @return String con mensaje si es que se agrega
     */
    @PostMapping("/agregar/{nombre}/{direccion}/{idComuna}")
    @ResponseBody
    public String agregar(@PathVariable String nombre, @PathVariable String direccion, @PathVariable int idComuna){
        Long l = (long) idComuna;
        Establecimiento establecimiento = new Establecimiento(nombre, direccion, comunaService.find(l).orElse(null), new ArrayList<>());
        establecimientoService.guardar(establecimiento);
        return "El establecimiento se ha agregado correctamente.";
    }

    /**
     * Método que permite buscar registros de establecimientos.
     * @param idEstablecimiento
     * @return null en caso que no se encuentre el registro buscado o el objeto en caso que se encuentre.
     */
    @GetMapping("/buscar/{idEstablecimiento}")
    public Establecimiento buscar(@PathVariable int idEstablecimiento){
        Long l = (long) idEstablecimiento;
        if (establecimientoService.find(l).isEmpty()) {
            return null;
        } else {
            return establecimientoService.find(l).get();
        }
    }

    /**
     * Método que permite listar los registros de establecimiento.
     */
    @GetMapping("/listar")
    public Iterable<Establecimiento> listar(){
        return establecimientoService.listarTodo();
    }

    /**
     * Método que permite eliminar un registro de establecimiento.
     * @param idEstablecimiento
     * @return String con mensaje en caso que se elimine o no el registro de establecimiento.
     */
    @DeleteMapping("/eliminar/{idEstablecimiento}")
    @ResponseBody
    public String eliminar(@PathVariable ("idEstablecimiento") Long idEstablecimiento){
        if (establecimientoService.find(idEstablecimiento).isEmpty()){
            return null;
        } else {
            establecimientoService.eliminar(idEstablecimiento);
        }
        return "Se ha eliminado correctamente";
    }

    /**
     * Método que permite editar un registro de un establecimiento.
     * @param idEstablecimiento
     * @param establecimiento
     * @return String con mensaje en caso que se edite o no el registro de establecimiento.
     */
    @PutMapping(value = "/editar/{idEstablecimiento}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String editar(@PathVariable ("idEstablecimiento") Long idEstablecimiento, @RequestBody Establecimiento establecimiento) {
        if (establecimientoService.existById(idEstablecimiento)) {
            establecimientoService.editarNombre(idEstablecimiento, establecimiento.getEstNombre());
            establecimientoService.editarDireccion(idEstablecimiento, establecimiento.getEstDireccion());
            establecimientoService.editarComuna(idEstablecimiento, establecimiento.getComuna());

            return "El establecimiento se ha cambiado correctamente.";
        }
        return "No se ha podido editar el establecimiento.";
    }

}
