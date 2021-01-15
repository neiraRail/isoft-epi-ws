package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.service.AntecedenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/antecedentes/")
public class AntecedenteController {

    @Autowired
    private AntecedenteService antecedenteService;

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public Antecedente guardarAntecedente(@RequestBody Antecedente antecedente) {
        return antecedenteService.guardar(antecedente);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void> borrarAntecedente(@PathVariable long id) {
        try {
            antecedenteService.borrar(id);
            return ResponseEntity.ok().build();
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public Iterable<Antecedente> listaAntecedentes() {
        try {
            return antecedenteService.findAll();
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @GetMapping("{id}")
    public Antecedente buscar(@PathVariable long id){
        if(antecedenteService.buscar(id).isEmpty()){
            return null;
        } else{
            return antecedenteService.buscar(id).get();
        }
    }
}
