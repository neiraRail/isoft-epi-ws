package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * You can try this controller in http://localhost:8080/api/region/agregar
     */

    /**
     * Método que permite agregar registros de regiones.
     *
     *
     * @return String con mensaje si es que se agrega
     */
    @PostMapping("/agregar")
    public String agregar(){

        regionService.save(new Region("Región de Arica y Parinacota"));
        return "it's wordks";
    }

    /**
     * Método que permite buscar registros de regiones.
     *
     * @return String con mensaje si es que se agrega.
     */
    @GetMapping("/buscar")
    public Region buscarRegion(@RequestParam String nombre) {
        return regionService.findByNombre(nombre);
    }

    /**
     * Método que permite eliminar registros de regiones.
     *
     * @return String con mensaje si es que se agrega
     */
    @DeleteMapping("/eliminar/{idRegion}")
    public String eliminarRegion(@PathVariable Long idRegion){
        if (regionService.find(idRegion).isEmpty()){
            return null;
        } else {
            regionService.eliminar(idRegion);
        }
        return "Se ha eliminado correctamente";
    }

    /**
     * Método que permite editar registros de regiones.
     *
     * @return String con mensaje si es que se agrega
     */
    @PutMapping("/editarNombre/{idRegion}")
    public String editarNombre(@PathVariable ("idRegion") Long idRegion, @RequestBody Region region) {
        if (regionService.existById(idRegion)) {
            regionService.editarNombre(idRegion, region.getRgnNombre());

            return "El establecimiento se ha cambiado correctamente.";
        }
        return "No se ha podido editar el establecimiento.";
    }
}
