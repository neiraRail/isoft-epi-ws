package cl.ufro.dci.epiws.controller;

import cl.ufro.dci.epiws.model.Region;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RegionRepository regionRepository;

    @BeforeAll
    void preparacion(){
        Region region = new Region("Araucania");
        Region region1 = new Region("Bio Bio");
        regionRepository.save(region);
        regionRepository.save(region1);
    }

    @Test
    @DisplayName("Test para agregar región")
    void agregar() throws Exception {
        mvc.perform(post("/api/region/agregar/").contentType("application/json").param("nombre","Arica y Parinacota")).andExpect(status().isOk());
        assertEquals("Arica y Parinacota",regionRepository.findByRgnNombre("Arica y Parinacota").get().getRgnNombre());
    }

    @Test
    @DisplayName("Test para buscar regiones")
    void buscar() throws Exception {
        String region = mvc.perform(get("/api/region/buscar")
                .contentType("application/json").param("nombre","Araucania")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Region est = new ObjectMapper().readValue(region, Region.class);
        assertEquals("Araucania",est.getRgnNombre());
    }

    @Test
    @DisplayName("Test para eliminar regiones")
    void eliminar() throws Exception {
        mvc.perform(delete("/api/region/eliminar/1")
                .contentType("application/json")).andExpect(status().isOk());
        assertFalse(regionRepository.existsById(1L));
    }
}