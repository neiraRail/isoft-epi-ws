package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Region;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComunaRepositoryTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    Region region;
    Comuna comuna;

    @BeforeAll
    void preparacion(){
        region = new Region(1L,"Araucania");
        comuna = new Comuna(1L,"Temuco",region,new ArrayList<>());
        regionRepository.save(region);
        comunaRepository.save(comuna);
    }

    @Test
    @DisplayName("Test de agregado de comuna")
    public void agregado() {
        Comuna found = comunaRepository.findByComNombre("Temuco");
        assertEquals("Temuco",found.getComNombre());
    }

    @Test
    @DisplayName("Test de edicion de comuna")
    public void edicion(){
        comunaRepository.findByComNombre("Temuco").setComNombre("Lautaro");
        assertEquals("Lautaro",comunaRepository.findByComNombre("Lautaro").getComNombre());
    }

    @Test
    @DisplayName("test de eliminado de comuna")
    public void eliminacion(){
        comunaRepository.deleteById(1L);
        assertEquals(false,comunaRepository.existsById(1L));
    }

}