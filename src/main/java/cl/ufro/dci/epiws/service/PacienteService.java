package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> list() {
        return pacienteRepository.findAll();
    }


    public void save(Paciente paciente)throws Exception{
        if(!pacienteRepository.existsById(paciente.getPacRut())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        } pacienteRepository.save(paciente);
    }

    public void borrarPaciente(Long id)throws Exception {
        if(!pacienteRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPaciente(Long id)throws Exception{
        return Optional.ofNullable(pacienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado")));
    }

    public void editarPaciente(long rut,String fechaFallecimiento,String pacNombres,String pacApellidos,String pacSexo,String pacFechaNacimiento,
                               String pacNacionalidad,String pacPuebloOriginario,String pacDireccion,String pacTelefono) throws Exception {
        Paciente pacienteMod = buscarPaciente(rut).get();
        pacienteMod.setPacFechaFallecimiento(fechaFallecimiento);
        pacienteMod.setPacNombres(pacNombres);
        pacienteMod.setPacApellidos(pacApellidos);
        pacienteMod.setPacSexo(pacSexo);
        pacienteMod.setPacFechaNacimiento(pacFechaNacimiento);
        pacienteMod.setPacNacionalidad(pacNacionalidad);
        pacienteMod.setPacPuebloOriginario(pacPuebloOriginario);
        pacienteMod.setPacDireccion(pacDireccion);
        pacienteMod.setPacTelefono(pacTelefono);
        pacienteRepository.save(pacienteMod);
    }
}