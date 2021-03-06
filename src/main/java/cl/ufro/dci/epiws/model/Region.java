package cl.ufro.dci.epiws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rgn_id")
    private Long rgnId;

    @Column(name = "rgn_nombre")
    private String rgnNombre;

    @OneToMany(mappedBy = "region")
    @JsonIgnoreProperties(value = "region",allowSetters = true)
    private List<Comuna> comunaList;

    public Region() {
    }

    public Region(String rgnNombre) {
        this.rgnNombre = rgnNombre;
        this.comunaList = new ArrayList<>();
    }

    public Region(Long rgnId, String rgnNombre) {
        this.rgnId = rgnId;
        this.rgnNombre = rgnNombre;
        this.comunaList = new ArrayList<>();
    }

    public Long getRgnId() {
        return rgnId;
    }

    public void setRgnId(Long rgnId) {
        this.rgnId = rgnId;
    }

    public String getRgnNombre() {
        return rgnNombre;
    }

    public void setRgnNombre(String rgnNombre) {
        this.rgnNombre = rgnNombre;
    }

    public List<Comuna> getComunaList() {
        return comunaList;
    }

    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }

}



