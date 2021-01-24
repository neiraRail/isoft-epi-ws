package cl.ufro.dci.epiws.helpers;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Se prueba la clase PDF")
class PDFTest {

    private static PDF pdf;

    @BeforeAll
    static void setUp() {
        pdf = new PDF();
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
    void crearTablaConColumnasMayorQueCero() {
        PdfPTable tabla = pdf.crearTabla(4, 3, 20);
        assertEquals(4, tabla.getNumberOfColumns());
    }

    @Test
    void crearTablaConColumnasMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(-1, 4, 40));
    }

    @Test
    void crearTablaConColumnasIgualQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(0, 4, 40));
    }

    @Test
    void crearTablaConAnchoMayorQueCero() {
        PdfPTable tabla = pdf.crearTabla(4, 100, 20);
        assertEquals(100, tabla.getWidthPercentage());
    }

    @Test
    void crearTablaConAnchoMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(4, -10000, 40));
    }

    @Test
    void crearTablaConAnchoIgualQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(4, 0, 40));
    }

    @Test
    void crearTablaConSeparacionVerticalMayorQueCero() {
        PdfPTable tabla = pdf.crearTabla(4, 100, 20);
        assertEquals(20, tabla.getSpacingBefore());
    }

    @Test
    void crearTablaConSeparacionVerticalMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(4, 100, -1));
    }

    @Test
    void crearTablaConSeparacionVerticalIgualQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearTabla(4, 100, 0));
    }

    @Test
    @DisplayName("Se espera que la celda sea correcta con todos los parámetros no nulos y los int mayores que 0")
    void crearCeldaEsperada() {
        PdfPCell celda = pdf.crearCelda("Esta es una nueva celda", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL), 4, Element.ALIGN_CENTER);
        assertEquals("Esta es una nueva celda", celda.getPhrase().getContent());
    }

    @Test
    @DisplayName("Se espera que el texto de la celda sea vacío si el texto es null")
    void crearCeldaTextoNulo() {
        PdfPCell celda = pdf.crearCelda(null, new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL), 4, Element.ALIGN_CENTER);
        assertEquals(" ", celda.getPhrase().getContent());
    }

    @Test
    @DisplayName("Se espera un NullPointerException si la fuente es null")
    void crearCeldaConFuenteNull() {
        assertThrows(NullPointerException.class, () -> pdf.crearCelda("Hello", null, 10, 2));
    }

    @Test
    @DisplayName("Se espera un IlegalArgumentException si el espacio de la celda es menor a 0")
    void crearCeldaConEspacioMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearCelda("Hello World", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), -1, 2));
    }

    @Test
    @DisplayName("Se espera un IlegalArgumentException si el espacio de la celda es menor a 0")
    void crearCeldaConAlineacionMenorQueCero() {
        assertThrows(IllegalArgumentException.class, () -> pdf.crearCelda("Hello World", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD), 1, -1));
    }

    @Test
    void cargarImagen() {
    }

}