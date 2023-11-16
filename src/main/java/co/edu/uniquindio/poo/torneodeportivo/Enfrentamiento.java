package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class Enfrentamiento{
    private String ubicacion;
    private LocalDate fecha;
    private LocalTime hora;
    private Participante rivalA;
    private Participante rivalB;
    private Collection<Juez> jueces;
    private Resultado resultado;
    private EstadoEnfrentamiento estado;

    public Enfrentamiento(String ubicacion, LocalDate fecha, LocalTime hora, Participante rivalA, Participante rivalB,Collection<Juez> jueces) {

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

    public void setEstado(EstadoEnfrentamiento estado) {
        this.estado = estado;
    }
    
}
