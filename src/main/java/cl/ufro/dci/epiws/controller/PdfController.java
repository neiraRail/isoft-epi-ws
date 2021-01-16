package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.helpers.PdfReport;
import cl.ufro.dci.epiws.model.*;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PacienteRepository pacienteRepository;

    //para generar el pdf se debe ingresar a la ruta http://localhost:8080/pdf/generar/
    @PostMapping(path="/generar/{pacRut}/")
    public void generarPdf(@PathVariable("pacRut") Long pacRut){
        Paciente paciente = pacienteRepository.findById(pacRut).get();
        PdfReport pdf = new PdfReport();
        pdf.generarPDF(paciente);
    }
}
