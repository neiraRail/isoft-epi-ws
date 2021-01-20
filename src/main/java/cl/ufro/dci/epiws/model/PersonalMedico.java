package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_medico")
public class PersonalMedico {

    @Id
    @Column(name = "med_rut")
    private Long medRut;

    @Column(name = "med_dv")
    private String medDv;

    @Column(name = "med_nombres")
    private String medNombres;

    @Column(name = "med_apellidos")
    private String medApellidos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "med_rut")
    private List<Paciente> pacienteList = new ArrayList<>();

    public PersonalMedico () {}

    public PersonalMedico(Long medRut, String medDv, String medNombres, String medApellidos, List<Paciente> pacienteList) {
        this.medRut = medRut;
        this.medDv = medDv;
        this.medNombres = medNombres;
        this.medApellidos = medApellidos;
        this.pacienteList = pacienteList;
    }

    public Long getMedRut() {
        return medRut;
    }

    public void setMedRut(Long medRut) {
        this.medRut = medRut;
    }

    public String getMedDv() {
        return medDv;
    }

    public void setMedDv(String medDv) {
        this.medDv = medDv;
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

    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

}



