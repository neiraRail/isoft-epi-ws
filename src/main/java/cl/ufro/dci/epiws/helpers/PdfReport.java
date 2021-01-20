package cl.ufro.dci.epiws.helpers;

import cl.ufro.dci.epiws.model.*;
import com.itextpdf.text.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfReport {
    private Document document;
    private String rutaImagen = "logo.png";
    private Image image;

    private Font fuenteNormal;
    private Font fuenteNegrita;

    PdfPCell celda = new PdfPCell();

    public PdfReport() {
        this.document = new Document();
        this.rutaImagen=rutaImagen = "src/main/resources/logo.png";
        this.fuenteNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        this.fuenteNegrita =new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    }

    public void generarPDF(Paciente paciente){
        try{
            PdfWriter.getInstance(document, new FileOutputStream("Formulario.pdf"));
            document.open();
            image = Image.getInstance(rutaImagen);
            image.setAbsolutePosition(25, 755);
            image.scaleAbsolute(90,90);
            document.add(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        agregaTitulo();
        crearTablaDatosPaciente(paciente);
        crearTablaDatosProcedencia(paciente);
        crearTablaDatosAntecedentes(paciente);
        crearTablaDatosSintomas(paciente);
        document.close();
    }

    private void agregaTitulo(){
        Chunk primerTitulo=new Chunk("                        Formulario notificación inmediata y envío de muestras\n",new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD));
        Chunk segundoTitulo=new Chunk("                        a confirmación IRA grave y 2019-nCoV",new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD));

        try{
            document.add(primerTitulo);
            document.add(segundoTitulo);
            document.add(new Phrase("\n\n\n"));
        }catch (DocumentException e){
            e.printStackTrace();
        }

    }

    private void crearTablaDatosPaciente(Paciente paciente){
        PdfPTable tablaInformacionPersonal = new PdfPTable(4);
        PdfPTable tablaFechaNacimiento = new PdfPTable(16);

        tablaInformacionPersonal.setWidthPercentage(100);
        tablaFechaNacimiento.setWidthPercentage(100);

        //tabla información del paciente, parte 1
        celda.setPhrase(new Phrase("Información del Paciente", fuenteNegrita));
        celda.setColspan(2);
        tablaInformacionPersonal.addCell(celda);
        tablaInformacionPersonal.addCell(new Phrase("", fuenteNormal));
        tablaInformacionPersonal.addCell(new Phrase("", fuenteNormal));

        tablaInformacionPersonal.addCell(new Phrase("Rut:", fuenteNormal));
        tablaInformacionPersonal.addCell(new Phrase("Comuna:", fuenteNormal));
        if(paciente.getEstablecimiento() == null){
            tablaInformacionPersonal.addCell(new Phrase(" "));
        }else {
            tablaInformacionPersonal.addCell(new Phrase(paciente.getEstablecimiento().getComuna().getComNombre(), fuenteNormal));
        }

        tablaInformacionPersonal.addCell(new Phrase("Nombres:", fuenteNormal));
        tablaInformacionPersonal.addCell(new Phrase(paciente.getPacNombres(), fuenteNormal));
        tablaInformacionPersonal.addCell(new Phrase("Región:", fuenteNormal));
        if(paciente.getEstablecimiento() == null){
            tablaInformacionPersonal.addCell(new Phrase(" "));
        }else {
            tablaInformacionPersonal.addCell(new Phrase(paciente.getEstablecimiento().getComuna().getRegion().getRgnNombre(), fuenteNormal));
        }

        tablaInformacionPersonal.addCell(new Phrase("Apellidos:", fuenteNormal));
        celda.setPhrase(new Phrase(paciente.getPacApellidos(), fuenteNormal));
        celda.setColspan(3);
        tablaInformacionPersonal.addCell(celda);

        tablaInformacionPersonal.addCell(new Phrase("Dirección", fuenteNormal));
        celda.setPhrase(new Phrase(paciente.getPacDireccion(), fuenteNormal));
        celda.setColspan(3);
        tablaInformacionPersonal.addCell(celda);


        //tabla información del paciente, parte 2
        celda.setPhrase(new Phrase("Sexo:", fuenteNormal));
        celda.setColspan(2);
        tablaFechaNacimiento.addCell(celda);

        celda.setPhrase(new Phrase(paciente.getPacSexo(), fuenteNormal));
        celda.setColspan(6);
        tablaFechaNacimiento.addCell(celda);

        celda.setPhrase(new Phrase("Teléfono:", fuenteNormal));
        celda.setColspan(2);
        tablaFechaNacimiento.addCell(celda);

        celda.setPhrase(new Phrase(paciente.getPacTelefono(), fuenteNormal));
        celda.setColspan(6);
        tablaFechaNacimiento.addCell(celda);

        //tabla de información del paciente, parte 3
        tablaFechaNacimiento.setWidthPercentage(100);

        celda.setPhrase(new Phrase("Fecha Nacimiento:", fuenteNormal));
        celda.setColspan(3);
        tablaFechaNacimiento.addCell(celda);

        celda.setPhrase(new Phrase(paciente.getPacFechaNacimiento(), fuenteNormal));
        celda.setColspan(13);
        tablaFechaNacimiento.addCell(celda);

        //tabla de información del paciente, parte 4
        tablaFechaNacimiento.setWidthPercentage(100);

        celda.setPhrase(new Phrase("Pueblo originario:", fuenteNormal));
        celda.setColspan(3);
        tablaFechaNacimiento.addCell(celda);
        celda.setPhrase(new Phrase(paciente.getPacPuebloOriginario(), fuenteNormal));
        celda.setColspan(13);
        tablaFechaNacimiento.addCell(celda);

        celda.setPhrase(new Phrase("Nacionalidad:", fuenteNormal));
        celda.setColspan(3);
        tablaFechaNacimiento.addCell(celda);
        celda.setPhrase(new Phrase(paciente.getPacNacionalidad(), fuenteNormal));
        celda.setColspan(13);
        tablaFechaNacimiento.addCell(celda);

        try{
            this.document.add(tablaInformacionPersonal);
            this.document.add(tablaFechaNacimiento);
            document.add(new Phrase("\n"));
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }

    private void crearTablaDatosProcedencia(Paciente paciente){
        PdfPTable tablaProcedencia = new PdfPTable(4);

        tablaProcedencia.setWidthPercentage(100);

        celda.setPhrase(new Phrase("Datos de la Procedencia", fuenteNegrita));
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);
        celda.setPhrase(new Phrase("", fuenteNormal));
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);

        tablaProcedencia.addCell(new Phrase("Profesional Responsable:", fuenteNormal));
        if(paciente.getMedico() == null){
            celda.setPhrase(new Phrase(" "));
        }else {
            celda.setPhrase(new Phrase(paciente.getMedico().getMedNombres()+ " "+paciente.getMedico().getMedApellidos(),fuenteNormal));
        }
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);

        celda.setPhrase(new Phrase("Rut:", fuenteNormal));
        if(paciente.getMedico() == null){
            celda.setPhrase(new Phrase(" "));
        }else {
            celda.setPhrase(new Phrase(paciente.getMedico().getMedRut()+paciente.getMedico().getMedDv(), fuenteNormal));
        }
        celda.setColspan(3);
        tablaProcedencia.addCell(celda);

        celda.setPhrase(new Phrase("Nombre hospital:", fuenteNormal));
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);
        if(paciente.getEstablecimiento() == null){
            celda.setPhrase(new Phrase(" "));
        }else {
            celda.setPhrase(new Phrase(paciente.getEstablecimiento().getEstNombre(), fuenteNormal));
        }
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);

        celda.setPhrase(new Phrase("Dirección hospital:", fuenteNormal));
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);
        if(paciente.getEstablecimiento() == null){
            celda.setPhrase(new Phrase(" "));
        }else {
            celda.setPhrase(new Phrase(paciente.getEstablecimiento().getEstDireccion(), fuenteNormal));
        }
        celda.setColspan(2);
        tablaProcedencia.addCell(celda);

        try{
            this.document.add(tablaProcedencia);
            this.document.add(new Phrase("\n"));
        }catch(DocumentException e){
            e.printStackTrace();
        }

    }

    private void crearTablaDatosAntecedentes(Paciente paciente){
        PdfPTable tablaAntecedentesClinicos = new PdfPTable(4);
        tablaAntecedentesClinicos.setWidthPercentage(100);
        celda.setPhrase(new Phrase("Antecedentes Clínicos/Epidemiológicos", fuenteNegrita));
        celda.setColspan(4);
        tablaAntecedentesClinicos.addCell(celda);
        tablaAntecedentesClinicos.addCell(new Phrase("Embarazo:", fuenteNormal));
        if(paciente.getAntecedenteList().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            if(paciente.getAntecedenteList().get(0).getAntEmbarazo()) {
                celda.setPhrase(new Phrase("SI", fuenteNormal));
            }
            else {
                celda.setPhrase(new Phrase("NO", fuenteNormal));
            }
        }

        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);

        tablaAntecedentesClinicos.addCell(new Phrase("Enfermedad Crónica:", fuenteNormal));
        if(paciente.getAntecedenteList().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getAntecedenteList().get(0).getAntEnfermedadCronica(), fuenteNormal));
        }
        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);

        tablaAntecedentesClinicos.addCell(new Phrase("Alergias:", fuenteNormal));
        if(paciente.getAntecedenteList().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getAntecedenteList().get(0).getAntAlergias(), fuenteNormal));
        }
        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);

        tablaAntecedentesClinicos.addCell(new Phrase("Tipo de sangre:", fuenteNormal));
        if(paciente.getAntecedenteList().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getAntecedenteList().get(0).getAntTipoSangre(), fuenteNormal));
        }
        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);

        tablaAntecedentesClinicos.addCell(new Phrase("Medicamentos:", fuenteNormal));
        if(paciente.getAntecedenteList().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getAntecedenteList().get(0).getAntMedicamentos(), fuenteNormal));
        }
        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);

        celda.setPhrase(new Phrase("Viajó al extranjero en los 14 días previos al inicio de los síntomas:", fuenteNormal));
        celda.setColspan(3);
        tablaAntecedentesClinicos.addCell(celda);
        if(paciente.getAntecedenteList().size() == 0){
            tablaAntecedentesClinicos.addCell(new Phrase(" ", fuenteNormal));
        }else {
            tablaAntecedentesClinicos.addCell(new Phrase(paciente.getAntecedenteList().get(0).getAntViajeExtranjero(), fuenteNormal));
        }

        try{
            document.add(tablaAntecedentesClinicos);
            document.add(new Phrase("\n"));
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }

    private void crearTablaDatosSintomas(Paciente paciente){
        PdfPTable tablaSintomas = new PdfPTable(24);
        tablaSintomas.setWidthPercentage(100);

        celda.setPhrase(new Phrase("Síntomas", fuenteNegrita));
        celda.setColspan(24);
        tablaSintomas.addCell(celda);

        celda.setPhrase(new Phrase("Fecha:", fuenteNormal));
        celda.setColspan(2);
        tablaSintomas.addCell(celda);
        if(paciente.getCasos().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getCasos().get(0).getCasFecha(), fuenteNormal));
        }
        celda.setColspan(2);
        tablaSintomas.addCell(celda);
        if(paciente.getCasos().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            celda.setPhrase(new Phrase(paciente.getCasos().get(0).getCasSintomas(), fuenteNormal));
        }
        celda.setColspan(20);
        tablaSintomas.addCell(celda);


        celda.setPhrase(new Phrase("Asintomático", fuenteNormal));
        celda.setColspan(12);
        tablaSintomas.addCell(celda);
        if(paciente.getCasos().size() == 0){
            celda.setPhrase(new Phrase(" ", fuenteNormal));
        }else {
            if(paciente.getCasos().get(0).getCasAsintomatico()){
                celda.setPhrase(new Phrase("Positivo", fuenteNormal));
            }else{
                celda.setPhrase(new Phrase("Negativo", fuenteNormal));
            }
        }
        celda.setColspan(12);
        tablaSintomas.addCell(celda);

        try{
            document.add(tablaSintomas);
            document.add(new Phrase("\n"));
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }

}
