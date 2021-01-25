package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.repository.ComunaRepository;
import cl.ufro.dci.epiws.repository.RegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComunaControllerTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    void preparacion(){
        Region region = new Region("Araucania");
        Comuna comuna = new Comuna("Temuco",region,new ArrayList<>());
        regionRepository.save(region);
        comunaRepository.save(comuna);
    }

    @Test
    @DisplayName("Test para agregar comuna")
    void agregar() throws Exception {
        mvc.perform(post("/api/comuna/agregar").contentType("application/json")
                .param("nombre","Padre las Casas").param("regionId","1"))
                .andExpect(status().isOk());
        Long l = (long) 2;
        assertEquals("Padre las Casas",comunaRepository.findById(l).get().getComNombre());
    }

    /*@Test
    @DisplayName("Test para buscar comunas")
    void buscar() throws Exception {
        String region = mvc.perform(get("/api/comuna/buscar")
                .contentType("application/json").param("nombre","Temuco")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Comuna est = new ObjectMapper().readValue(region, Comuna.class);
        assertEquals("Temuco",est.getComNombre());
    }*/

    @Test
    @DisplayName("Test para eliminar comunas")
    void eliminar() throws Exception {
        mvc.perform(delete("/api/comuna/eliminar/1")
                .contentType("application/json")).andExpect(status().isOk());
        assertFalse(comunaRepository.existsById(1L));
    }
}