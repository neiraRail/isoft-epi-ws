package cl.ufro.dci.epiws.model;

import javax.persistence.*;

@Entity
@Table(name = "antecedente")
public class Antecedente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ant_id")
    private Long antId;

    @Column(name = "ant_embarazo")
    private Boolean antEmbarazo;

    @Column(name = "ant_semana_gestacion")
    private int antSemanaGestacion;

    @Column(name = "and_enfermedad_cronica")
    private String antEnfermedadCronica;

    @Column(name = "ant_alergias")
    private String antAlergias;

    @Column(name = "ant_tipo_sangre")
    private String antTipoSangre;

    @Column(name = "ant_medicamentos")
    private String antMedicamentos;

    @Column(name = "ant_viaje_al_extranjero")
    private String antViajeExtranjero;

    @ManyToOne
    @JoinColumn(name = "pac_run")
    private Paciente paciente;

    public Antecedente() {

    }

    //Constructor sin relaciones
    public Antecedente(Long antId , Boolean antEmbarazo, int antSemanaGestacion, String antEnfermedadCronica, String antAlergias, String antTipoSangre, String antMedicamentos, String antViajeExtranjero) {
        this.antId = antId;
        this.antEmbarazo = antEmbarazo;
        this.antSemanaGestacion = antSemanaGestacion;
        this.antEnfermedadCronica = antEnfermedadCronica;
        this.antAlergias = antAlergias;
        this.antTipoSangre = antTipoSangre;
        this.antMedicamentos = antMedicamentos;
        this.antViajeExtranjero = antViajeExtranjero;
    }

    //Constructor con relaciones
    public Antecedente(Long antId, Boolean antEmbarazo, int antSemanaGestacion, String antEnfermedadCronica, String antAlergias, String antTipoSangre, String antMedicamentos, String antViajeExtranjero, Paciente paciente) {
        this.antId = antId;
        this.antEmbarazo = antEmbarazo;
        this.antSemanaGestacion = antSemanaGestacion;
        this.antEnfermedadCronica = antEnfermedadCronica;
        this.antAlergias = antAlergias;
        this.antTipoSangre = antTipoSangre;
        this.antMedicamentos = antMedicamentos;
        this.antViajeExtranjero = antViajeExtranjero;
        this.paciente = paciente;
    }

    public Long getAntId() {
        return antId;
    }

    public void setAntId(Long antId) {
        this.antId = antId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Boolean getAntEmbarazo() {
        return antEmbarazo;
    }

    public void setAntEmbarazo(Boolean antEmbarazo) {
        this.antEmbarazo = antEmbarazo;
    }

    public int getAntSemanaGestacion() {
        return antSemanaGestacion;
    }

    public void setAntSemanaGestacion(int antSemanaGestacion) {
        this.antSemanaGestacion = antSemanaGestacion;
    }

    public String getAntEnfermedadCronica() {
        return antEnfermedadCronica;
    }

    public void setAntEnfermedadCronica(String antEnfermedadCronica) {
        this.antEnfermedadCronica = antEnfermedadCronica;
    }

    public String getAntAlergias() {
        return antAlergias;
    }

    public void setAntAlergias(String antAlergias) {
        this.antAlergias = antAlergias;
    }

    public String getAntTipoSangre() {
        return antTipoSangre;
    }

    public void setAntTipoSangre(String antTipoSangre) {
        this.antTipoSangre = antTipoSangre;
    }

    public String getAntMedicamentos() {
        return antMedicamentos;
    }

    public void setAntMedicamentos(String antMedicamentos) {
        this.antMedicamentos = antMedicamentos;
    }

    public String getAntViajeExtranjero() {
        return antViajeExtranjero;
    }

    public void setAntViajeExtranjero(String antViajeExtranjero) {
        this.antViajeExtranjero = antViajeExtranjero;
    }
}
