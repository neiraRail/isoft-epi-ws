package cl.ufro.dci.epiws.controller;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //indica que es una clase controller
@RequestMapping("/paciente")

public class PacienteController {
    @Autowired
    private PacienteService ps;
    @PostMapping("")
    @ResponseBody
    public String agregarPaciente(@RequestBody Paciente paciente)throws Exception{
        ps.save(paciente);
        return "se ha agregado al paciente";
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
    public String eliminarPaciente(@PathVariable long rut) throws Exception{
        try {
            ps.borrarPaciente(rut);
            return "Se ha eliminado al paciente";
        }catch (NullPointerException e){
            return "No se encuentra el registro de este paciente";
        }
    }

    /**
     * @param rut
     * @return
     */
    @PostMapping("/editar/{rut}/{pacDv}/{pacNombres}/{pacApellidos}/{pacSexo}/{pacFechaNacimiento}/{pacNacionalidad}/{pacPuebloOriginario}/{pacDireccion}/{pacTelefono}")
    public String editarPaciente(@PathVariable long rut,@PathVariable String fechaFallecimiento, @PathVariable String pacNombres,@PathVariable String pacApellidos,
                                 @PathVariable String pacSexo,@PathVariable String pacFechaNacimiento, @PathVariable String pacNacionalidad,
                                 @PathVariable String pacPuebloOriginario,@PathVariable String pacDireccion,@PathVariable String pacTelefono) {
        try {
            ps.editarPaciente(rut, fechaFallecimiento, pacNombres, pacApellidos,pacSexo,pacFechaNacimiento,pacNacionalidad,pacPuebloOriginario,pacDireccion,pacTelefono);
            return "editado";
        } catch (Exception e) {
            return "No encontrado";
        }
    }

}
