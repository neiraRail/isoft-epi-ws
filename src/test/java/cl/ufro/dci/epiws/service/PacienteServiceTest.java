package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Paciente;
import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de la clase PacienteService")
class PacienteServiceTest {

    private PacienteService ps;
    private Paciente paciente1;
    private Paciente paciente2;

    @BeforeEach
    @DisplayName("Carga los datos iniciales para la ejecucion de las pruebas")
    void setUp(){
        paciente1 = new Paciente(123,null,null,null,null,"nombre","apellido",
                "sexo","fechanaci","nacionalidad","as","sad","ads");
        paciente2 = new Paciente();
        ps = new PacienteService();
    }


    @Test
    @DisplayName("Verifica no guardar un objeto nulo")
    void saveNulo() throws Exception {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            ps.save(null);
        });
    }

    @Test
    @DisplayName("Verifica no guardar dos pacientes con el mismo rut")
    void saveIguales() throws Exception {
    }

    @Test
    @DisplayName("Verifica no guardar vacio")
    void saveVacio() throws Exception {
    }

    @Test
    @DisplayName("verifica borrar Nulo")
    void borrarPacienteInexistente() {

    }

    @Test
    @DisplayName("verifica funcionamiento correcto")
    void borrarPaciente(){

    }
    @Test
    @DisplayName("Verifica borrar vacio")
    void borrarPacienteVacio(){

    }

    @Test
    @DisplayName("Verifica paciente inexistente")
    void buscarPacienteInexistente() {

    }

    @Test
    @DisplayName("verifica datos incorrectos")
    void buscarPacienteIncorrecto(){

    }

    @Test
    @DisplayName("Verifica datos ingresados nulos")
    void buscarPacienteNulo(){

    }

    @Test
    @DisplayName("Verifica datos mal ingresados")
    void editarPacienteInexistente() {

    }
    @Test
    @DisplayName("Verifica datos incompletos")
    void editarPacienteIncompleto(){

    }

    @Test
    @DisplayName("Verifica entrada correcta de datos")
    void editarPacienteTest(){

    }
}