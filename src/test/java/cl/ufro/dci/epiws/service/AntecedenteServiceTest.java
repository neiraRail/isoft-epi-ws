package cl.ufro.dci.epiws.service;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import cl.ufro.dci.epiws.repository.PacienteRepository;
import cl.ufro.dci.epiws.service.AntecedenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        antViajeExtranjero = "Canada";
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
        assertThrows(ResponseStatusException.class,()->antecedenteService.guardar(antecedente));
    }


    @Test
    public void testEliminarAntecedenteNoExistente(){
        when(antecedenteRepository.existsById(any(long.class))).thenReturn(false);
        assertThrows(ResponseStatusException.class,()->antecedenteService.borrar(1L));

    }





}