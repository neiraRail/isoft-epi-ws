package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.service.SeremiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;

@RestController
@RequestMapping("/api/seremi")
public class SeremiController {

    @Autowired
    private SeremiService seremiService;

    @PostMapping("/agregar")
    public String agregar(){

        return "it's wordks";
    }
}
