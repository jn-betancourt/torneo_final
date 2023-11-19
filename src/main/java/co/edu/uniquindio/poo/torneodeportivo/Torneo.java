/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final Collection<Participante> participantes;
    private final CaracterTorneo caracter;
    // RQ1: genero incluido en el torneo
    private final GeneroTorneo generoTorneo;
    // RQ2: registro de jueces
    private Collection<Juez> jueces;
    // RQ3: registro de enfrentamientos
    private Collection<Enfrentamiento> enfrentamientos;

    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion, TipoTorneo tipoTorneo, CaracterTorneo caracter, GeneroTorneo genero) {
        
        ASSERTION.assertion( nombre != null , "El nombre es requerido");
        
        
        
        ASSERTION.assertion( numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion( limiteEdad >= 0,"El limite de edad no puede ser negativo");
        ASSERTION.assertion( valorInscripcion >= 0,"El valor de la inscripción no puede ser negativo");
        
        // RQ1: asecion genero torneo
        ASSERTION.assertion(genero != null, "Genero del torneo es necesario");
        
        this.nombre = nombre;
        
        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones); 
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.participantes = new LinkedList<>();
        this.caracter = Objects.requireNonNull(caracter,"El carácter del torneo es requerido");
        // RQ1: inicializar nueva propiedad 'genero'
        this.generoTorneo = genero;
        // RQ2: inicializar nueva propiedad 'Jueces'
        this.jueces = new ArrayList<>();
        // RQ3: inicializar nueva propiedad 'enfrentamientos'
        this.enfrentamientos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public CaracterTorneo getCaracter() {
        return caracter;
    }

    public GeneroTorneo getGeneroTorneo() {
        return generoTorneo;
    }

    public Collection<Juez> getJueces() {
        return jueces;
    }

    public Collection<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion( fechaInicio != null , "La fecha de inicio es requerida");
        ASSERTION.assertion( ( fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones) ) &&
                ( fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones) ),"La fecha de inicio no es válida" );
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion( fechaInicioInscripciones != null , "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }


    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion( fechaCierreInscripciones != null , "La fecha de cierre es requerida");
        ASSERTION.assertion( fechaCierreInscripciones.isAfter(fechaInicioInscripciones),"La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones" );
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }
    
    /**
     * Permite registrar un participante en el torneo
     * @param participante Participante a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo nombre, o en caso de que las inscripciones del torneo no estén abiertas.
     */
    public void registrarParticipante(Participante participante) {
        validarParticipanteExiste(participante); 

        validarInscripciopnesAbiertas(); 
        validarCaracter(participante);
        // Valida genero si el torneo NO es mixto
        if (generoTorneo != GeneroTorneo.MIXTO){
            validarGenero(participante);
        }
        participantes.add(participante);
    }

    /*
    *  RQ1: Validar genero del participante
    */
    private void validarGenero(Participante participante){
        ASSERTION.assertion(generoTorneo.validarGenero(participante), "El genero de un jugador no coincide con el torneo");
    }
    
    /*
     * RQ2: registrar juez al torneo
     */
    public void registrarJuez(Juez juez){
        validarJuezNoExiste(juez);// JUEZ NO DUPLICADO
        validarInscripciopnesAbiertas();// EL TORNEO TODAVIA PERMITE INSCRIPCIONES

        jueces.add(juez);
    }

    /*
     * RQ2: Validar juez no dubplicado
     */
    private void validarJuezNoExiste(Juez juez){
        Predicate<Juez> buscarJuez = j -> j.equals(juez);
        boolean condicion = !jueces.stream().anyMatch(buscarJuez);
        ASSERTION.assertion(condicion, "Juez duplicado");
    }

    /*
     * RQ3: Regsistrar un enfrentamiento
     */
    public void registrarEnfrentamiento(String ubicacion, LocalDate fecha, LocalTime hora, 
        Participante rivalA, Participante rivalB,Collection<Juez> jueces){
            Enfrentamiento nuevEnfrentamiento = new Enfrentamiento(ubicacion, fecha, hora, rivalA, rivalB, jueces);
            enfrentamientos.add(nuevEnfrentamiento);
    }
    
    /*
     * RQ4: devolver listado de enfrentamiento
     * por nombre de participante
     */
    public Optional<Collection<Enfrentamiento>> listarEnfrentamientosPorNombre(String nombre){
        /*
         * buscar enfrentamiento que contenga un participante
         * con el nombre dado
         */
        Predicate<Enfrentamiento> enfrentamiento = e-> e.getRivalA().getNombreCompleto().equals(nombre) || e.getRivalB().getNombreCompleto().equals(nombre);
        Collection<Enfrentamiento> enfrentamientosEncontrados = enfrentamientos.stream().filter(enfrentamiento).toList();
        return Optional.of(enfrentamientosEncontrados);
    }

    /*
     * RQ5: devolver listado de enfrentamiento
     * por licencia de Juez
     */
    public Optional<Collection<Enfrentamiento>> listarEnfrentamientosPorNumeroLicencia(int numeroLicencia){
        /*
         * buscar enfrentamiento que contenga un juez
         * con el numero dado
         */
        Predicate<Juez> juezEncontrado =  j->j.licencia()==numeroLicencia;
        Collection<Enfrentamiento> enfrentamientosEncontrados = enfrentamientos.stream().filter(e->{
            Optional<Juez> juez = e.getJueces().stream().filter(juezEncontrado).findAny();
            return juez.isPresent();
        }).toList();
        return Optional.of(enfrentamientosEncontrados);
    }
    
    /**
     * Valida que el participante sea acorde con el carácter del torneo.
     * @param participante Participante a ser registrado
     */
    private void validarCaracter(Participante participante) {
        ASSERTION.assertion( caracter.esValido(participante),"Las inscripciones no están abiertas");
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion( inscripcionAbierta,"Las inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarParticipanteExiste(Participante participante) {
        boolean existeEquipo = buscarParticipantePorNombre(participante.getNombreCompleto()).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los participantes registrados.
     * @return Collection<Participante> no modificable de los participantes registrados en el torneo.
     */
    public Collection<Participante> getParticipantes() {
        return Collections.unmodifiableCollection(participantes);
    }
    
    /**
     * Permite buscar un participante por su nombre entre los participantes registrados en el torneo
     * @param nombre Nombre del participante que se está buscando
     * @return Un Optional<Participante> con el participante cuyo nombre sea igual al nombre buscado, o un Optional vacío en caso de no encontrar un participante con nombre igual al dado.
     */
    public Optional<Participante> buscarParticipantePorNombre(String nombre){
        Predicate<Participante> condicion = participante->participante.getNombreCompleto().equals(nombre);
        return participantes.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     *  
     * @param nombre Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var participante = buscarParticipantePorNombre(nombre);
        
        participante.ifPresent( (e)->{
            if( e instanceof Equipo equipo){
                registrarJugador(equipo, jugador);
            }
        } );
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo
     * y su genero sea acorde al torneo.

     * @param equipo Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion( !LocalDate.now().isAfter(fechaCierreInscripciones) , "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador); 
        validarJugadorExiste(jugador);
        /*
         * Validar genero jugador RQ1
         */
        if (generoTorneo != GeneroTorneo.MIXTO){
            validarGenero(jugador);
        }
        equipo.registrarJugador(jugador);
    }

    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos registrados en el torneo.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no haber encontrado un jugador con el nombre y apellido del jugador buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        return participantes.stream()
            .filter(p->p instanceof Equipo)
            .map(p->(Equipo)p)
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion( limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo , "No se pueden registrar jugadores que excedan el limite de edad del torneo"); 
    }

}
