package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.service.ComunaService;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/comuna")
@CrossOrigin
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @Autowired
    private RegionService regionService;

    /**
     * Método que permite agregar registros de regiones.
     * @param nombre
     * @param regionId
     * @return String con mensaje si es que se agrega.
     */
    @PostMapping("/agregar")
    @ResponseBody
    public String agregarComuna(@RequestParam String nombre, @RequestParam int regionId){
        Long l = (long) regionId;
        Comuna comuna = new Comuna(nombre, regionService.find(l).orElse(null), new ArrayList<>());
        comunaService.guardar(comuna);
        return "Se ha agregado la comuna exitosamente";
    }
    /**
     * Método que permite buscar registros de comunas.
     * @param nombre
     * @return String con mensaje si es que se agrega
     */
    @GetMapping("/buscar")
    public Comuna buscarComuna(@RequestParam String nombre) {
        return comunaService.findByNombre(nombre);
    }

    /**
     * Método para editar un registro de comuna.
     * @param idComuna
     * @param comuna
     * @return
     */
    @PutMapping(value = "/editar/{idComuna}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String editar(@PathVariable ("idComuna") Long idComuna, @RequestBody Comuna comuna) {
        if (comunaService.existById(idComuna)) {
            comunaService.editarNombre(idComuna, comuna.getComNombre());
            comunaService.editarRegion(idComuna,comuna.getRegion());
            return "La comuna se ha cambiado correctamente.";
        }
        return "No se ha podido editar la comuna.";
    }

    /**
     * Método que permite eliminar registros de comunas.
     * @param idComuna
     * @return String con mensaje si es que se agrega
     */
    @DeleteMapping("/eliminar/{idComuna}")
    public String eliminarComuna(@PathVariable int idComuna){
        Long l = (long) idComuna;
        if (comunaService.find(l).isEmpty()){
            return null;
        } else {
            comunaService.eliminar(l);
        }
        return "Se ha eliminado correctamente";
    }

}
