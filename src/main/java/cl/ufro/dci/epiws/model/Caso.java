package cl.ufro.dci.epiws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "caso")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cas_id")
    private Long casId;



    @Column(name = "cas_fecha")
    private String casFechaNotificacion;

    @Column(name = "cas_asintomatico")
    private Boolean casAsintomatico;

    @Column(name="cas_fecha_primeros_sintomas")
    private String casFechaPrimerosSintomas;

    @Column(name="cas_semana_epidemiologica")
    private int casSemanaEpidemiologica;

    @Column(name = "cas_sintomas")
    private String casSintomas;

    @Column(name="cas_razon_sospecha")
    private String casRazonSospecha;

    @Column(name="cas_clasificacion_final")
    private String casClasificacionFinal;

    @Column(name="cas_hospitalizacion")
    private Boolean casHospitalizacion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("casos")
    @JoinColumn(name = "pac_rut")
    private Paciente paciente;

    public Caso() {}

    public Caso(String casFechaNotificacion, Boolean casAsintomatico, String casFechaPrimerosSintomas, int casSemanaEpidemiologica, String casSintomas, String casRazonSospecha, String casClasificacionFinal, Boolean casHospitalizacion) {
        this.casFechaNotificacion = casFechaNotificacion;
        this.casAsintomatico = casAsintomatico;
        this.casFechaPrimerosSintomas = casFechaPrimerosSintomas;
        this.casSemanaEpidemiologica = casSemanaEpidemiologica;
        this.casSintomas = casSintomas;
        this.casRazonSospecha = casRazonSospecha;
        this.casClasificacionFinal = casClasificacionFinal;
        this.casHospitalizacion = casHospitalizacion;
    }

    public Caso(String casFechaNotificacion, Boolean casAsintomatico, String casFechaPrimerosSintomas, int casSemanaEpidemiologica, String casSintomas, String casRazonSospecha, String casClasificacionFinal, Boolean casHospitalizacion, Paciente paciente) {
        this.casFechaNotificacion = casFechaNotificacion;
        this.casAsintomatico = casAsintomatico;
        this.casFechaPrimerosSintomas = casFechaPrimerosSintomas;
        this.casSemanaEpidemiologica = casSemanaEpidemiologica;
        this.casSintomas = casSintomas;
        this.casRazonSospecha = casRazonSospecha;
        this.casClasificacionFinal = casClasificacionFinal;
        this.casHospitalizacion = casHospitalizacion;
        this.paciente = paciente;
    }

    public Long getCasId() {
        return casId;
    }

    public void setCasId(Long casId) {
        this.casId = casId;
    }

    public String getCasFechaNotificacion() {
        return casFechaNotificacion;
    }

    public void setCasFechaNotificacion(String casFechaNotificacion) {
        this.casFechaNotificacion = casFechaNotificacion;
    }

    public Boolean getCasAsintomatico() {
        return casAsintomatico;
    }

    public void setCasAsintomatico(Boolean casAsintomatico) {
        this.casAsintomatico = casAsintomatico;
    }

    public String getCasFechaPrimerosSintomas() {
        return casFechaPrimerosSintomas;
    }

    public void setCasFechaPrimerosSintomas(String casFechaPrimerosSintomas) {
        this.casFechaPrimerosSintomas = casFechaPrimerosSintomas;
    }

    public int getCasSemanaEpidemiologica() {
        return casSemanaEpidemiologica;
    }

    public void setCasSemanaEpidemiologica(int casSemanaEpidemiologica) {
        this.casSemanaEpidemiologica = casSemanaEpidemiologica;
    }

    public String getCasSintomas() {
        return casSintomas;
    }

    public void setCasSintomas(String casSintomas) {
        this.casSintomas = casSintomas;
    }

    public String getCasRazonSospecha() {
        return casRazonSospecha;
    }

    public void setCasRazonSospecha(String casRazonSospecha) {
        this.casRazonSospecha = casRazonSospecha;
    }

    public String getCasClasificacionFinal() {
        return casClasificacionFinal;
    }

    public void setCasClasificacionFinal(String casClasificacionFinal) {
        this.casClasificacionFinal = casClasificacionFinal;
    }

    public Boolean getCasHospitalizacion() {
        return casHospitalizacion;
    }

    public void setCasHospitalizacion(Boolean casHospitalizacion) {
        this.casHospitalizacion = casHospitalizacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
