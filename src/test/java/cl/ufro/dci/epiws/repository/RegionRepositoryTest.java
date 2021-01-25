package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Region;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    Region region;

    @BeforeAll
    void preparacion(){
        region = new Region("Araucania");
        regionRepository.save(region);
        Region region1 = new Region("Arica y Parinacota");
        regionRepository.save(region1);
    }

    @Test
    @DisplayName("Test de agregado de region")
    void agregar(){
        Region region2 = new Region("Metropolitana de Santiago");
        regionRepository.save(region2);
        assertEquals("Metropolitana de Santiago",regionRepository.findByRgnNombre("Metropolitana de Santiago").get().getRgnNombre());
    }

    @Test
    @DisplayName("Test de edicion de region")
    void editar(){
        regionRepository.findById(region.getRgnId()).get().setRgnNombre("Atacama");
        assertEquals("Atacama",regionRepository.findByRgnNombre("Atacama").get().getRgnNombre());
    }

    @Test
    @DisplayName("Test de eliminado de region")
    void eliminar(){
        regionRepository.deleteById(region.getRgnId());
        assertEquals(false,regionRepository.existsById(region.getRgnId()));
    }

    @Test
    @DisplayName("test de buscado de region")
    void buscar(){
        Region resultado = regionRepository.findByRgnNombre("Arica y Parinacota").get();
        assertEquals("Arica y Parinacota",resultado.getRgnNombre());
    }

}