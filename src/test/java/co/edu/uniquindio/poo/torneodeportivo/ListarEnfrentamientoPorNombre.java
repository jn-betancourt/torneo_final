package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class ListarEnfrentamientoPorNombre {
    private static final Logger LOG = Logger.getLogger(ListarEnfrentamientoPorNombre.class.getName());

    @Test
    public void listarEnfrentamientosPorNombreExitoso() {
        LOG.info("Inicio de prueba listar enfrentamientos por nombre exitoso...");

        // Datos de prueba
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);
        Equipo equipoA = new Equipo("malosfc", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("parcerosfc", new Persona("jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));
        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1,23), Genero.FEMENINO);
        equipoA.registrarJugador(jugador1);
        equipoB.registrarJugador(jugador2);

        // Agendar el enfrentamiento en el torneo
        torneo.registrarEnfrentamiento("Ubicacion", LocalDate.now().plusDays(5), LocalTime.now().plusHours(2), equipoA, equipoB, List.of(juez1, juez2));

        // Listar enfrentamientos por nombre (insensible a mayúsculas y minúsculas)
        Optional<Collection<Enfrentamiento>> enfrentamientosPorNombre = torneo.listarEnfrentamientosPorNombre("parcerosfc");

        // Verificación
        assertTrue(enfrentamientosPorNombre.isPresent());
        Collection<Enfrentamiento> enfrentamientosEncontrados = enfrentamientosPorNombre.get();
        assertEquals(1, enfrentamientosEncontrados.size());
        Enfrentamiento enfrentamiento = enfrentamientosEncontrados.iterator().next();
        assertEquals("Ubicacion", enfrentamiento.getUbicacion());
        assertEquals(LocalDate.now().plusDays(5), enfrentamiento.getFecha());
        assertEquals(LocalTime.now().plusHours(2).truncatedTo(ChronoUnit.HOURS), enfrentamiento.getHora().truncatedTo(ChronoUnit.HOURS));
        assertEquals(equipoA, enfrentamiento.getRivalA());
        assertEquals(equipoB, enfrentamiento.getRivalB());
        assertEquals(List.of(juez1, juez2), enfrentamiento.getJueces());
        assertEquals(EstadoEnfrentamiento.PENDIENTE, enfrentamiento.getEstado());

        LOG.info("Fin de prueba listar enfrentamientos por nombre exitoso...");
    }
}

