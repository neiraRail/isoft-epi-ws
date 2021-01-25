package cl.ufro.dci.epiws.helpers;

import cl.ufro.dci.epiws.model.*;
import com.itextpdf.text.pdf.PdfPTable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PDFReporteCovidTest {

    private static PDFReporteCovid reporteCovid;
    private static PersonalMedico personalMedico;
    private static Establecimiento establecimiento;
    private static ArrayList<Antecedente> antecedentes;
    private static ArrayList<Caso> casos;
    private static Paciente paciente;
    private static PdfPTable tabla;

    @BeforeAll
    static void setUp() {
        reporteCovid = new PDFReporteCovid();
        paciente = new Paciente();
        personalMedico = new PersonalMedico();
        establecimiento = new Establecimiento();
        antecedentes = new ArrayList<>();
        casos = new ArrayList<>();
        tabla = new PdfPTable(4);

        antecedentes.add(new Antecedente(0l, false, 0, "", "", "", "", ""));
        casos.add(new Caso(0l, "", false, "", 0, "", "", "", false));

        paciente.setMedico(personalMedico);
        paciente.setEstablecimiento(establecimiento);
        paciente.setAntecedenteList(antecedentes);
        paciente.setCasos(casos);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Se prueba un objeto Paciente null")
        /*
         * Se espera un NullPointerException al entregar un objeto Paciente null
         */
    void validarDatosPacienteNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.validarDatosNulosPaciente(null));
    }

    @Test
    @DisplayName("Se prueba un objeto Paciente válido")
        /*
         * Se entrega un objeto Paciente no null
         */
    void validadDatosPacienteNoNull() {
        assertEquals(paciente, reporteCovid.validarDatosNulosPaciente(paciente));
    }

    @Test
    @DisplayName("Se crea la tabla paciente con datos válidos")
        /*
         * Se entrega un objeto tabla PdfPtable de 4 columnas y un objeto Paciente no null
         */
    void crearTablaPacienteValido() {
        assertEquals(tabla, reporteCovid.crearTablaPaciente(tabla, paciente));
    }

    @Test
    @DisplayName("Se intenta crear la tabla paciente, con el objeto paciente nulo")
        /*
         * Se entrega un objeto tabla PdfPtable de 4 columnas y un objeto Paciente null
         */
    void crearTablaPacienteNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaPaciente(tabla, null));
    }

    @Test
    @DisplayName("Se intenta crear la tabla paciente, con el objeto tabla nulo")
        /*
         * Se entrega un objeto tabla PdfPtable null y un objeto Paciente no null
         */
    void crearTablaPacienteTablaPdfNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaPaciente(null, paciente));
    }

    @Test
    @DisplayName("Se intenta crear la tabla paciente, con el objeto tabla y paciente nulos")
        /*
         * Se entrega un objeto tabla PdfPtable null y un objeto Paciente null
         */
    void crearTablaPacienteTodoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaPaciente(null, null));
    }

    @Test
    @DisplayName("Se crea la tabla procedencia con datos válidos")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas, un objeto PersonalMedico y uno Establecimiento, todos datos válidos
         */
    void crearTablaProcedenciaValido() {
        assertEquals(tabla, reporteCovid.crearTablaProcedencia(tabla, personalMedico, establecimiento));
    }

    @Test
    @DisplayName("Se crea la tabla procedencia con el objeto PersonalMedico nulo")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas, un objeto PersonalMedico nulo y un Establecimiento no nulo
         */
    void crearTablaProcedenciaPersonalMedicoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaProcedencia(tabla, null, establecimiento));
    }

    @Test
    @DisplayName("Se crea la tabla procedencia con el objeto establecimiento nulo")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas, un objeto PersonalMedico válido y un objeto Establecimiento nulo
         */
    void crearTablaProcedenciaEstablecimientoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaProcedencia(tabla, personalMedico, null));
    }

    @Test
    @DisplayName("Se crea la tabla procedencia con el objeto tabla null")
        /*
         * Se entrega un objeto PdfPtable null y un objeto PersonalMedico y un objeto Establecimiento no nulos
         */
    void crearTablaProcedenciaTablaPdfNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaProcedencia(null, personalMedico, establecimiento));
    }

    @Test
    @DisplayName("Se crea la tabla procedencia con todos los objetos null")
        /*
         * Se entrega un objeto PdfPtable null, un objeto PersonalMedico null y un objeto Establecimiento null
         */
    void crearTablaProcedenciaTodoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaProcedencia(null, null, null));
    }

    @Test
    @DisplayName("Se crea la tabla antecedentes con datos válidos")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas y un objeto Antecedente no null
         */
    void crearTablaAntecedentesValido() {
        assertEquals(tabla, reporteCovid.crearTablaAntecedentes(tabla, antecedentes.get(0)));
    }

    @Test
    @DisplayName("Se prueba la tabla antecedentes con el objeto antecedente null")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas y un objeto Antecedente null
         */
    void crearTablaAntecedentesNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaAntecedentes(tabla, null));
    }

    @Test
    @DisplayName("Se prueba la tabla antecedentes con el objeto tabla null")
        /*
         * Se entrega un objeto PdfPtable null y un objeto Antecedente no null
         */
    void crearTablaAntecedentesTablaPdfNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaAntecedentes(null, antecedentes.get(0)));
    }

    @Test
    @DisplayName("Se prueba la tabla antecedentes el objeto tabla y antecedente null")
        /*
         * Se entrega un objeto PdfPtable null y un objeto Antecedente null
         */
    void crearTablaAntecedentesTodoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaAntecedentes(null, null));
    }

    @Test
    @DisplayName("Se crea la tabla síntomas con datos válidos")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas y un objeto Caso no null
         */
    void crearTablaSintomasValido() {
        assertEquals(tabla, reporteCovid.crearTablaSintomas(tabla, casos.get(0)));
    }

    @Test
    @DisplayName("Se prueba la tabla síntomas con el objeto caso null")
        /*
         * Se entrega un objeto PdfPtable de 4 columnas y un objeto Caso null
         */
    void crearTablaSintomasNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaSintomas(tabla, null));
    }

    @Test
    @DisplayName("Se prueba la tabla síntomas con el objeto tabla null")
        /*
         * Se entrega un objeto PdfPtable null y un objeto Caso no null
         */
    void crearTablaSintomasTablaPdfNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaSintomas(null, casos.get(0)));
    }

    @Test
    @DisplayName("Se prueba la tabla síntomas con el objeto tabla y caso null")
        /*
         * Se entrega un objeto PdfPtable null y un objeto Caso null
         */
    void crearTablaSintomasTodoNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearTablaSintomas(null, null));
    }

    @Test
    @DisplayName("Se prueba crear un reporte con el objeto PersonalMedico null")
    void crearReportePersonalMedicoNull() {
        paciente.setMedico(null);
        assertThrows(NullPointerException.class, () -> reporteCovid.crearReporte(paciente));
    }

    @Test
    @DisplayName("Se prueba crear un reporte con el objeto Establecimiento null")
    void crearReporteEstablecimientoNull() {
        paciente.setEstablecimiento(null);
        assertThrows(NullPointerException.class, () -> reporteCovid.crearReporte(paciente));
    }

    @Test
    @DisplayName("Se prueba crear un reporte con la lista antecedentes null")
    void crearReporteAntecedentesListNull() {
        paciente.setAntecedenteList(null);
        assertThrows(NullPointerException.class, () -> reporteCovid.crearReporte(paciente));
    }

    @Test
    @DisplayName("Se prueba crear un reporte con la lista casos null")
    void crearReporteCasosListNull() {
        paciente.setCasos(null);
        assertThrows(NullPointerException.class, () -> reporteCovid.crearReporte(paciente));
    }

    @Test
    @DisplayName("Se prueba crear un reporte con el objeto Paciente null")
    void crearReporteCasosPacienteNull() {
        assertThrows(NullPointerException.class, () -> reporteCovid.crearReporte(null));
    }

}