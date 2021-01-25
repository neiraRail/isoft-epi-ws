package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.*;
import cl.ufro.dci.epiws.repository.ComunaRepository;
import cl.ufro.dci.epiws.repository.EstablecimientoRepository;
import cl.ufro.dci.epiws.repository.RegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EstablecimientoControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    void preparacion(){
        Region region = new Region("Araucania");
        Comuna comuna = new Comuna("Temuco",region,new ArrayList<>());
        // id = 1
        Establecimiento establecimiento = new Establecimiento("Clinica alemana","senador estebanes",comuna,new ArrayList<>());
        // id = 2
        Establecimiento establecimiento2 = new Establecimiento("hospital 2","senador estebanes",comuna,new ArrayList<>());
        // id = 3
        Establecimiento establecimiento3 = new Establecimiento("hospital 3","senador estebanes",comuna,new ArrayList<>());
        regionRepository.save(region);
        comunaRepository.save(comuna);
        establecimientoRepository.save(establecimiento);
        establecimientoRepository.save(establecimiento2);
        establecimientoRepository.save(establecimiento3);
    }



    @Test
    @DisplayName("Test para agregar establecimientos")
    void agregar() throws Exception {
        mvc.perform(post("/api/establecimiento/agregar/HospitaRegional/plaza/1").contentType("application/json")).andExpect(status().isOk());
        Long l = (long) 4;
        assertEquals("HospitaRegional",establecimientoRepository.findById(l).orElse(new Establecimiento("est_nulo","dic",new Comuna("ara",new Region("dd"),new ArrayList<>()),new ArrayList<>())).getEstNombre());
    }

    @Test
    @DisplayName("Test para buscar establecimientos")
    void buscar() throws Exception {
        String establecimiento = mvc.perform(get("/api/establecimiento/buscar/1")
                .contentType("application/json")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Establecimiento est = new ObjectMapper().readValue(establecimiento, Establecimiento.class);
        assertEquals("Clinica alemana",est.getEstNombre());
    }

    @Test
    @DisplayName("Test para eliminar establecimientos")
    void eliminar() throws Exception {
        mvc.perform(delete("/api/establecimiento/eliminar/2")
                .contentType("application/json")).andExpect(status().isOk());
        assertFalse(establecimientoRepository.existsById(2L));
    }

    /*@Test
    @DisplayName("Test para editar establecimiento")
    void editar() throws Exception {
        Establecimiento establecimiento4 = new Establecimiento("hospital 4",
                "senador estebanes", new Comuna(), new ArrayList<>());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(establecimiento4);

        mvc.perform(put("/api/establecimiento/editar/3").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }*/
}