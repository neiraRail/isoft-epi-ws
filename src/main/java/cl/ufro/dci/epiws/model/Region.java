package cl.ufro.dci.epiws.model;

import javax.persistence.*;
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
    private List<Seremi> seremiList;

    public Region() {
    }

    public Region(String rgnNombre) {
        this.rgnNombre = rgnNombre;
    }

    public Region(Long rgnId, String rgnNombre) {
        this.rgnId = rgnId;
        this.rgnNombre = rgnNombre;
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

    public List<Seremi> getSeremiList() {
        return seremiList;
    }

    public void setSeremiList(List<Seremi> seremiList) {
        this.seremiList = seremiList;
    }

}