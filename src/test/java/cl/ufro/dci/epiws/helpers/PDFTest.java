package cl.ufro.dci.epiws.helpers;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Se prueba la clase PDF")
class PDFTest {

    private static PDF pdf;

    @BeforeAll
    static void setUp() {
        pdf = new PDF();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Se espera que el título sea correcto con todos los parámetros no nulos y los int mayores que 0")
    void creaTituloEsperado() {
        Paragraph titulo = pdf.creaTitulo("Este es el título de un PDF", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), Element.ALIGN_CENTER, 20);
        assertEquals("Este es el título de un PDF", titulo.getContent());
    }

    @Test
    @DisplayName("Se espera que el título sea vacío si el texto es null")
    void creaTituloTextoNull() {
        Paragraph titulo = pdf.creaTitulo(null, new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), Element.ALIGN_CENTER, 20);
        assertEquals("", titulo.getContent());
    }

    @Test
    @DisplayName("Se espera un NullPointerException si la fuente es null")
    void creaTituloConFuenteNull() {
        assertThrows(NullPointerException.class, () -> pdf.creaTitulo("Hola", null, Element.ALIGN_CENTER, 20));
    }

    @Test
    @DisplayName("Se espera un IlegalArgumentException si la alineación es menor a 0")
    void creaTituloConAlineacionMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.creaTitulo("Hola", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), -1, 20));
    }

    @Test
    @DisplayName("Se espera un IlegalArgumentException si la separacion vertical es menor a 0")
    void creaTituloConSeparacionVerticalMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.creaTitulo("Hola", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD), 1, -20));
    }

    @Test
    void crearTabla() {
    }

    @Test
    void crearCelda() {
    }

    @Test
    void cargarImagen() {
    }

}