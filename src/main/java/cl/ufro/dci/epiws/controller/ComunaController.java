package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.service.ComunaService;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/comuna")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @Autowired
    private RegionService regionService;

    /**
     * Método que permite agregar registros de regiones.
     * @param nombre
     * @return String con mensaje si es que se agrega.
     */
    @PostMapping("/agregar")
    @ResponseBody
    public String agregarComuna(@RequestParam String nombre, @RequestParam Long regionId){
        Comuna comuna = new Comuna(1l,nombre,regionService.findById(regionId),new ArrayList<>());
        regionService.findById(regionId).getComunaList().add(comuna);
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
     * Método que permite eliminar registros de comunas.
     *
     * @return String con mensaje si es que se agrega
     */
    @DeleteMapping("/eliminar/{idComuna}")
    public String eliminarComuna(@PathVariable Long idComuna){
        if (comunaService.find(idComuna).isEmpty()){
            return null;
        } else {
            comunaService.eliminar(idComuna);
        }
        return "Se ha eliminado correctamente";
    }

}
