package cl.ufro.dci.epiws.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroPaciente {
    private ArrayList<Paciente> listaPaciente = new ArrayList<>();

    /**
     * @return
     */
    public ArrayList<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    /**
     * @param listaPaciente
     */
    public void setListaPaciente(ArrayList<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    /**
     * @param pacRut
     * @param pacDv
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
     * @return
     */
    public boolean ingresarPaciente(long pacRut, String pacDv, Establecimiento establecimiento, List<Caso> casos, List<Antecedente> antecedenteList,
                                    String pacNombres, String pacApellidos, String pacSexo, String pacFechaNacimiento, String pacNacionalidad, String pacPuebloOriginario,
                                    String pacDireccion, String pacTelefono){

        Paciente paciente = new Paciente( pacRut, pacDv, establecimiento, casos, antecedenteList,  pacNombres,  pacApellidos,  pacSexo,  pacFechaNacimiento,  pacNacionalidad, pacPuebloOriginario, pacDireccion, pacTelefono);
        if(listaPaciente.size()>0) {
            Paciente existe = buscarPaciente(pacRut);
            if (existe != null) {
                return false;
            } else {
                listaPaciente.add(paciente);
                return true;
            }
        }else{
            listaPaciente.add(paciente);
            return true;
        }
    }

    /**
     * @param paciente
     * @return
     */
    public boolean ingresarPaciente(Paciente paciente){
        if(buscarPaciente(paciente.getPacRut()) != null){
            return false;
        }else{
        listaPaciente.add(paciente);
        return true;
        }
    }

    /**
     * @param rut
     * @return
     */
    public Paciente buscarPaciente(long rut){
        for (int i = 0; i < listaPaciente.size(); i++) {
            if(listaPaciente.get(i).getPacRut() == rut){
                return listaPaciente.get(i);
            }
        }
        return null;
    }

    /**
     * @param paciente
     * @return
     */
    public Paciente buscarPaciente(Paciente paciente){
        return buscarPaciente(paciente.getPacRut());
    }

    /**
     * @param rut
     */
    public boolean eliminarPaciente(long rut){
        for (int i = 0; i < listaPaciente.size(); i++) {
            if( listaPaciente.get(i).getPacRut() ==rut){
                listaPaciente.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * @param paciente
     * @return
     */
    public boolean eliminarPaciente(Paciente paciente){
        return eliminarPaciente(paciente.getPacRut());
    }

    /**
     * @param pacRut
     * @param pacDv
     * @param pacNombres
     * @param pacApellidos
     * @param pacSexo
     * @param pacFechaNacimiento
     * @param pacNacionalidad
     * @param pacPuebloOriginario
     * @param pacDireccion
     * @param pacTelefono
     * @return
     */
    public Paciente editarPaciente(long pacRut, String pacDv, String pacNombres, String pacApellidos,
                                   String pacSexo, String pacFechaNacimiento, String pacNacionalidad, String pacPuebloOriginario,
                                   String pacDireccion, String pacTelefono){
        try {
            Paciente paciente = buscarPaciente(pacRut);
            paciente.setPacDv(pacDv);
            paciente.setPacNombres(pacNombres);
            paciente.setPacApellidos(pacApellidos);
            paciente.setPacSexo(pacSexo);
            paciente.setPacDireccion(pacDireccion);
            paciente.setPacFechaNacimiento(pacFechaNacimiento);
            paciente.setPacNacionalidad(pacNacionalidad);
            paciente.setPacPuebloOriginario(pacPuebloOriginario);
            paciente.setPacTelefono(pacTelefono);
            return paciente;
        }catch (Exception e){
            return null;
        }
    }
}
