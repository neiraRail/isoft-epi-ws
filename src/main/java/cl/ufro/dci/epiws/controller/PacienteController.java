package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController //indica que es una clase controller
@RequestMapping("/paciente")


public class PacienteController {
    @Autowired
    private PacienteService ps;
    private  ArrayList<Paciente> pacientes=new ArrayList<>();

    /**
     * @return
     */
    @PostMapping(" ")
    public  String  listaPaciente () {
        Paciente paciente = new Paciente(1,"",null,null,null,"","","","","","","","");
        Paciente paciente2 = new Paciente(2,"",null,null,null,"","","","","","","","");
        ps.save(paciente);
        return "agregado";
    }
    @PostMapping("/agregar/{rut}/{pacDv}/{pacNombres}/{pacApellidos}/{pacSexo}/{pacFechaNacimiento}/{pacNacionalidad}/{pacPuebloOriginario}/{pacDireccion}/{pacTelefono}")
    @ResponseBody
    public String agregar(@PathVariable long rut, @PathVariable String pacDv, @PathVariable String pacNombres, @PathVariable String pacApellidos,@PathVariable String pacSexo,@PathVariable String pacFechaNacimiento,@PathVariable String pacNacionalidad,@PathVariable String pacPuebloOriginario,@PathVariable String pacDireccion,@PathVariable String pacTelefono){
        Paciente paciente = new Paciente(rut,pacDv,null,null,null,pacNombres,pacApellidos,pacSexo,pacFechaNacimiento,pacNacionalidad,pacPuebloOriginario,pacDireccion,pacTelefono);
        ps.save(paciente);
        return "El paciente se ha agregado";
    }

    /**
     * @param rut
     * @return
     */
    @GetMapping("/buscar/{rut}")
    public String buscaPaciente(@PathVariable long rut) throws Exception {
        try {
            Paciente pacienteEncontrado = ps.buscarPaciente(rut).get();
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
    @GetMapping("/eliminar/{rut}")
    public String eliminarPaciente(@PathVariable long rut){
        ps.borrarPaciente(rut);
        return "se ha eliminado el paciente";
    }

    /**
     * @param rut
     * @return
     */
    @PostMapping("/editar/{rut}/{pacPuebloOriginario}")
    public String editarPaciente(@PathVariable long rut,@PathVariable String pacPuebloOriginario) {
        try {
            ps.editarPaciente(rut, pacPuebloOriginario);
            return "editado";
        } catch (Exception e) {
            return "No encontrado";
        }
    }

}
