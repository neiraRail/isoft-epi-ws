package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AntecedenteService {
    @Autowired
    private AntecedenteRepository antecedenteRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    /** Método que permite guardar un nuevo antecedente clínico
     * @param nuevoAntecedente
     * @return
     */
    public Antecedente guardar(Antecedente nuevoAntecedente){
        long pacId = nuevoAntecedente.getPaciente().getPacRut();
        if(!pacienteRepository.existsById(pacId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }
        //Validacion embarazo
        //Validacion Ciudad y Pais
        return antecedenteRepository.save(nuevoAntecedente);
    }

    /** Método que permite borrar un antecedente clínico a partir de su id
     * @param id
     */
    public void borrar(Long id){
        if(!antecedenteRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedente no encontrado");
        }
        antecedenteRepository.deleteById(id);
    }

    /** Método que retorna un antecedente clínico a partir de su id
     * @param id
     */
    public Antecedente buscar(Long id){
        return antecedenteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Antecedente no encontrado"));
    }

    /** Método que retorna todos los antecedentes clínicos guardados
     */
    public Iterable<Antecedente> findAll(){
        return antecedenteRepository.findAll();
    }

    /**
     * Método que busca todos los antecedentes que tengan embarazo true or false
     * @param embarazo
     * @return
     */
    public Iterable<Antecedente> buscarPorEmbarazo(boolean embarazo){
        return antecedenteRepository.findAllByAntEmbarazo(embarazo);
    }
}
