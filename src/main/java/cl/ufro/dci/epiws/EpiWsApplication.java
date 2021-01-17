package cl.ufro.dci.epiws;

import cl.ufro.dci.epiws.helpers.PDFReporteCovid;
import cl.ufro.dci.epiws.model.Paciente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EpiWsApplication {

    public static void main(String[] args) {
        //SpringApplication.run(EpiWsApplication.class, args);
        PDFReporteCovid reporte = new PDFReporteCovid();
        Paciente paciente = new Paciente("20103953-3", "Rodrigo Alexis", "Pardo Gatica", "Masculino", "08/01/1999", "", "Chileno", "Ninguno", "Pje. Fénix 01040 Villa Ilusión", "999827643", "Temuco", "Araucania");
        Optional<Paciente> couldBeenPaciente = Optional.of(paciente);
        reporte.crearReporte(reporte.validarDatosNulosPaciente(couldBeenPaciente));
    }

}
