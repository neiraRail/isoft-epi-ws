package cl.ufro.dci.epiws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pac_run")
    private Long pacRun;

    @Column(name = "pac_nombres")
    private String pacNombres;

    @Column(name = "pac_apellidos")
    private String pacApellidos;

    @Column(name = "pac_sexo")
    private String pacSexo;

    @Column(name = "pac_fecha_nacimiento")
    private String pacFechaNacimiento;

    @Column(name = "pac_fecha_fallecimiento")
    private String pacFechaFallecimiento;

    @Column(name = "pac_nacionalidad")
    private String pacNacionalidad;

    @Column(name = "pac_pueblo_originario")
    private String pacPuebloOriginario;

    @Column(name = "pac_direccion")
    private String pacDireccion;

    @Column(name = "pac_telefono")
    private String pacTelefono;

    @Column(name = "pac_comuna_residencia")
    private String pacComunaResidencia;

    @Column(name = "pac_region_residencia")
    private String pacRegionResidencia;

    @ManyToOne
    @JoinColumn(name = "est_id")
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "med_run")
    private PersonalMedico medico;

    @ManyToOne
    @JoinColumn(name = "etd_id")
    private EstadoPaciente estado;

    @OneToMany(mappedBy = "paciente")
    private List<Caso> casos = new ArrayList<>();

    @OneToMany(mappedBy = "paciente")
    private List<Antecedente> antecedentes = new ArrayList<>();

    public Paciente() {

    }

    //Constructor sin relaciones
    public Paciente(Long pacRun, String pacNombres, String pacApellidos, String pacSexo, String pacFechaNacimiento, String pacFechaFallecimiento, String pacNacionalidad, String pacPuebloOriginario, String pacDireccion, String pacTelefono, String pacComunaResidencia, String pacRegionResidencia) {
        this.pacRun = pacRun;
        this.pacNombres = pacNombres;
        this.pacApellidos = pacApellidos;
        this.pacSexo = pacSexo;
        this.pacFechaNacimiento = pacFechaNacimiento;
        this.pacFechaFallecimiento = pacFechaFallecimiento;
        this.pacNacionalidad = pacNacionalidad;
        this.pacPuebloOriginario = pacPuebloOriginario;
        this.pacDireccion = pacDireccion;
        this.pacTelefono = pacTelefono;
        this.pacComunaResidencia = pacComunaResidencia;
        this.pacRegionResidencia = pacRegionResidencia;
    }

    //Constructor con relaciones
    public Paciente(Long pacRun, String pacNombres, String pacApellidos, String pacSexo, String pacFechaNacimiento, String pacFechaFallecimiento, String pacNacionalidad, String pacPuebloOriginario, String pacDireccion, String pacTelefono, String pacComunaResidencia, String pacRegionResidencia, Establecimiento establecimiento, PersonalMedico medico, EstadoPaciente estado) {
        this.pacRun = pacRun;
        this.pacNombres = pacNombres;
        this.pacApellidos = pacApellidos;
        this.pacSexo = pacSexo;
        this.pacFechaNacimiento = pacFechaNacimiento;
        this.pacFechaFallecimiento = pacFechaFallecimiento;
        this.pacNacionalidad = pacNacionalidad;
        this.pacPuebloOriginario = pacPuebloOriginario;
        this.pacDireccion = pacDireccion;
        this.pacTelefono = pacTelefono;
        this.pacComunaResidencia = pacComunaResidencia;
        this.pacRegionResidencia = pacRegionResidencia;
        this.establecimiento = establecimiento;
        this.medico = medico;
        this.estado = estado;
    }

    public Long getPacRun() {
        return pacRun;
    }

    public void setPacRun(Long pacRun) {
        this.pacRun = pacRun;
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

    public String getPacFechaFallecimiento() {
        return pacFechaFallecimiento;
    }

    public void setPacFechaFallecimiento(String pacFechaFallecimiento) {
        this.pacFechaFallecimiento = pacFechaFallecimiento;
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

    public String getPacComunaResidencia() {
        return pacComunaResidencia;
    }

    public void setPacComunaResidencia(String pacComunaResidencia) {
        this.pacComunaResidencia = pacComunaResidencia;
    }

    public String getPacRegionResidencia() {
        return pacRegionResidencia;
    }

    public void setPacRegionResidencia(String pacRegionResidencia) {
        this.pacRegionResidencia = pacRegionResidencia;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public PersonalMedico getMedico() {
        return medico;
    }

    public void setMedico(PersonalMedico medico) {
        this.medico = medico;
    }

    public EstadoPaciente getEstado() {
        return estado;
    }

    public void setEstado(EstadoPaciente estado) {
        this.estado = estado;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public List<Antecedente> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<Antecedente> antecedentes) {
        this.antecedentes = antecedentes;
    }
}
