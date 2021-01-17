package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado_paciente")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etd_id")
    private Long etdId;

    @Column(name = "etd_nombre")
    private String etdNombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "etd_id")
    private List<Paciente> pacienteList = new ArrayList<>();

    public Estado() {}

    public Estado(Long etdId, String etdNombre, List<Paciente> pacienteList) {
        this.etdId = etdId;
        this.etdNombre = etdNombre;
        this.pacienteList = pacienteList;
    }

    public Long getEtdId() {
        return etdId;
    }

    public void setEtdId(Long etdId) {
        this.etdId = etdId;
    }

    public String getEtdNombre() {
        return etdNombre;
    }

    public void setEtdNombre(String etdNombre) {
        this.etdNombre = etdNombre;
    }

    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }
}
