package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.EpiWsApplication;
import cl.ufro.dci.epiws.model.Paciente;
import cl.ufro.dci.epiws.model.Caso;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Antecedente;
import cl.ufro.dci.epiws.service.CasoService;
import cl.ufro.dci.epiws.service.PacienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test que usa el contexto de la aplicación (no la simula). Se testea desde la petición HTTP
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = EpiWsApplication.class
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class CasoControllerTest {

    @Autowired
    protected PacienteService pacienteService;

    @Autowired
    protected CasoService casoService;

    @Autowired
    protected WebApplicationContext wac;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private MockMvc mockMvc;

    private ArrayList<Caso> casos;

    private Caso caso;
    private Paciente paciente;

    private Long pacRut;
    private Establecimiento establecimiento;
    private ArrayList<Caso> casosPaciente;
    private ArrayList<Antecedente> antecedenteList;
    private String pacNombres;
    private String pacApellidos;
    private String pacSexo;
    private String pacFechaNacimiento;
    private String pacFechaFallecimiento;
    private String pacNacionalidad;
    private String pacPuebloOriginario;
    private String pacDireccion;
    private String pacTelefono;

    private String casFechaNotificacion;
    private Boolean casAsintomatico;
    private String casFechaPrimerosSintomas;
    private int casSemanaEpidemiologica;
    private String casSintomas;
    private String casRazonSospecha;
    private String casClasificacionFinal;
    private Boolean casHospitalizacion;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        pacRut =  176892L;
        establecimiento = null;
        casosPaciente = new ArrayList<>();
        antecedenteList = new ArrayList<>();
        pacNombres = "Luis";
        pacApellidos = "Silva";
        pacSexo = "Masculino";
        pacFechaNacimiento = "07-10-1998";
        pacFechaFallecimiento = "vivito y coleando";
        pacNacionalidad = "Chilena";
        pacPuebloOriginario = "mapuche";
        pacDireccion = "urrutia";
        pacTelefono = "912239122";

        casFechaNotificacion = "11-01-2021";
        casAsintomatico = true;
        casFechaPrimerosSintomas = "01-01-2021";
        casSemanaEpidemiologica = 2;
        casSintomas = "Diarrea y dolor de cabeza";
        casRazonSospecha = "Fuerte dolor de cabeza";
        casClasificacionFinal = "Contagiado";
        casHospitalizacion = true;

        casos = new ArrayList<>();

        paciente = new Paciente(pacRut,establecimiento,casosPaciente,antecedenteList,pacNombres,pacApellidos,pacSexo,pacFechaNacimiento,pacFechaFallecimiento,pacNacionalidad,pacPuebloOriginario,pacDireccion,pacTelefono);
        caso = new Caso(casFechaNotificacion,casAsintomatico,casFechaPrimerosSintomas,casSemanaEpidemiologica,casSintomas,casRazonSospecha,casClasificacionFinal,casHospitalizacion,paciente);
        pacienteService.save(paciente);

        casoService.save(caso);
        this.casos.add(caso);
    }

    @Test
    @DisplayName("Verifica el funcionamiento del controlador que guarda casos en la base de datos")
    void testPostCaso() throws Exception {
        String requestJson = casoToJsonString(caso);
        caso.setCasId(2L);
        String responseJson = casoToJsonString(caso);
        //Ejecuta peticion post, que retorna, en este caso, un json del caso creado
        this.mockMvc.perform(post("/api/casos/")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print()) //para imprimir el request, response, entre otros.
                .andExpect(status().isOk()) //verifica que el código de estado debe ser 200
                .andExpect(content().json(responseJson)); //verifica que el json de respuesta sea el que esperamos
    }

    @Test
    @DisplayName("Verifica el funcionamiento del controlador que entrega los casos almacenados en la base de datos")
    void testGetAllCasos() throws Exception {
        String requestJson = listToJsonString(casos);
        //Ejecuta peticion get, que retorna un json de todos los casos
        this.mockMvc.perform(get("/api/casos/"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestJson));
    }

    private String listToJsonString(List<Caso> casos) throws JsonProcessingException { //Convierte una lista de JSON a String
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        return ow.writeValueAsString(casos);
    }

    private String casoToJsonString(Caso caso) throws JsonProcessingException { //Convierte un JSON a String
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer();
        return ow.writeValueAsString(caso);
    }

}