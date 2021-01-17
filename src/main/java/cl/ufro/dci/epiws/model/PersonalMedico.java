package cl.ufro.dci.epiws.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_medico")
public class PersonalMedico {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "med_run")
    private Long medRun;

    @Column(name = "med_nombres")
    private String medNombres;

    @Column(name = "med_apellidos")
    private String medApellidos;

    @Column(name = "med_telefono")
    private String medTelefono;

    @Column(name = "med_email")
    private String medEmail;

    @OneToMany(mappedBy = "medico")
    private List<Paciente> pacientes = new ArrayList<>();

    public PersonalMedico() {
    }

    public PersonalMedico(Long medRun, String medNombres, String medApellidos, String medTelefono, String medEmail) {
        this.medRun = medRun;
        this.medNombres = medNombres;
        this.medApellidos = medApellidos;
        this.medTelefono = medTelefono;
        this.medEmail = medEmail;
    }

    public Long getMedRun() {
        return medRun;
    }

    public void setMedRun(Long medRun) {
        this.medRun = medRun;
    }

    public String getMedNombres() {
        return medNombres;
    }

    public void setMedNombres(String medNombres) {
        this.medNombres = medNombres;
    }

    public String getMedApellidos() {
        return medApellidos;
    }

    public void setMedApellidos(String medApellidos) {
        this.medApellidos = medApellidos;
    }

    public String getMedTelefono() {
        return medTelefono;
    }

    public void setMedTelefono(String medTelefono) {
        this.medTelefono = medTelefono;
    }

    public String getMedEmail() {
        return medEmail;
    }

    public void setMedEmail(String medEmail) {
        this.medEmail = medEmail;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
