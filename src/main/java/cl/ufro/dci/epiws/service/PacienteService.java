package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> list() {
        return pacienteRepository.findAll();
    }


    public void save(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void borrarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public void editarPaciente(long rut, String pacPuebloOriginario){
        Paciente pacienteModificado = buscarPaciente(rut).get();
        pacienteModificado.setPacPuebloOriginario(pacPuebloOriginario);
        pacienteRepository.save(pacienteModificado);
    }
}