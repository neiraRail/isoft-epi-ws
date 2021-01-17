package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.helpers.PDFReporteCovid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {


    //para generar el pdf se debe ingresar a la ruta http://localhost:8080/pdf/generar/
    @PostMapping(path = "/generar/{pacRut}/")
    public void generarPdf(@PathVariable("pacRut") Long pacRut) {
    }
}
