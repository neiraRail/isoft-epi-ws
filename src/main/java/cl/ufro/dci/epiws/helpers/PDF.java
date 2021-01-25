package cl.ufro.dci.epiws.helpers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.Optional;

public class PDF {

    public PdfPTable crearTabla(int numColumnas, int ancho, float separacionVertical) {
        if (numColumnas > 0 && ancho > 0 && separacionVertical > 0) {
            PdfPTable tabla = new PdfPTable(numColumnas);
            tabla.setWidthPercentage(ancho);
            tabla.setSpacingBefore(separacionVertical);
            return tabla;
        } else {
            throw new IllegalArgumentException("Solo números mayores que cero");
        }
    }

    public PdfPCell crearCelda(String texto, Font fuente, int espacioCeldas, int alineacion) {
        if (Optional.ofNullable(fuente).isPresent()) {
            if (espacioCeldas > 0 && alineacion >= 0) {
                if (Optional.ofNullable(texto).isPresent()) {
                    PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
                    celda.setColspan(espacioCeldas);
                    celda.setHorizontalAlignment(alineacion);
                    return celda;
                } else {
                    PdfPCell celdaTextoVacio = new PdfPCell(new Phrase(" ", fuente));
                    celdaTextoVacio.setColspan(espacioCeldas);
                    celdaTextoVacio.setHorizontalAlignment(alineacion);
                    return celdaTextoVacio;
                }
            } else {
                throw new IllegalArgumentException("Solo números mayores que cero");
            }
        } else {
            throw new NullPointerException("La Fuente no puede ser nula");
        }
    }

    public Image cargarImagen(String rutaImagen) throws IOException, BadElementException {
        if (Optional.ofNullable(rutaImagen).isPresent()) {
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
