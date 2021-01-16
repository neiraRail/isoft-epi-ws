package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * You can try this controller in http://localhost:8080/api/region/agregar
     * @return
     */
    @GetMapping("/agregar")
    public String agregar(){

        regionService.save(new Region("Región de Arica y Parinacota"));
        return "it's wordks";
    }
}
