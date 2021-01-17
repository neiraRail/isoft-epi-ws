package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_id")
    private Long regId;

    @Column(name = "reg_nombre")
    private String regNombre;

    @OneToMany(mappedBy = "region")
    private List<Comuna> comuna = new ArrayList<>();

    public Region() {
    }

    public Region(Long regId, String regNombre) {
        this.regId = regId;
        this.regNombre = regNombre;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public String getRegNombre() {
        return regNombre;
    }

    public void setRegNombre(String regNombre) {
        this.regNombre = regNombre;
    }

    public List<Comuna> getComuna() {
        return comuna;
    }

    public void setComuna(List<Comuna> comuna) {
        this.comuna = comuna;
    }
}
