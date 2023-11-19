package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class AgendarEnfrentamientoTest {

    @Test
    public void cambiarEstadoEnfrentamiento() {
        // Crear instancias para la prueba
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),LocalDate.now().plusDays(15), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL, CaracterTorneo.INDIVIDUAL,GeneroTorneo.MASCULINO);
        Juez juez1 = new Juez("Juez1", "Apellido1", "juez1@gmail.com", 123, 567);
        Juez juez2 = new Juez("Juez2", "Apellido2", "juez2@gmail.com", 456, 789);
        torneo.registrarJuez(juez1);
        torneo.registrarJuez(juez2);
        Equipo equipoA = new Equipo("EquipoA", new Persona("Juan", "castaño", "castaño@gmail", "iphone12", Genero.MASCULINO));
        Equipo equipoB = new Equipo("EquipoB", new Persona("Jose", "Garcia", "jose@gmail", "A21", Genero.MASCULINO));
        Jugador jugador1 = new Jugador("Angel", "castaño", "casta@gmail.com", "iphone", LocalDate.of(1, 5, 14),Genero.MASCULINO);
        Jugador jugador2 = new Jugador("manuela", "galvis", "g@gmail.com", "samsung", LocalDate.of(4, 1, 23),Genero.FEMENINO);
        equipoA.registrarJugador(jugador1);
        equipoB.registrarJugador(jugador2);

        // Agendar el enfrentamiento en el torneo
        torneo.registrarEnfrentamiento("Ubicacion", LocalDate.now().plusDays(5), LocalTime.now().plusHours(2),
                equipoA, equipoB, List.of(juez1, juez2));

        // Obtener el enfrentamiento del torneo
        Optional<Enfrentamiento> enfrentamientoOptional = torneo.getEnfrentamientos().stream().findFirst();

        // Verificar que el enfrentamiento existe
        assertEquals(true, enfrentamientoOptional.isPresent());
        // Cambiar a EN JUEGO
        enfrentamientoOptional.get().setEstado(EstadoEnfrentamiento.EN_JUEGO);
        assertEquals(EstadoEnfrentamiento.EN_JUEGO, enfrentamientoOptional.get().getEstado());


        // Cambiar a FINALIZADO con un resultado
        Resultado resultado = new Resultado(equipoA, equipoB);
        resultado.sumarPunto(equipoA, 2);
        resultado.sumarPunto(equipoB, 1);

        enfrentamientoOptional.get().setResultado(resultado);
        enfrentamientoOptional.get().setEstado(EstadoEnfrentamiento.FINALIZADO);

        // Verificar que el estado se haya cambiado correctamente
        assertEquals(EstadoEnfrentamiento.FINALIZADO, enfrentamientoOptional.get().getEstado());
        // Verificar los puntos en el resultado
        assertEquals(2, enfrentamientoOptional.get().getResultado().getPuntosRivalA());
        assertEquals(1, enfrentamientoOptional.get().getResultado().getPuntosRivalB());
    }
}


        
        

        



