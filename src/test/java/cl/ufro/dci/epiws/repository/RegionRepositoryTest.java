package cl.ufro.dci.epiws.repository;

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
        region = new Region("Arica y Parinacota");
        regionRepository.save(region);
    }

    @Test
    @DisplayName("Test de agregado de region")
    public void agregar(){
        region = new Region("Metropolitana de Santiago");
        regionRepository.save(region);
        assertEquals("Metropolitana de Santiago",regionRepository.findByRgnNombre("Metropolitana de Santiago").getRgnNombre());
    }

    @Test
    @DisplayName("Test de edicion de region")
    public void editar(){
        regionRepository.findByRgnNombre("Araucania").setRgnNombre("Atacama");
        assertEquals("Atacama",regionRepository.findByRgnNombre("Atacama").getRgnNombre());
    }

    @Test
    @DisplayName("Test de eliminado de region")
    public void eliminar(){
        regionRepository.deleteById(2L);
        assertEquals(false,regionRepository.existsById(2L));
    }

}