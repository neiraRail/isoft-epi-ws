package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import cl.ufro.dci.epiws.service.AntecedenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AntecedenteServiceTest {
    private MockMvc mockMvc;

    @Mock
    private AntecedenteRepository antecedenteRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private AntecedenteService antecedenteService;


    private Antecedente antecedente;
    private Paciente paciente;
    private Boolean antEmbarazo;
    private String antEnfermedadCronica;
    private String antAlergias;
    private String antTipoSangre;
    private String antMedicamentos;
    private String antViajeExtranjero;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setPacRut(1l);
        antEmbarazo = true;
        antEnfermedadCronica = "";
        antAlergias = "";
        antTipoSangre = "B+";
        antMedicamentos = "";
        antViajeExtranjero = "Canada, Toronto";
        antecedente = new Antecedente(paciente,antEmbarazo,4,antEnfermedadCronica,antAlergias,antTipoSangre,antMedicamentos,antViajeExtranjero);
        MockitoAnnotations.initMocks(this); //Inicializa el controlador y los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(antecedenteService,pacienteRepository).build();

    }

    @Test
    public void testBuscarAntecedenteEncuentra() {
        when(antecedenteRepository.findById(1L)).thenReturn(Optional.of(antecedente));
        assertEquals(antecedente,antecedenteService.buscar(1L));
    }

    @Test
    public void testBuscarAntecedenteNoEncuentra() {
        when(antecedenteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,()->antecedenteService.buscar(1L));
    }

    @Test
    public void testGuardarAntecedentePasa() {
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        when(antecedenteRepository.save(any(Antecedente.class))).thenReturn(antecedente);
        assertEquals(antecedente,antecedenteService.guardar(antecedente));
    }

    @Test
    public void testGuardarAntecedenteYNoExistePaciente(){
        when(pacienteRepository.existsById(any(long.class))).thenReturn(false);
        when(antecedenteRepository.save(any(Antecedente.class))).thenReturn(antecedente);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("404 NOT_FOUND \"Paciente no encontrado\"",e.getMessage());
    }

    @Test
    public void testGuardarConSemanasNoValidas(){
        antecedente.setAntSemanasGest(50);
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("400 BAD_REQUEST \"Semanas no valido\"",e.getMessage());
    }

    @Test
    public void testGuardarSinTipoSangre(){
        antecedente.setAntTipoSangre("");
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("400 BAD_REQUEST \"Tipo de Sangre vacio o invalido\"",e.getMessage());

    }

    @Test
    public void testGuardarTipoSangreNulo(){
        antecedente.setAntTipoSangre(null);
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("400 BAD_REQUEST \"Tipo de Sangre vacio o invalido\"",e.getMessage());

    }

    @Test
    public void testGuardarAntViajeInvalido(){
        antecedente.setAntViajeExtranjero("candad2");
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("400 BAD_REQUEST \"Antecedente de Viaje no valido\"",e.getMessage());

    }

    @Test
    public void testGuardarNoEmbarazadaConSemanas(){
        antecedente.setAntEmbarazo(false);
        antecedente.setAntSemanasGest(14);
        when(pacienteRepository.existsById(any(long.class))).thenReturn(true);
        Throwable e = assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));

        assertEquals("400 BAD_REQUEST \"Semanas de gestación no aplican\"",e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A+", "B-", "AB+","O-"})
    public void testValidarTipoSangreTrue(String tipo){
        assertTrue(antecedenteService.validarCampoTipoSangre(tipo));
    }
    @ParameterizedTest
    @ValueSource(strings = {"A", "C-", "","BA-"})
    public void testValidarTipoSangreFalse(String tipo){
        assertFalse(antecedenteService.validarCampoTipoSangre(tipo));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Temuco,Chile","Santiago de Chile  , Chile","Valdivia,chile","osorno ,chile",""})
    public void testValidarAntViaje(String antViaje){
        assertTrue(antecedenteService.validarCampoAntViaje(antViaje));

    }
    @ParameterizedTest
    @ValueSource(strings = {"Temuco1,Chile","Santiago de Chile  , _Chile","Valdiviachile","osorno chile"})
    public void testValidarAntViajeFalse(String antViaje){
        assertFalse(antecedenteService.validarCampoAntViaje(antViaje));
    }



    @Test
    public void testEliminarAntecedenteNoExistente(){
        when(antecedenteRepository.existsById(any(long.class))).thenReturn(false);
        assertThrows(ResponseStatusException.class,()->antecedenteService.borrar(1L));

    }

    @Test
    public void testReformateoAntViaje(){
        String[] strings = {"  temuco,chile"," Temuco, chile", "New York , USA"};
        String[] result = {"Temuco, Chile", "Temuco, Chile","New York, USA"};

        for(int i = 0; i<3; i++){
            assertEquals(result[i],antecedenteService.reformatearAntViaje(strings[i]));
        }
    }





}