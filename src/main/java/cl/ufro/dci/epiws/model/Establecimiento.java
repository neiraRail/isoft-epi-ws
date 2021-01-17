package cl.ufro.dci.epiws.model;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "com_id")
    private Comuna comuna;

    @OneToMany(mappedBy = "establecimiento")
    private List<Paciente> pacienteList;



    public Establecimiento(Long estId, String estNombre, String estDireccion, Comuna comuna) {
        this.estId = estId;
        this.estNombre = estNombre;
        this.estDireccion = estDireccion;
        this.comuna = comuna;
    }

    public Establecimiento() {

    }
    //constructor sin id.
    public Establecimiento(String estNombre, String estDireccion, Comuna comuna, List<Paciente> pacienteList) {
        this.estNombre = estNombre;
        this.estDireccion = estDireccion;
        this.comuna = comuna;
        this.pacienteList = pacienteList;
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

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

}



