package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AntecedenteService {
    @Autowired
    private AntecedenteRepository antecedenteRepository;

    /** Método que permite guardar un nuevo antecedente clínico
     * @param nuevoAntecedente
     */
    public void guardar(Antecedente nuevoAntecedente){
        antecedenteRepository.save(nuevoAntecedente);
    }

    /** Método que permite borrar un antecedente clínico a partir de su id
     * @param id
     */
    public void borrar(Long id){
        antecedenteRepository.deleteById(id);
    }

    /** Método que retorna un antecedente clínico a partir de su id
     * @param id
     */
    public Optional<Antecedente> buscar(Long id){
        return antecedenteRepository.findById(id);
    }

    /** Método que retorna todos los antecedentes clínicos guardados
     */
    public Iterable<Antecedente> findAll(){
        return antecedenteRepository.findAll();
    }
    /** Método que permite  editar Embarazo del antecedente clínico
     * @param id
     * @param embarazo
     */
    public void editarEmbarazo(Long id, Boolean embarazo){
        antecedenteRepository.findById(id).get().setAntEmbarazo(embarazo);
    }
    /** Método que permite editar enefermedades crónicas del antecedente clínico
     * @param id
     * @param enfermedad
     */
    public void editarEnfermedadCronica(Long id, String enfermedad){
        antecedenteRepository.findById(id).get().setAntEnfermedadCronica(enfermedad);
    }

    public void editarAlergias(Long id, String alergias){
        antecedenteRepository.findById(id).get().setAntAlergias(alergias);
    }

    /** Método que permite editar los medicaementos del antecedente clínico
     * @param id
     * @param medicamentos
     */
    public void editarTipoMedicamentos(Long id, String medicamentos){
        antecedenteRepository.findById(id).get().setAntMedicamentos(medicamentos);
    }

    /** Método que permite editar los antecedentes de viaje al extrajero del antecedente clínico
     * @param id
     * @param antecedenteViaje
     */
    public void editarViaje(Long id, String antecedenteViaje){
        antecedenteRepository.findById(id).get().setAntViajeExtranjero(antecedenteViaje);
    }
}
