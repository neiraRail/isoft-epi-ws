package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.EpiWsApplication;
import cl.ufro.dci.epiws.model.*;
import cl.ufro.dci.epiws.service.AntecedenteService;
import cl.ufro.dci.epiws.service.CasoService;
import cl.ufro.dci.epiws.service.PacienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = EpiWsApplication.class
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AntecedenteControllerTest {
    @Autowired
    protected AntecedenteService antecedenteService;

    @Autowired
    protected PacienteService pacienteService;

    @Autowired
    protected WebApplicationContext wac;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private MockMvc mockMvc;

    private ArrayList<Antecedente> antecedentes;


    private Antecedente antecedente;
    private Paciente paciente;
    private Boolean antEmbarazo;
    private String antEnfermedadCronica;
    private String antAlergias;
    private String antTipoSangre;
    private String antMedicamentos;
    private String antViajeExtranjero;

    private String pacFechaFallecimiento;
    private Establecimiento establecimiento;
    private PersonalMedico medico;
    private List<Caso> casos;
    private List<Antecedente> antecedenteList;
    private String pacNombres;
    private String pacApellidos;
    private String pacSexo;
    private String pacFechaNacimiento;
    private String pacNacionalidad;
    private String pacPuebloOriginario;
    private String pacDireccion;
    private String pacTelefono;

    @BeforeAll
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        antecedentes = new ArrayList<>();

        pacFechaFallecimiento = null;
        establecimiento = null;
        medico = new PersonalMedico();
        casos = new ArrayList<>();
        antecedenteList = new ArrayList<>();
        pacNombres = "Marcelo Alfredo";
        pacApellidos = "Neira Carrasco";
        pacSexo = "M";
        pacFechaNacimiento = "13-01-1970";
        pacNacionalidad = "Chilena";
        pacPuebloOriginario = null;
        pacDireccion = "Fco Pizarro 333";
        pacTelefono = "979567634";
        paciente = new Paciente(166020L,establecimiento,casos,antecedenteList,pacNombres,pacApellidos,pacSexo,pacFechaNacimiento,pacFechaFallecimiento,pacNacionalidad,pacPuebloOriginario,pacDireccion,pacTelefono);

        antEmbarazo = true;
        antEnfermedadCronica = "Cancer";
        antAlergias = "Mani, Penicilina";
        antTipoSangre = "B+";
        antMedicamentos = "";
        antViajeExtranjero = "Canada, Chile";
        antecedente = new Antecedente(paciente,antEmbarazo,4,antEnfermedadCronica,antAlergias,antTipoSangre,antMedicamentos,antViajeExtranjero);

        pacienteService.save(paciente);
        antecedenteService.guardar(antecedente);

        this.antecedentes.add(antecedente);
    }



    @Test
    @DisplayName("Verifica el funcionamiento del metodo getAll del controlador")
    void testAGetAll() throws Exception {

        String requestJson = listToJsonString(antecedentes);
        //Ejecuta peticion get, que retorna un json de todos los antecedentes
        this.mockMvc.perform(get("/api/antecedentes/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(requestJson));
    }

    @Test
    void buscar() throws Exception {
        String responseJson = antecedenteToJsonString(antecedente);
        this.mockMvc.perform(get("/api/antecedentes/1"))
                .andExpect(status().isOk()) //verifica que el código de estado debe ser 200
                .andExpect(content().json(responseJson)); //verifica que el json de respuesta sea el que esperamos
    }

    @Test
    @DisplayName("Verifica el funcionamiento del controlador de antecedentes")
    void testPostAntecedente() throws Exception {
        antecedente.setAntId(null);
        String requestJson = antecedenteToJsonString(antecedente);
        antecedente.setAntId(2L);
        String responseJson = antecedenteToJsonString(antecedente);
        //Ejecuta peticion post, que retorna, en este caso, un json del caso creado
        this.mockMvc.perform(post("/api/antecedentes/")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print()) //para imprimir el request, response, entre otros.
                .andExpect(status().isOk()) //verifica que el código de estado debe ser 200
                .andExpect(content().json(responseJson)); //verifica que el json de respuesta sea el que esperamos
    }

    @Test
    @DisplayName("Verifica el funcionamiento del metodo borrar del controlador")
    void borrarAntecedente() throws Exception {

        //Ejecuta peticion post, que retorna, en este caso, un json del caso creado
        this.mockMvc.perform(delete("/api/antecedentes/1"))
                .andDo(print()) //para imprimir el request, response, entre otros.
                .andExpect(status().isOk()); //verifica que el código de estado debe ser 200
    }



    @Test
    @DisplayName("Verifica el funcionamiento del metodo buscar del controlador")
    void buscarAntecedente() throws Exception {
        String responseJson = antecedenteToJsonString(antecedente);
        this.mockMvc.perform(get("/api/antecedentes/1"))
                .andExpect(status().isOk()) //verifica que el código de estado debe ser 200
                .andExpect(content().json(responseJson)); //verifica que el json de respuesta sea el que esperamos
    }


    private String listToJsonString(List<Antecedente> antecedentes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        return ow.writeValueAsString(antecedentes);
    }

    private String antecedenteToJsonString(Antecedente antecedente) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        return ow.writeValueAsString(antecedente);
    }
}