/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Equipo implements Participante {

    private String nombre;
    private Persona representante;
    private Collection<Jugador> jugadores;
    // RQ6: numero de victorias, derrotas y/o empates
    private int victorias = 0;
    private int derrotas = 0;
    private int empates = 0;

    public Equipo(String nombre, Persona representante, Collection<Jugador> jugadores){
        ASSERTION.assertion( nombre != null && !nombre.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( representante != null , "El representante es requerido");
        this.nombre = nombre;
        this.representante = representante;
        this.jugadores = jugadores;
    }

    public Equipo(String nombre, Persona representante){
        ASSERTION.assertion( nombre != null && !nombre.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( representante != null , "El representante es requerido");
        this.nombre = nombre;
        this.representante = representante;
        this.jugadores = new ArrayList<>();
    }

    /**
     * Permite registrar un jugador en un equipo siempre y cuando no exista ya un jugador registrado en el equipo con el mismo nombre y apellido
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Jugador jugador) {
        validarJugadorExiste(jugador);
        jugadores.add(jugador);
    }

    /**
     * Permimte buscar un jugador en el equipo basado en su nombre y apellido.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador que coincida con el nombre y apellido del jugador buscado, 
     * o Optinal vacío en caso de no encontrar un jugador en el equipo con dicho nombre y apellido.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        Predicate<Jugador> nombreIgual = j->j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j->j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /*
     * Control de las variables
     * con las que se determinara 
     * el orden del requisito RQ6
     */
    public void sumarVictoria(){
        this.victorias += 1;
    }
    public void sumarDerrota(){
        this.derrotas += 1;
    }
    public void sumarEmpate(){
        this.empates += 1;
    }

    @Override
    public String getNombreCompleto() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getRepresentante() {
        return representante;
    }

    public void setRepresentante(Persona representante) {
        this.representante = representante;
    }

    public Collection<Jugador> getJugadores() {
        return jugadores;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

}
