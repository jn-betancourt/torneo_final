package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class Enfrentamiento{
    private String ubicacion;
    private LocalDate fecha;
    private LocalTime hora;
    private Equipo rivalA;
    private Equipo rivalB;
    private Collection<Juez> jueces;
    private Resultado resultado;
    private EstadoEnfrentamiento estado;

    public Enfrentamiento(String ubicacion, LocalDate fecha, LocalTime hora, Equipo rivalA, Equipo rivalB,Collection<Juez> jueces) {

        ASSERTION.assertion(!ubicacion.isBlank(), "Ubicacion requerida");
        ASSERTION.assertion(fecha!=null, "Fecha requerida");
        ASSERTION.assertion(hora!=null, "hora requerida");
        ASSERTION.assertion(rivalA!=null && rivalB!=null, "rival requerido");
        ASSERTION.assertion(jueces.size()>=1 , "rival requerida");

        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.hora = hora;
        this.rivalA = rivalA;
        this.rivalB = rivalB;
        this.jueces = jueces;
        this.estado = EstadoEnfrentamiento.PENDIENTE;
        this.resultado = new Resultado(rivalA, rivalB);
    }

    public void aplazarEnfrentamiento(LocalDate nuevaFecha, LocalTime nuevaHora){
        // verificar fecha mayor a la actual
        ASSERTION.assertion(
            fecha.isAfter(nuevaFecha),
            "La fecha debe ser mayor a la actual"
        );
        // verificar hora mayor a la actual
        ASSERTION.assertion(
            hora.isAfter(nuevaHora),
            "La fecha debe ser mayor a la actual"
        );
        // cambiar los valores de las variables
        setFecha(nuevaFecha);
        setHora(nuevaHora);
        setEstado(EstadoEnfrentamiento.APLAZADO);
    }

    public void iniciarEnfrentamiento(){
        //fecha y hora de inicio
        //son las actuales
        ASSERTION.assertion(
            LocalDate.now().equals(fecha),
            "La fecha no se cumple"
        );
        setEstado(EstadoEnfrentamiento.EN_JUEGO);
    }

    public void finalizarEnfrentamiento(){
        // verificar que la hora sea mayor
        ASSERTION.assertion(
            hora.isBefore(LocalTime.now()),
            "La hora no se cumple" 
        );
        setEstado(EstadoEnfrentamiento.FINALIZADO);
        resultado.determinarGanador();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Participante getRivalA() {
        return rivalA;
    }

    public Participante getRivalB() {
        return rivalB;
    }

    public Collection<Juez> getJueces() {
        return jueces;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public EstadoEnfrentamiento getEstado() {
        return estado;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    private void setEstado(EstadoEnfrentamiento estado){
        this.estado = estado;
    }
}
