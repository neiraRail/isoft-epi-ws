package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.*;
import cl.ufro.dci.epiws.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //indica que es una clase controller
@RequestMapping("/paciente")


public class controlerPaciente {
    @Autowired
    private PacienteService ps;
    private  ArrayList<Paciente> pacientes=new ArrayList<>();

    /**
     * @return
     */
    @PostMapping(" ")
    public  ArrayList<Paciente>  listaPaciente () {
        Paciente paciente = new Paciente(1,"",null,null,null,"","","","","","","","");
        Paciente paciente2 = new Paciente(2,"",null,null,null,"","","","","","","","");
        RegistroPaciente rp = new RegistroPaciente();
        ps.save(paciente);
        rp.setListaPaciente(pacientes);
        rp.ingresarPaciente(paciente);
        rp.ingresarPaciente(paciente2);
        return rp.getListaPaciente();
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
    public Paciente buscaPaciente(@PathVariable long rut) {
        RegistroPaciente rp = new RegistroPaciente();
        ps.buscarPaciente(rut);
        rp.setListaPaciente(pacientes);
        return rp.buscarPaciente(rut);
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
    @GetMapping("/editar/{rut}")
    public Paciente editarPaciente(@PathVariable long rut){
        RegistroPaciente rp = new RegistroPaciente();
        rp.setListaPaciente(pacientes);
        return rp.editarPaciente(rut,"modi","Marcos Fabián","Navarrete Sepulveda","Masculino","hoy","Chilena","selknam","temuquito","133");
    }
}
