package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * Método que permite agregar registros de regiones.
     * @param nombre
     *
     * @return String con mensaje si es que se agrega
     */
    @PostMapping("/agregar")
    @ResponseBody
    public String agregar(@RequestParam String nombre){
        Region region = new Region(nombre);
        regionService.guardar(region);
        return "Se ha agregado correctamente";
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
     * @param idRegion
     * @return String con mensaje si es que se agrega
     */
    @DeleteMapping("/eliminar/{idRegion}")
    @ResponseBody
    public String eliminarRegion(@PathVariable Long idRegion){
        if (regionService.find(idRegion)==null){
            return null;
        } else {
            regionService.eliminar(idRegion);
        }
        return "Se ha eliminado correctamente";
    }

    /**
     * Método que permite editar registros de regiones.
     * @param idRegion
     * @param region
     * @return String con mensaje si es que se agrega
     */
    @PutMapping(value = "/editar/{idRegion}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String editarNombre(@PathVariable ("idRegion") long idRegion, @RequestBody Region region) {
        if(regionService.existById(idRegion)) {
            regionService.editarRegion(idRegion, region.getRgnNombre());

            return "La región se ha editado correctamente";
        }
        return "No se ha podido editar la región";
    }
}
