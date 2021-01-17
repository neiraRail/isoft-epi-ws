package cl.ufro.dci.epiws.model;

import javax.persistence.*;

@Entity
@Table(name = "estado")
public class EstadoPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etd_id")
    private Long etdId;

    @Column(name = "etd_nombre")
    private String etdNombre;

    public EstadoPaciente() {}


    public EstadoPaciente(Long etdId, String etdNombre) {
        this.etdId = etdId;
        this.etdNombre = etdNombre;
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
}
