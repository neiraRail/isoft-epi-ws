package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "establecimiento")
public class Establecimiento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "est_id")
    private Long estId;

    @Column(name = "est_nombre")
    private String estNombre;

    @Column(name = "est_direccion")
    private String estDireccion;

    @ManyToOne()
    @JoinColumn(name = "com_id")
    private Region region;

    @OneToMany(mappedBy = "establecimiento")
    private List<Paciente> pacientes = new ArrayList<>();

    public Establecimiento() {

    }

    //Constructor sin relaciones
    public Establecimiento(Long estId, String estNombre, String estDireccion) {
        this.estId = estId;
        this.estNombre = estNombre;
        this.estDireccion = estDireccion;
    }

    //Constructor con relaciones
    public Establecimiento(Long estId, String estNombre, String estDireccion, Region region) {
        this.estId = estId;
        this.estNombre = estNombre;
        this.estDireccion = estDireccion;
        this.region = region;
    }

    public Long getEstId() {
        return estId;
    }

    public void setEstId(Long estId) {
        this.estId = estId;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public String getEstDireccion() {
        return estDireccion;
    }

    public void setEstDireccion(String estDireccion) {
        this.estDireccion = estDireccion;
    }

    public Region getComuna() {
        return region;
    }

    public void setComuna(Region region) {
        this.region = region;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
