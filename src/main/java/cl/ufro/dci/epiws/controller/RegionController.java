package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.repository.RegionRepository;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * Método que permite agregar registros de regiones.
     *
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
     *
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
     *
     * @return String con mensaje si es que se agrega
     */
    @PutMapping("/editar")
    public String editarNombre(@RequestParam int idRegion, @RequestParam String nombre) {
        Long l = (long) idRegion;
        if (regionService.existById(l)) {
            if (regionService.find(l).isPresent()){
                regionService.editarNombre(l,nombre);
            }
            return "El establecimiento se ha cambiado correctamente.";
        }
        return "No se ha podido editar el establecimiento.";
    }
}
