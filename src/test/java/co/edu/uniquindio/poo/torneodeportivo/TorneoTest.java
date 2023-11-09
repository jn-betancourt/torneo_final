/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class TorneoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());
    
    /**
     * Verificar que la clase Torneo almacene y recupere los datos 
     * 
     */
    @Test
    public void datosCompletos() {
        LOG.info("Inicio de prueba datos completos...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL|GRUPAL
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1), LocalDate.of(2023, 9, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO);

        // Recuperación y verificación de datos
        assertEquals("Copa Mundo",torneo.getNombre());
        assertEquals(LocalDate.of(2023, 10, 1),torneo.getFechaInicio());
        assertEquals(LocalDate.of(2023, 8, 1),torneo.getFechaInicioInscripciones());
        assertEquals(LocalDate.of(2023, 9, 15),torneo.getFechaCierreInscripciones());
        assertEquals((byte)24,torneo.getNumeroParticipantes());
        assertEquals((byte)0,torneo.getLimiteEdad());
        assertEquals(0,torneo.getValorInscripcion());
        assertEquals(TipoTorneo.LOCAL,torneo.getTipoTorneo());
        LOG.info("Fin de prueba datos completos...");
    }

    /**
     * Verificar que la clase Torneo valide que se ingrese los datos
     * 
     */
    @Test
    public void datosNulos() {
        LOG.info("Inicio de prueba datos nulos...");
        // Almacenar los datos de prueba null|null|null|null|24|0|0|null|LOCAL|GRUPAL|MIXTO
        assertThrows(Throwable.class, ()-> new Torneo(null, null, null, null, (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        
        LOG.info("Fin de prueba datos nulos...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de número de participantes negativo 
     * 
     */
    @Test
    public void participantesNegativos() {
        LOG.info("Inicio de prueba número de participantes negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|-24|0|0|LOCAL|GRUPAL|MIXTO
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)-24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba  número de participantes negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de limites de edades negativo 
     * 
     */
    @Test
    public void limiteEdadesNegativo() {
        LOG.info("Inicio de prueba limites de edades negativo...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|-1|0|LOCAL|GRUPAL|MIXTO
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)-1, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba  limites de edades negativo...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de valor de inscripción negativa
     * 
     */
    @Test
    public void inscripcionNegativa() {
        LOG.info("Inicio de prueba inscripción negativa...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|-1|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01), LocalDate.of(2023, 10, 15), (byte)24, (byte)0, -1,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba inscripción negativa...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inscripciones posteriores a la 
     * fecha de inicio del torneo
     * 
     */
    @Test
    public void inscripcionTardia() {
        LOG.info("Inicio de prueba inscripción tardia...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 01), LocalDate.of(2023, 11, 15), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba inscripción tardia...");
    }

    /**
     * Verificar que la clase Torneo valide que el ingreso de inicio inscripciones posteriores a 
     * la fecha de cierre de inscripciones
     * 
     */
    @Test
    public void cierreInscripcionAnteriorInicio() {
        LOG.info("Inicio de prueba Cierre inscripción anterior al inicio...");
        // Almacenar los datos de prueba Copa Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL|GRUPAL
        assertThrows(Throwable.class, ()-> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 1), (byte)24, (byte)0, 0,TipoTorneo.LOCAL,CaracterTorneo.GRUPAL, GeneroTorneo.MIXTO));
        
        LOG.info("Fin de prueba Cierre inscripción anterior al inicio...");
    }

    /*
     * Tests RQ1
     */

    // Testear error al añadir jugadores de un genero errado
    @Test
    public void torneoMasculinoJugadorFemenino(){
        LOG.info("Inicio de prueba agreagr jugador femenino a torneo masculino...");
        var jugadora = new Jugador("Jenifer", "Gonzales", "jg@mail.com", "32221", LocalDate.of(2006, 2, 8), Genero.FEMENINO);
        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)20, 0,TipoTorneo.LOCAL,CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);
        assertThrows(Throwable.class,()->torneo.registrarParticipante(jugadora));
        LOG.info("Fin de prueba agreagr jugador femenino a torneo masculino...");
    }
    // Testear comprobacion para genero correcto
    @Test
    public void torneoMasculinoJugadorMasculino(){
        LOG.info("Inicio de prueba agreagr jugador masculino a torneo masculino...");
        var jugador = new Jugador("Julian", "Gonzales", "jg@mail.com", "32221", LocalDate.of(2006, 2, 8), Genero.MASCULINO);
        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)20, 0,TipoTorneo.LOCAL,CaracterTorneo.INDIVIDUAL, GeneroTorneo.MASCULINO);

        torneo.registrarParticipante(jugador);
        System.out.println(torneo.getParticipantes());
        LOG.info("Fin de prueba agreagr jugador masculino a torneo masculino...");
    }
}