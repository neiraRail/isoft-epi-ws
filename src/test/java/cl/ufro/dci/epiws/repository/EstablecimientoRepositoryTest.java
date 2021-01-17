package cl.ufro.dci.epiws.repository;

import cl.ufro.dci.epiws.model.Comuna;
import cl.ufro.dci.epiws.model.Establecimiento;
import cl.ufro.dci.epiws.model.Region;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EstablecimientoRepositoryTest {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    Region region;
    Comuna comuna;
    Establecimiento establecimiento;

    @BeforeAll
     void preparacion(){
        region = new Region(1L,"Araucania");
        comuna = new Comuna(1L,"Temuco",region,new ArrayList<>());
        regionRepository.save(region);
        comunaRepository.save(comuna);
        establecimiento = new Establecimiento((long) 1,"Hospital regional","plaza",comuna,new ArrayList<>());
        establecimientoRepository.save(establecimiento);
    }

    @Test
    @DisplayName("Test de agregado de establecimiento")
    public void agregado() {
        Establecimiento found = establecimientoRepository.findByEstNombre("Hospital regional");
        assertEquals("Hospital regional",found.getEstNombre());
    }

    @Test
    @DisplayName("Test de edicion establecimiento")
    public void edicion(){
        establecimientoRepository.findByEstNombre("Hospital regional").setEstDireccion("av. alemania");
        assertEquals("av. alemania",establecimientoRepository.findByEstNombre("Hospital regional").getEstDireccion());
    }

    @Test
    @DisplayName("test de eliminado")
    public void eliminacion(){
        establecimientoRepository.deleteById(1L);
        assertEquals(false,establecimientoRepository.existsById(1L));
    }
}