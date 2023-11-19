package co.edu.uniquindio.poo.torneodeportivo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
public class RegistroJuecesTest {
    private static final Logger LOG = Logger.getLogger(RegistroJuecesTest.class.getName());

 // Test para RQ2 Registro Jueces 
 @Test
public void registrarJuezExitoso() {
    LOG.info("Inicio de prueba registrar juez exitoso...");

    Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
    Juez juez = new Juez("Esteban", "Narvaez", "esteban@gmail.com", 31123441, 1234567890);

    // Registro del juez en el torneo
    torneo.registrarJuez(juez);

    // Verificación
    assertEquals(1, torneo.getJueces().size());
    assertTrue(torneo.getJueces().contains(juez));

    LOG.info("Fin de prueba registrar juez exitoso...");
    }

}
