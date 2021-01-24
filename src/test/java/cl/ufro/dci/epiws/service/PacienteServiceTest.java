package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.controller.PacienteController;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;
import static org.mockito.ArgumentMatchers.any;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Pruebas de la clase PacienteService")
class PacienteServiceTest {
    private MockMvc mockMvc;
    private PacienteService ps;
    private Paciente paciente;
    private Paciente paciente1;
    @Mock
    private PacienteRepository pacienteRepository;
    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    @DisplayName("Carga los datos iniciales para la ejecucion de las pruebas")
    void setUp(){
        paciente = new Paciente(123,null,null,null,null,"nombre","apellido",
                "sexo","fechanaci","nacionalidad","as","sad","ads");
        paciente1 = new Paciente(124,null,null,null,null,"nombre","apellido",
                "sexo","fechanaci","nacionalidad","as","sad","ads");
        MockitoAnnotations.initMocks(this); //Inicializa el controlador y los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(pacienteRepository).build();
    }

    @Test
    @DisplayName("Verifica no guardar dos pacientes con el mismo rut")
    void saveIguales() throws Exception {
        when(pacienteRepository.findById(123L)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            pacienteService.save(paciente);
        });
    }

    @Test
    @DisplayName("Verifica correcta funcionalidad del metodo save")
    void saveCorrecto()throws Exception{
        when(pacienteRepository.findById(paciente.getPacRut())).thenReturn(Optional.of(paciente));
        pacienteService.save(paciente);
        assertEquals(pacienteService.buscarPaciente(paciente.getPacRut()).get().getPacRut(),paciente.getPacRut());
    }

    @Test
    @DisplayName("Verifica que no existe un paciente a eliminar")
    void borrarPacienteInexistente() throws Exception {
        when(pacienteRepository.existsById(any(long.class))).thenReturn(false);
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            pacienteService.borrarPaciente(123L);
        });
    }

    @Test
    @DisplayName("verifica funcionamiento correcto")
    void borrarPaciente()throws Exception{
        //ARREGLAR
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            pacienteService.borrarPaciente(null);
        });
    }

    @Test
    @DisplayName("Verifica paciente inexistente")
    void buscarPacienteInexistente() throws Exception {
        when(pacienteRepository.existsById(any(long.class))).thenReturn(false);
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            pacienteService.buscarPaciente(123L);
        });
    }

    @Test
    @DisplayName("verifica que los datos recogidos son incorrectos")
    void buscarPacienteIncorrecto()throws Exception{
        when(pacienteRepository.findById(123L)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.findById(124L)).thenReturn(Optional.of(paciente1));
        assertNotEquals(paciente.getPacRut(),pacienteService.buscarPaciente(124L).get().getPacRut());
    }

    @Test
    @DisplayName("Verifica que el paciente se encuentra")
    void buscarPacienteCorrecto() throws Exception {
        when(pacienteRepository.findById(123L)).thenReturn(Optional.of(paciente));
        assertEquals(paciente.getPacRut(),pacienteService.buscarPaciente(123L).get().getPacRut());
    }
    @Test
    @DisplayName("Verifica cuando el paciente  no es encontrado")
    void buscarPacienteNoEncontrado() throws Exception {
        when(pacienteRepository.findById(123L)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            pacienteService.buscarPaciente(123L);
        });;
    }
}