package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.repository.AntecedenteRepository;
import cl.ufro.dci.epiws.service.AntecedenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.Column;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AntecedenteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private AntecedenteService antecedenteService;
    @Mock
    private AntecedenteRepository antecedenteRepository;

    @InjectMocks
    private AntecedenteController antecedenteController;

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
        antEmbarazo = true;
        antEnfermedadCronica = "";
        antAlergias = "";
        antTipoSangre = "B+";
        antMedicamentos = "";
        antViajeExtranjero = "Canada";
        antecedente = new Antecedente(paciente,antEmbarazo,antEnfermedadCronica,antAlergias,antTipoSangre,antMedicamentos,antViajeExtranjero);
        MockitoAnnotations.initMocks(this); //Inicializa el controlador y los mocks
        mockMvc = MockMvcBuilders.standaloneSetup(antecedenteController).build();

    }

    @Test
    public void testGetEstudiante() {
        when(antecedenteRepository.findById(1l)).thenReturn(Optional.of(antecedente));
        assertEquals(antecedente,antecedenteController.buscar(1L));
    }

    @Test
    public void testPostEstudiante() {
        when(antecedenteRepository.save(any(Antecedente.class))).thenReturn(antecedente);
        assertEquals(antecedente,antecedenteController.guardarAntecedente(antecedente));
    }
}