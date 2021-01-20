package cl.ufro.dci.epiws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comuna")
public class Comuna {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "com_id")
    private Long comId;

    @Column(name = "com_nombre")
    private String comNombre;

    @ManyToOne
    @JoinColumn(name = "rgn_id")
    private Region region;

    @OneToMany(mappedBy = "comuna")
    @JsonIgnoreProperties("comuna")
    private List<Establecimiento> establecimientoList;

    public Comuna() {
    }

    public Comuna(Long comId, String comNombre, Region region, List<Establecimiento> establecimientoList) {
        this.comId = comId;
        this.comNombre = comNombre;
        this.region = region;
        this.establecimientoList = establecimientoList;
    }

    public Comuna(String comNombre, Region region, List<Establecimiento> establecimientoList) {
        this.comNombre = comNombre;
        this.region = region;
        this.establecimientoList = establecimientoList;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }
}
