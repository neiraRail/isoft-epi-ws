package cl.ufro.dci.epiws.helpers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.Optional;

public class PDF {

    public PDF() {
        //Constructor vacío por framework
    }

    public PdfPTable crearTabla(int numColumnas, int ancho, float separacionVertical) {
        try {
            PdfPTable tabla = new PdfPTable(numColumnas);
            tabla.setWidthPercentage(ancho);
            tabla.setSpacingBefore(separacionVertical);
            return tabla;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Solo números superior a 0");
        }
    }

    public PdfPCell crearCelda(String texto, Font fuente, int espacioCeldas, int alineacion) {
        try {
            PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
            celda.setColspan(espacioCeldas);
            celda.setHorizontalAlignment(alineacion);
            if (texto != null) {
                return celda;
            } else {
                celda = new PdfPCell(new Phrase(" ", fuente));
                celda.setColspan(espacioCeldas);
                celda.setHorizontalAlignment(alineacion);
                return celda;
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("La fuente no debe ser nula");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Solo números superior a 0");
        }
    }

    public Image cargarImagen(String rutaImagen) throws IOException, BadElementException {
        if (rutaImagen != null) {
            Image imagen = Image.getInstance(rutaImagen);
            imagen.setAbsolutePosition(25, 755);
            imagen.scaleAbsolute(90, 90);
            return imagen;
        } else {
            throw new NullPointerException("Ruta nula");
        }
    }

    public Paragraph creaTitulo(String texto, Font fuente, int alineacion, int separacionVertical) {
        if (Optional.ofNullable(fuente).isPresent()) {
            if (alineacion >= 0 && separacionVertical > 0) {
                Paragraph parrafo = new Paragraph(texto, fuente);
                parrafo.setAlignment(alineacion);
                parrafo.setSpacingBefore(separacionVertical);
                return parrafo;
            } else {
                throw new IllegalArgumentException("Solo números mayores que 0");
            }
        } else {
            throw new NullPointerException("La fuente es nula");
        }
    }

}
