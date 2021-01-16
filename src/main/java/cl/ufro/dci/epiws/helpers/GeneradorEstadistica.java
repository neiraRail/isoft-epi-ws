package cl.ufro.dci.epiws.helpers;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.service.AntecedenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GeneradorEstadistica {
    @Autowired
    private AntecedenteService antecedenteService;

    public GeneradorEstadistica(){
    }

    public float calcularPorcentajeEmbarazo(){
        ArrayList<Antecedente> antecedentes = (ArrayList<Antecedente>) antecedenteService.findAll();
        ArrayList<Antecedente> embarazos = (ArrayList<Antecedente>) antecedenteService.buscarPorEmbarazo(true);
        int total = antecedentes.size();
        int nroEmb = embarazos.size();

        return (float)nroEmb/total;
    }
}
