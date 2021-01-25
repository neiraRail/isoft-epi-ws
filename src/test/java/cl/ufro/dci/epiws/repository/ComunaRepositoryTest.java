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
import java.util.Optional;

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
    Comuna comuna2;
    @BeforeAll
    void preparacion(){
        region = new Region("Araucania");
        comuna = new Comuna("Temuco",region,new ArrayList<>());
        comuna2 = new Comuna("inventada",region,new ArrayList<>());
        regionRepository.save(region);
        comunaRepository.save(comuna);
        comunaRepository.save(comuna2);
    }

    @Test
    @DisplayName("Test de agregado de comuna")
    void agregado() {
        Optional<Comuna> found = comunaRepository.findById(comuna.getComId());
        assertEquals("Temuco",found.get().getComNombre());
    }

    @Test
    @DisplayName("Test de edicion de comuna")
    void edicion(){
        comunaRepository.findById(comuna.getComId()).get().setComNombre("Lautaro");
        assertEquals("Lautaro",comunaRepository.findByComNombre("Lautaro").get().getComNombre());
    }

    @Test
    @DisplayName("test de eliminado de comuna")
    void eliminacion(){
        comunaRepository.deleteById(comuna.getComId());
        assertEquals(false,comunaRepository.existsById(comuna.getComId()));
    }

    @Test
    @DisplayName("test de buscado de comuna")
    void buscar(){
        Comuna resultado = comunaRepository.findByComNombre("inventada").get();
        assertEquals("inventada",resultado.getComNombre());
    }

}