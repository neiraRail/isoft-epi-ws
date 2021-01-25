package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.Arrays;


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
        if(validarDatosAntecedente(nuevoAntecedente)){
            return antecedenteRepository.save(nuevoAntecedente);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validarDatosAntecedente(Antecedente antecedente){
        long pacId = antecedente.getPaciente().getPacRut();
        if(antecedente.getAntSemanasGest()==null){
            antecedente.setAntSemanasGest(0);
        }
        if(!pacienteRepository.existsById(pacId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");

        else if(!validarCampoTipoSangre(antecedente.getAntTipoSangre()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de Sangre vacio o invalido");

        else if(!antecedente.getAntEmbarazo()){

            if(antecedente.getAntSemanasGest()!=0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Semanas de gestación no aplican");
            }
        }
        else if(!validarSemanas(antecedente.getAntSemanasGest()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Semanas no valido");

        //Validacion Ciudad y Pais
        String antViaje = antecedente.getAntViajeExtranjero();
        if(validarCampoAntViaje(antViaje)){
            if(!antViaje.equals(""))
            antecedente.setAntViajeExtranjero(reformatearAntViaje(antViaje));
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Antecedente de Viaje no valido");
        }

        return true;
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

    private boolean validarSemanas(Integer any) {
        return any >= 0 && any < 42;
    }

    public boolean validarCampoTipoSangre(String tipoSangre){
        if(tipoSangre==null){
            return false;
        }
        else {
            return tipoSangre.matches("^([ABO]|AB)[+-]$");
        }
    }

    public boolean validarCampoAntViaje(String antViaje) {
        return antViaje.matches("^\\s*[a-z\\sA-Z]*\\s*,\\s*[a-z\\sA-Z]*\\s*$") || antViaje.matches("");
    }

    public String reformatearAntViaje(String string) {
        String[] partes = string.split(",");
        String[] result = new String[2];
        for (int i =0; i<partes.length;i++) {
            partes[i] = partes[i].trim();
            partes[i] = partes[i].substring(0, 1).toUpperCase() + partes[i].substring(1);
        }

        return partes[0]+", "+partes[1];

    }
}
