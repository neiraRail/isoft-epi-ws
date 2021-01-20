package cl.ufro.dci.epiws.helpers;

import cl.ufro.dci.epiws.model.Paciente;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PDFReporteCovidTest {

    private static PDFReporteCovid reporteCovid;
    private Paciente paciente;

    @BeforeAll
    static void setUp() {
        reporteCovid=new PDFReporteCovid();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Se espera un NullPointerException al ingresar un objeto paciente null")
    void validarDatosPacienteNull(){
        paciente=null;
        assertThrows(NullPointerException.class, () -> reporteCovid.validarDatosNulosPaciente(paciente));
    }

    @Test
    @DisplayName("Se espera que se retorne el paciente al ingresar un objeto paciente no null")
    void validarDatosPacienteNoNull() {
        paciente=new Paciente();
        assertEquals(paciente,reporteCovid.validarDatosNulosPaciente(paciente));
    }
}