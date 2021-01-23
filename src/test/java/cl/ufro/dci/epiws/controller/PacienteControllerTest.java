package cl.ufro.dci.epiws.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteControllerTest {
    private PacienteController pc;
    @Test
    @DisplayName("Verifica no guardar un objeto nulo")
    void saveNulo() throws Exception {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            pc.agregarPaciente(null);
        });
    }

}