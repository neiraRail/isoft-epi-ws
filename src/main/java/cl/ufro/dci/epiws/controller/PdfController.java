package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.helpers.PDFReporteCovid;
import cl.ufro.dci.epiws.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import cl.ufro.dci.epiws.repository.PacienteRepository;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PacienteRepository pacienteRepository;

    //para generar el pdf se debe ingresar a la ruta http://localhost:8080/pdf/generar/
    //lo genera en local
    @GetMapping(path = "/generar/{pacRut}/")
    public void generarPdf(@PathVariable("pacRut") Long pacRut, HttpServletResponse response) {
        Paciente paciente = pacienteRepository.findById(pacRut).get();
        PDFReporteCovid reporte = new PDFReporteCovid();
        reporte.crearReporte(reporte.validarDatosNulosPaciente(paciente));
    }

    //Método no probado
    @CrossOrigin("*")
    @GetMapping(path = "/exportar/{pacRut}/")
    public void exportarPDF(@PathVariable("pacRut") Long pacRut, HttpServletResponse response) {
        Paciente paciente = pacienteRepository.findById(pacRut).get();
        PDFReporteCovid reporte = new PDFReporteCovid();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=paciente.pdf");
        reporte.exportar(reporte.validarDatosNulosPaciente(paciente), response);
    }
}
