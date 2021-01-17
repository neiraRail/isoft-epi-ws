package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Region;
import cl.ufro.dci.epiws.model.Seremi;
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
class SeremiRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private SeremiRepository seremiRepository;

    Seremi seremi;
    Region region;

    @BeforeAll
    void preparacion(){
        region = new Region("Araucania");
        seremi = new Seremi(1L,"Seremi de salud araucania",region);
        region.getSeremiList().add(seremi);

        regionRepository.save(region);
        seremiRepository.save(seremi);
    }

    @Test
    @DisplayName("Test de agregado de seremi")
    public void agregar(){
        seremi = new Seremi(2L,"Seremi de mineria",region);
        regionRepository.findByRgnNombre("Araucania").getSeremiList().add(seremi);
        seremiRepository.save(seremi);
        assertEquals("Seremi de mineria",seremiRepository.findBySerNombre("Seremi de mineria").getSerNombre());
    }

    @Test
    @DisplayName("Test de edicion de seremi")
    public void editar(){
        seremiRepository.findBySerNombre("Seremi de salud araucania").setSerNombre("Mineria");
        assertEquals("Mineria",seremiRepository.findBySerNombre("Mineria").getSerNombre());
    }

    @Test
    @DisplayName("Test de eliminado de seremi")
    public void eliminar(){
        seremiRepository.deleteById(1L);
        assertEquals(false,seremiRepository.existsById(1L));
    }
}