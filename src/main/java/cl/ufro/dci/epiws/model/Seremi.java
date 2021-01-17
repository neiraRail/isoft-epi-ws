package cl.ufro.dci.epiws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "seremi")
public class Seremi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ser_id")
    private Long serId;

    @Column(name = "ser_nombre")
    private String serNombre;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("seremiList")
    @JoinColumn(name = "rgn_id")
    private Region region;

    public Seremi() {

    }

    public Seremi(String serNombre) {
        this.serNombre = serNombre;
    }

    public Seremi(Long serId, String serNombre, Region region) {
        this.serId = serId;
        this.serNombre = serNombre;
        this.region = region;
    }

    public Long getSerId() {
        return serId;
    }

    public void setSerId(Long serId) {
        this.serId = serId;
    }

    public String getSerNombre() {
        return serNombre;
    }

    public void setSerNombre(String serNombre) {
        this.serNombre = serNombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}