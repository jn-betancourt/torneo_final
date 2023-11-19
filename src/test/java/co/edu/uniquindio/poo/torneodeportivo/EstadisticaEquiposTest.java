package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EstadisticasEquiposTest {

    private static final Logger LOG = Logger.getLogger(EstadisticasEquiposTest.class.getName());

    @Test
    public void obtenerEstadisticasEquiposExitoso() {
        LOG.info("Inicio de prueba obtener estadísticas de equipos exitoso...");

        // Datos de prueba
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));
        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Manuela", "Galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);
        equipoA.registrarJugador(jugador1);
        equipoB.registrarJugador(jugador2);

        // Agendar enfrentamientos en el torneo
        torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now().plusDays(5), LocalTime.now().plusHours(2), equipoA, equipoB, List.of());
        torneo.registrarEnfrentamiento("Ubicacion2", LocalDate.now().plusDays(8), LocalTime.now().plusHours(4), equipoB, equipoA, List.of());

        // Obtener estadísticas de equipos
        Map<Equipo, EstadisticasEquipo> estadisticasEquipos = torneo.obtenerEstadisticasEquipos();

        // Verificación
        assertTrue(estadisticasEquipos.containsKey(equipoA));
        assertTrue(estadisticasEquipos.containsKey(equipoB));

        EstadisticasEquipo estadisticasEquipoA = estadisticasEquipos.get(equipoA);
        EstadisticasEquipo estadisticasEquipoB = estadisticasEquipos.get(equipoB);

        assertEquals(1, estadisticasEquipoA.getVictorias());
        assertEquals(1, estadisticasEquipoB.getVictorias());
        assertEquals(1, estadisticasEquipoA.getDerrotas());
        assertEquals(1, estadisticasEquipoB.getDerrotas());
        assertEquals(0, estadisticasEquipoA.getEmpates());
        assertEquals(0, estadisticasEquipoB.getEmpates());

        LOG.info("Fin de prueba obtener estadísticas de equipos exitoso...");
    }
}

