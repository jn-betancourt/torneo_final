package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    
    Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
    Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
    Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
    torneo.registrarJuez(juez1);
    torneo.registrarJuez(juez2);

    Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
    Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));

    Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
    Jugador jugador2 = new Jugador("Manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);

    // Simulación de enfrentamientos y resultados
    torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now().plusDays(5), LocalTime.now().plusHours(2), equipoA, equipoB, List.of());
    Enfrentamiento enfrentamiento = torneo.getEnfrentamientos().iterator().next();

    // Cambiar a EN JUEGO
    enfrentamiento.iniciarEnfrentamiento();
    assertEquals(EstadoEnfrentamiento.EN_JUEGO, enfrentamiento.getEstado());

    // Crear un nuevo enfrentamiento con el resultado
    Resultado resultado = new Resultado(equipoA, equipoB);
    resultado.sumarPunto(equipoA, 2);
    resultado.sumarPunto(equipoB, 1);
    resultado.determinarGanador();
    Enfrentamiento enfrentamientoConResultado = new Enfrentamiento
     
    enfrentamientoConResultado.setResultado(resultado);

    // Cambiar a FINALIZADO
    enfrentamientoConResultado.finalizarEnfrentamiento();

    // Verificar que el estado se haya cambiado correctamente
    assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamientoConResultado.getEstado());
    // Verificar los puntos en el resultado
    assertEquals(2, resultado.getPuntosRivalA());
    assertEquals(1, resultado.getPuntosRivalB());
}


    @Test
    public void equiposOrdenadosPorDerrotas() {

        LOG.info("Inicio de prueba Derrotas...");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);

        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));

        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23), Genero.FEMENINO);
        // Simulación de enfrentamientos y resultados
        torneo.registrarEnfrentamiento("Ubicacion1", LocalDate.now().plusDays(5), LocalTime.now().plusHours(2), equipoA, equipoB, List.of());
        Resultado resultado = new Resultado(equipoA, equipoB);
        resultado.sumarPunto(equipoA, 1);
        resultado.sumarPunto(equipoB, 2);
        resultado.determinarGanador();
        torneo.getEnfrentamientos().get(0).setResultado(resultado);

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

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
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
        assertEquals("EquipoA", equipoConEmpates.getNombre());
        assertEquals(1, equipoConEmpates.getEmpates());
    }


}


