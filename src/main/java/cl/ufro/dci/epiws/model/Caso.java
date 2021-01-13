package cl.ufro.dci.epiws.model;
import javax.persistence.*;

@Entity
@Table(name = "caso")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cas_id")
    private Long casId;

    @Column(name = "cas_sintomas")
    private String casSintomas;

    @Column(name = "cas_fecha")
    private String casFecha;

    @Column(name = "cas_asintomatico")
    private Boolean casAsintomatico;

    @ManyToOne
    @JoinColumn(name = "pac_rut")
    private Paciente paciente;

    public Caso() {}

    public Caso(Long casId, String casSintomas, String casFecha, Boolean casAsintomatico) {
        this.casId = casId;
        this.casSintomas = casSintomas;
        this.casFecha = casFecha;
        this.casAsintomatico = casAsintomatico;
    }
    public Caso(String casSintomas, String casFecha, Boolean casAsintomatico, Paciente paciente) {
        this.casSintomas = casSintomas;
        this.casFecha = casFecha;
        this.casAsintomatico = casAsintomatico;
        this.paciente = paciente;
    }

    public Long getCasId() {
        return casId;
    }

    public void setCasId(Long casId) {
        this.casId = casId;
    }

    public String getCasSintomas() {
        return casSintomas;
    }

    public void setCasSintomas(String casSintomas) {
        this.casSintomas = casSintomas;
    }

    public String getCasFecha() {
        return casFecha;
    }

    public void setCasFecha(String casFecha) {
        this.casFecha = casFecha;
    }

    public Boolean getCasAsintomatico() {
        return casAsintomatico;
    }

    public void setCasAsintomatico(Boolean casAsintomatico) {
        this.casAsintomatico = casAsintomatico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
