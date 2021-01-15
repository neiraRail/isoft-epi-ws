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
        antecedenteService.guardar(antecedente);
        return antecedente;
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

    @PutMapping("/editarEmbarazo/{id}")
    public String editarEmbarazo(@RequestParam boolean embarazo, @PathVariable long id) {
        try {
            antecedenteService.editarEmbarazo(id,embarazo);
            return "editado correctamente";
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @PutMapping("/editarEnfermedad/{id}")
    public String editarEnfermedad(@RequestParam String enfermedades, @PathVariable long id) {
        try {
            antecedenteService.editarEnfermedadCronica(id,enfermedades);
            return "editado correctamente";
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @PutMapping("/editarAlergias/{id}")
    public String editarAlergias(@RequestParam String alergias, @PathVariable long id) {
        try {
            antecedenteService.editarAlergias(id,alergias);
            return "editado correctamente";
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @PutMapping("/editaMedicamentos/{id}")
    public String editarMedicamentos(@RequestParam String medicamentos, @PathVariable long id) {
        try {
            antecedenteService.editarTipoMedicamentos(id,medicamentos);
            return "editado correctamente";
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

    @PutMapping("/editaAntecedentesViaje/{id}")
    public String editarAntecedentesViaje(@RequestParam String antecedentesViaje, @PathVariable long id) {
        try {
            antecedenteService.editarViaje(id,antecedentesViaje);
            return "editado correctamente";
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedentes clínicos del paciente  no encontrados");
        }
    }

}
