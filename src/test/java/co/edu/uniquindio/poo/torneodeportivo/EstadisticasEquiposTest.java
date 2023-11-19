package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EstadisticasEquiposTest {

    private static final Logger LOG = Logger.getLogger(EstadisticasEquiposTest.class.getName());

    @Test
    public void equiposOrdenadosPorVictorias() {
        LOG.info("Inicio de prueba victorias...");

        
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);

        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));

        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);

        torneo.registrarJugador(equipoA, jugador1);
        torneo.registrarJugador(equipoB, jugador2);
        
        torneo.registrarParticipante(equipoA);
        torneo.registrarParticipante(equipoB);


        // Simulación de enfrentamientos y resultados
        torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now(), LocalTime.now(), equipoA, equipoB, List.of(juez1, juez2));
        Optional<Enfrentamiento> enfrentamiento = torneo.getEnfrentamientos().stream().findFirst();

        // Cambiar a EN JUEGO
        enfrentamiento.get().iniciarEnfrentamiento();
        assertEquals(EstadoEnfrentamiento.EN_JUEGO, enfrentamiento.get().getEstado());

        // Crear un nuevo enfrentamiento con el resultado
        enfrentamiento.get().getResultado().sumarPunto(equipoA, 2);
        enfrentamiento.get().getResultado().sumarPunto(equipoB, 1);

        // Cambiar a FINALIZADO
        enfrentamiento.get().finalizarEnfrentamiento();

        // Verificar que el estado se haya cambiado correctamente
        assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamiento.get().getEstado());
        // Verificar los puntos en el resultado
        assertEquals(2, enfrentamiento.get().getResultado().getPuntosRivalA());
        assertEquals(1, enfrentamiento.get().getResultado().getPuntosRivalB());
        // Verificar listado mayor victorias
        Collection<Equipo> equipos = torneo.equiposOrdenadosMayorVicotrias();
        System.out.println(equipos);
        Optional<Equipo> equipoMayorVictoria = equipos.stream().findFirst();
        assertEquals(equipoMayorVictoria.get(), equipoA);
    }

    @Test
    public void equiposOrdenadosPorDerrotas() {

        LOG.info("Inicio de prueba Derrotas...");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);

        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));

        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);
        
        torneo.registrarJugador(equipoA, jugador1);
        torneo.registrarJugador(equipoB, jugador2);

        torneo.registrarParticipante(equipoA);
        torneo.registrarParticipante(equipoB);


        // Simulación de enfrentamientos y resultados
        torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now(), LocalTime.now(), equipoA, equipoB, List.of(juez1, juez2));
        Optional<Enfrentamiento> enfrentamiento = torneo.getEnfrentamientos().stream().findFirst();

        enfrentamiento.get().getResultado().sumarPunto(equipoB, 2);
        enfrentamiento.get().finalizarEnfrentamiento();
        // Obtener equipos ordenados por derrotas
        Collection<Equipo> equiposOrdenadosDerrotas = torneo.equiposOrdenadosMayorDerrotas();

        // Verificación
        assertEquals(2, equiposOrdenadosDerrotas.size());

        Equipo equipoConDerrotas = equiposOrdenadosDerrotas.iterator().next();
        assertEquals("EquipoA", equipoConDerrotas.getNombre());
        assertEquals(1, equipoConDerrotas.getDerrotas());
    }

    @Test
    public void equiposOrdenadosPorEmpates() {
        
        LOG.info("Inicio de prueba empates...");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);

        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));

        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);
        
        equipoA.registrarJugador(jugador1);
        equipoB.registrarJugador(jugador2);

        torneo.registrarParticipante(equipoA);
        torneo.registrarParticipante(equipoB);

        // Simulación de enfrentamientos y resultados
        torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now(), LocalTime.now(), equipoB, equipoB, List.of(juez1 ,juez2));
        
        Optional<Enfrentamiento> enfrentamientoOptional = torneo.getEnfrentamientos().stream().findFirst();

        enfrentamientoOptional.get().iniciarEnfrentamiento();
        assertEquals(EstadoEnfrentamiento.EN_JUEGO, enfrentamientoOptional.get().getEstado());

        enfrentamientoOptional.get().getResultado().sumarPunto(equipoA, 1);
        enfrentamientoOptional.get().getResultado().sumarPunto(equipoB,1);

        enfrentamientoOptional.get().finalizarEnfrentamiento();

        // Obtener equipos ordenados por empates
        Collection<Equipo> equiposOrdenadosEmpates = torneo.equiposOrdenadosMayorEmpates();

        // Verificación
        assertEquals(2, equiposOrdenadosEmpates.size());

        Equipo equipoConEmpates = equiposOrdenadosEmpates.iterator().next();
        assertEquals("EquipoB", equipoConEmpates.getNombre());
        assertEquals(2, equipoConEmpates.getEmpates());
    }


}


