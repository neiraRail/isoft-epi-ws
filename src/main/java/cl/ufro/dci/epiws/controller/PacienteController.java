package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController //indica que es una clase controller
@RequestMapping("/api/paciente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT})

public class PacienteController {
    @Autowired
    private PacienteService ps;

    @PostMapping("")
    @ResponseBody
    public String agregarPaciente(@RequestBody Paciente paciente)throws Exception {
        if (Optional.ofNullable(paciente).isPresent()) { //si no es nulo el paciente
            ps.save(paciente);
            return "El paciente se ha agregado";
        }else{
            throw new NullPointerException("objeto nulo");
        }
    }
    /**
     * @return
     */
    @GetMapping("/buscar")
    @ResponseBody
    public String buscaPaciente(@RequestBody Paciente paciente) throws Exception {
        try {
            Paciente pacienteEncontrado = ps.buscarPaciente(paciente.getPacRut()).get();
            return "\nNombres: " + pacienteEncontrado.getPacNombres()+"\n Apellidos: "+pacienteEncontrado.getPacApellidos()+"\n Sexo: "+pacienteEncontrado.getPacSexo()
                    +"\n FechaNacimiento: "+pacienteEncontrado.getPacFechaNacimiento()+"\n Nacionalidad: "+pacienteEncontrado.getPacNacionalidad()
                    +"\n PuebloOriginario: "+ pacienteEncontrado.getPacPuebloOriginario()+"\n Direccion: "+pacienteEncontrado.getPacDireccion()
                    +"\n Telefono: "+ pacienteEncontrado.getPacTelefono();
        }catch (NullPointerException e){
            return "no encontrado";
        }
    }


    /**
     * @param rut
     * @return
     */
    @DeleteMapping("/eliminar/{rut}")
    public String eliminarPaciente(@PathVariable long rut) throws Exception{
        try {
            ps.borrarPaciente(rut);
            return "Se ha eliminado al paciente";
        }catch (NullPointerException e){
            return "No se encuentra el registro de este paciente";
        }
    }

    /**
    // * @param rut
    // * @return
     */
    @PutMapping("/editar/{rut}")
    public String editarPaciente(@PathVariable Long rut,@RequestBody Paciente paciente) {
        try {
            ps.editarPaciente(rut,paciente.getPacFechaFallecimiento(), paciente.getPacNombres(),paciente.getPacApellidos(),paciente.getPacSexo(),paciente.getPacFechaNacimiento(),paciente.getPacNacionalidad(),paciente.getPacPuebloOriginario(),paciente.getPacDireccion(),paciente.getPacTelefono());
            return "editado";
        } catch (Exception e) {
            return "No encontrado";
        }
    }

}