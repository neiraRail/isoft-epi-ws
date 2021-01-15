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


    public void save(Paciente paciente)throws Exception{
            pacienteRepository.save(paciente);
    }

    public void borrarPaciente(Long id)throws Exception {
        if (buscarPaciente(id) != null) {
            pacienteRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
    }

    public Optional<Paciente> buscarPaciente(Long id)throws Exception{
        if (pacienteRepository.findById(id).isPresent()){
            return pacienteRepository.findById(id);
        }else{
            throw new NullPointerException();
        }
    }

    public void editarPaciente(long rut, String pacPuebloOriginario) throws Exception {
        Paciente pacienteModificado;
        pacienteModificado = buscarPaciente(rut).get();
        pacienteModificado.setPacPuebloOriginario(pacPuebloOriginario);
        pacienteRepository.save(pacienteModificado);
    }
}