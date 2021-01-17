package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Caso;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.CasoRepository;

import cl.ufro.dci.epiws.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CasoServiceTest {

    protected MockMvc mockMvc;

    @Mock
    private CasoRepository casoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CasoService casoService;

    private Paciente paciente0;
    private Caso caso0;

    private Paciente paciente1;
    private Caso caso1;

    @BeforeEach
    void prepare() {
        paciente0 = new Paciente(7737272L,null,new ArrayList<>(),new ArrayList<>(),"luis","silva","masculino","07-10-56",null,"chilena","mapuche","los trigales","+569 ...");
        caso0 = new Caso("16-01-2021",true,"01-01-2021",2,"Diarrea y dolor de cabeza","Fuerte dolor de cabeza","Contagiado",true,paciente0);

        paciente1 = new Paciente(20120220L,null,new ArrayList<>(),new ArrayList<>(),"luis","silva","masculino","07-10-99",null,"chilena","mapuche","los trigales","+569 ...");
        caso1 = new Caso("16-01-2021",true,"01-01-2021",2,"Diarrea y dolor de cabeza","Fuerte dolor de cabeza","Contagiado",true,paciente1);

        pacienteRepository.save(paciente0);
        pacienteRepository.save(paciente1);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(casoService,pacienteRepository).build();
    }

    @Test
    @DisplayName("Verificar el funcionamiento correcto")
    void testBuscarCaso() {
        when(casoRepository.existsById(any(long.class))).thenReturn(true);
        when(casoRepository.findById(1L)).thenReturn(Optional.of(caso0));
        assertEquals(caso0,casoService.findById(1L));
    }

    @Test
    @DisplayName("Verificar el funcionamiento correcto")
    void testGuardarCaso() {
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        when(casoRepository.save(any(Caso.class))).thenReturn(caso0);
        assertEquals(caso0,casoService.save(caso0));
    }

    @Test
    @DisplayName("Verificar el funcionamiento correcto")
    void testObtenerCasos() {
        ArrayList <Caso> casos = new ArrayList<>();
        casos.add(caso0);
        when(casoRepository.findAll()).thenReturn(casos);
        assertEquals(casos,casoService.obtenerCasos());
    }

    @Test
    @DisplayName("Verificar el funcionamiento correcto")
    void testVerificarExistencia() {
        when(casoRepository.existsById(1L)).thenReturn(true);
        assertTrue(casoService.existById(1L));
    }

    @Test
    @DisplayName("Verificar que el objeto Paciente sea nulo")
    void testAgregarPacienteNulo(){
        Caso caso_2 = new Caso("16-01-2021",true,"01-01-2021",2,"Diarrea y dolor de cabeza","Fuerte dolor de cabeza","Contagiado",true);
        Throwable exception = assertThrows(NullPointerException.class, () -> casoService.save(caso_2));
        assertEquals("El objeto Paciente es nulo", exception.getMessage(), "El objeto Paciente es nulo");
    }

    @Test
    @DisplayName("Verificar que el objeto Caso sea nulo")
    void testAgregarCasoNulo(){
        Throwable exception = assertThrows(NullPointerException.class, () -> casoService.save(null));
        assertEquals("El objeto Caso es nulo", exception.getMessage(), "El objeto Caso es nulo");
    }

}