package cl.ufro.dci.epiws.helpers;

import cl.ufro.dci.epiws.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

public class PDFReporteCovid {

    private PDF pdf;
    private ArrayList<Font> fuentes;

    public PDFReporteCovid() {
        this.pdf = new PDF();
        iniciarFuentes();
    }

    private void iniciarFuentes() {
        this.fuentes = new ArrayList<>();
        fuentes.add(new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
        fuentes.add(new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
        fuentes.add(new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
    }

    public void crearReporte(Paciente paciente) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Formulario.pdf"));
            document.open();
            document.add(pdf.cargarImagen("src/main/resources/ministerio_salud_logo.png"));
            document.add(pdf.creaTitulo("Formulario notificación inmediata y envío de reporte COVID-19", fuentes.get(0), Element.ALIGN_CENTER, 55));
            document.add(crearTablaPaciente(pdf.crearTabla(4, 100, 40), paciente));
            document.add(crearTablaProcedencia(pdf.crearTabla(4, 100, 40), paciente.getMedico(), paciente.getEstablecimiento()));
            document.add(crearTablaAntecedentes(pdf.crearTabla(4, 100, 40), paciente.getAntecedenteList().get(0)));
            document.add(crearTablaSintomas(pdf.crearTabla(4, 100, 40), paciente.getCasos().get(0)));
        } catch (DocumentException | MalformedURLException | IllegalArgumentException | FileNotFoundException e) {
            e.getClass();
        } catch (IOException e) {
            e.getClass();
        } finally {
            document.close();
        }
    }

    public void exportar(Paciente paciente, HttpServletResponse response) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(pdf.cargarImagen("src/main/resources/ministerio_salud_logo.png"));
            document.add(pdf.creaTitulo("Formulario notificación inmediata y envío de reporte COVID-19", fuentes.get(0), Element.ALIGN_CENTER, 55));
            document.add(crearTablaPaciente(pdf.crearTabla(4, 100, 40), paciente));
            document.add(crearTablaProcedencia(pdf.crearTabla(4, 100, 40), paciente.getMedico(), paciente.getEstablecimiento()));
            document.add(crearTablaAntecedentes(pdf.crearTabla(4, 100, 40), paciente.getAntecedenteList().get(0)));
            document.add(crearTablaSintomas(pdf.crearTabla(4, 100, 40), paciente.getCasos().get(0)));
        } catch (DocumentException | MalformedURLException | IllegalArgumentException | NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    //Falta agregar estado de paciente
    public PdfPTable crearTablaPaciente(PdfPTable tabla, Paciente paciente) {
        //Primera Fila
        tabla.addCell(pdf.crearCelda("Información Paciente", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Segunda Fila
        tabla.addCell(pdf.crearCelda("Nombres:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacNombres(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Tercera Fila
        tabla.addCell(pdf.crearCelda("Apellidos:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacApellidos(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Cuarta Fila
        tabla.addCell(pdf.crearCelda("Run:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(String.valueOf(paciente.getPacRut()), fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda("Teléfono:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacTelefono(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Quinta Fila
        tabla.addCell(pdf.crearCelda("Nacionalidad:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacNacionalidad(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda("Fecha Nacimiento:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacFechaNacimiento(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Sexta Fila
        tabla.addCell(pdf.crearCelda("Fecha Fallecimiento:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacFechaFallecimiento(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Septima Fila
        tabla.addCell(pdf.crearCelda("Sexo:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacSexo(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Octaba Fila
        tabla.addCell(pdf.crearCelda("Pueblo Originario:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacPuebloOriginario(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Novena Fila
        tabla.addCell(pdf.crearCelda("Datos Residencia", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Decima Fila
        tabla.addCell(pdf.crearCelda("Dirección:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(paciente.getPacDireccion(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        return tabla;
    }

    public PdfPTable crearTablaProcedencia(PdfPTable tabla, PersonalMedico medico, Establecimiento establecimiento) {
        //Primera Fila
        tabla.addCell(pdf.crearCelda("Datos Procedencia", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Segunda Fila
        tabla.addCell(pdf.crearCelda("Profesional Responsable:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(medico.getMedNombres() + medico.getMedApellidos(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Tercera Fila
        tabla.addCell(pdf.crearCelda("Datos Establecimiento", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Cuarta Fila
        tabla.addCell(pdf.crearCelda("Nombre Hospital:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(establecimiento.getEstNombre(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Quinta Fila
        tabla.addCell(pdf.crearCelda("Dirección Hospital:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(establecimiento.getEstDireccion(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        return tabla;
    }

    public PdfPTable crearTablaAntecedentes(PdfPTable tabla, Antecedente antecedente) {
        //Primera Fila
        tabla.addCell(pdf.crearCelda("Antecedentes Clínicos/Epidemiológicos", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Segunda Fila
        tabla.addCell(pdf.crearCelda("Embarazo:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(String.valueOf(antecedente.getAntEmbarazo()), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Tercera Fila
        tabla.addCell(pdf.crearCelda("Enfermedad Crónica:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(antecedente.getAntEnfermedadCronica(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Cuarta Fila
        tabla.addCell(pdf.crearCelda("Alergias:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(antecedente.getAntAlergias(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Quinta Fila
        tabla.addCell(pdf.crearCelda("Tipo de Sangre:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(antecedente.getAntTipoSangre(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Sexta Fila
        tabla.addCell(pdf.crearCelda("Medicamentos:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(antecedente.getAntMedicamentos(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Septima Fila
        tabla.addCell(pdf.crearCelda("Viajó al extranjero en los 14 días previos al inicio de los síntomas:", fuentes.get(2), 3, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(antecedente.getAntViajeExtranjero(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        return tabla;
    }

    public PdfPTable crearTablaSintomas(PdfPTable tabla, Caso caso) {
        //Primera Fila
        tabla.addCell(pdf.crearCelda("Síntomas", fuentes.get(1), 4, Element.ALIGN_CENTER));
        //Segunda Fila
        tabla.addCell(pdf.crearCelda("Fecha Notificación:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(caso.getCasFechaNotificacion(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Tercera Fila
        tabla.addCell(pdf.crearCelda("Semana Epidemiológica:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(String.valueOf(caso.getCasSemanaEpidemiologica()), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Cuarta Fila
        tabla.addCell(pdf.crearCelda("Asintomatico:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(String.valueOf(caso.getCasAsintomatico()), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Quinta Fila
        tabla.addCell(pdf.crearCelda("Fecha Primeros Sintomas:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(caso.getCasFechaPrimerosSintomas(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Sexta Fila
        tabla.addCell(pdf.crearCelda("Razón de la sospecha:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(caso.getCasRazonSospecha(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Septima Fila
        tabla.addCell(pdf.crearCelda("Sintomas:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(caso.getCasSintomas(), fuentes.get(2), 3, Element.ALIGN_LEFT));
        //Octava Fila
        tabla.addCell(pdf.crearCelda("Clasificación Final:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(caso.getCasClasificacionFinal(), fuentes.get(2), 1, Element.ALIGN_LEFT));
        //Novena Fila
        tabla.addCell(pdf.crearCelda("Hospitalización:", fuentes.get(2), 1, Element.ALIGN_LEFT));
        tabla.addCell(pdf.crearCelda(String.valueOf(caso.getCasHospitalizacion()), fuentes.get(2), 1, Element.ALIGN_LEFT));
        return tabla;
    }

    public Paciente validarDatosNulosPaciente(Paciente paciente) {
        if (Optional.ofNullable(paciente).isPresent()) {
            if (paciente.getEstablecimiento() == null) {
                paciente.setEstablecimiento(new Establecimiento(0l, "", "", new Comuna(0l, "", new Region(0l, ""))));
            }
            if (paciente.getMedico() == null) {
                paciente.setMedico(new PersonalMedico(0l, "", ""));
            }
            if (paciente.getAntecedenteList() == null) {
                ArrayList<Antecedente> antecedentesVacios = new ArrayList<>();
                antecedentesVacios.add(new Antecedente(0l, false,0, "", "", "", "", ""));
                paciente.setAntecedenteList(antecedentesVacios);
            }
            if (paciente.getCasos() == null) {
                ArrayList<Caso> casosVacio = new ArrayList<>();
                casosVacio.add(new Caso(0l, "", false, "", 0, "", "", "", false));
                paciente.setCasos(casosVacio);
            }
        } else {
            throw new NullPointerException("Paciente no puede ser nulo");
        }
        return paciente;
    }

}
