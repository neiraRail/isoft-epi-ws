package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "pac_rut")
    private Long pacRut;

    @Column(name = "pac_fechaFallecimiento")
    private String pacFechaFallecimiento;

    @ManyToOne
    @JoinColumn(name = "est_id")
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name="med_run")
    private PersonalMedico medico;

    @OneToMany(mappedBy = "paciente")
    private List<Caso> casos;

    @OneToMany(mappedBy = "paciente")
    private List<Antecedente> antecedenteList;

    @Column(name = "pac_nombres")
    private String pacNombres;

    @Column(name = "pac_apellidos")
    private String pacApellidos;

    @Column(name = "pac_sexo")
    private String pacSexo;

    @Column(name = "pac_fecha_nacimiento")
    private String pacFechaNacimiento;

    @Column(name = "pac_nacionalidad")
    private String pacNacionalidad;

    @Column(name = "pac_pueblo_originario")
    private String pacPuebloOriginario;

    @Column(name = "pac_direccion")
    private String pacDireccion;

    @Column(name = "pac_telefono")
    private String pacTelefono;


    /**
     * @param pacRut
     * @param pacFechaFallecimiento
     * @param establecimiento
     * @param casos
     * @param antecedenteList
     * @param pacNombres
     * @param pacApellidos
     * @param pacSexo
     * @param pacFechaNacimiento
     * @param pacNacionalidad
     * @param pacPuebloOriginario
     * @param pacDireccion
     * @param pacTelefono
     */
    public Paciente(long pacRut,  Establecimiento establecimiento, List<Caso> casos, List<Antecedente> antecedenteList, String pacNombres, String pacApellidos, String pacSexo, String pacFechaNacimiento,String pacFechaFallecimiento, String pacNacionalidad, String pacPuebloOriginario, String pacDireccion, String pacTelefono) {
        this.pacRut = pacRut;
        this.pacFechaFallecimiento= pacFechaFallecimiento;
        this.establecimiento = establecimiento;
        this.casos = casos;
        this.antecedenteList = antecedenteList;
        this.pacNombres = pacNombres;
        this.pacApellidos = pacApellidos;
        this.pacSexo = pacSexo;
        this.pacFechaNacimiento = pacFechaNacimiento;
        this.pacNacionalidad = pacNacionalidad;
        this.pacPuebloOriginario = pacPuebloOriginario;
        this.pacDireccion = pacDireccion;
        this.pacTelefono = pacTelefono;
    }

    public Paciente(){

    }

    public Long getPacRut() {
        return pacRut;
    }
    public void setPacRut(long pacRut) {
        this.pacRut = pacRut;
    }
    public String getPacFechaFallecimiento(){
        return pacFechaFallecimiento;
    }
    public void setPacFechaFallecimiento(String pacFechaFallecimiento) {
        this.pacFechaFallecimiento = pacFechaFallecimiento;
    }
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public List<Caso> getCasos() {
        return casos;
    }
    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }
    public List<Antecedente> getAntecedenteList() {
        return antecedenteList;
    }
    public void setAntecedenteList(List<Antecedente> antecedenteList) {
        this.antecedenteList = antecedenteList;
    }
    public String getPacNombres() {
        return pacNombres;
    }

    public void setPacNombres(String pacNombres) {
        this.pacNombres = pacNombres;
    }

    public String getPacApellidos() {
        return pacApellidos;
    }

    public void setPacApellidos(String pacApellidos) {
        this.pacApellidos = pacApellidos;
    }

    public String getPacSexo() {
        return pacSexo;
    }

    public void setPacSexo(String pacSexo) {
        this.pacSexo = pacSexo;
    }

    public String getPacFechaNacimiento() {
        return pacFechaNacimiento;
    }

    public void setPacFechaNacimiento(String pacFechaNacimiento) {
        this.pacFechaNacimiento = pacFechaNacimiento;
    }

    public String getPacNacionalidad() {
        return pacNacionalidad;
    }

    public void setPacNacionalidad(String pacNacionalidad) {
        this.pacNacionalidad = pacNacionalidad;
    }

    public String getPacPuebloOriginario() {
        return pacPuebloOriginario;
    }

    public void setPacPuebloOriginario(String pacPuebloOriginario) {
        this.pacPuebloOriginario = pacPuebloOriginario;
    }

    public String getPacDireccion() {
        return pacDireccion;
    }

    public void setPacDireccion(String pacDireccion) {
        this.pacDireccion = pacDireccion;
    }

    public String getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public PersonalMedico getMedico() {
        return medico;
    }

    public void setMedico(PersonalMedico medico) {
        this.medico = medico;
    }
}