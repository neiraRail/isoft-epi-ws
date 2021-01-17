package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seremi")
public class Comuna {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "com_id")
    private long comId;

    @Column(name = "com_nombre")
    private String comNombre;

    @ManyToOne()
    @JoinColumn(name = "reg_id")
    private Region region;

    @OneToMany(mappedBy = "comuna")
    private List<Establecimiento> establecimientos = new ArrayList<>();

    public Comuna() {

    }

    //Constructor sin relaciones
    public Comuna(Long comId, String comNombre) {
        this.comId = comId;
        this.comNombre = comNombre;
    }


    //Constructor con relaciones
    public Comuna(Long comId, String comNombre, Region region) {
        this.comId = comId;
        this.comNombre = comNombre;
        this.region = region;
    }

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
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
}
