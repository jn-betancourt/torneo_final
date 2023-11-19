package co.edu.uniquindio.poo.torneodeportivo;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Resultado {
    private Equipo rivalA;
    private Equipo rivalB;
    private int puntosRivalA;
    private int puntosRivalB;
    //Guardara el resultado de ganado
    //si no hay ganador quedara como null
    private Participante ganador;

    public Resultado(Equipo rivalA, Equipo rivalB) {
        ASSERTION.assertion(rivalA!=null, "los participantes no pueden ser nulos");
        ASSERTION.assertion(rivalB!=null, "los participantes no pueden ser nulos");

        this.rivalA = rivalA;
        this.rivalB = rivalB;
        this.puntosRivalA = 0;
        this.puntosRivalB = 0;
    }

    public void sumarPunto(Equipo participante, int cantidad){
        ASSERTION.assertion(cantidad>0, "La cantidad no puede ser negativa");

        if(participante.equals(rivalA)){
            puntosRivalA += cantidad;
        }else{
            puntosRivalB += cantidad;
        }
    }

    public void determinarGanador(){
        if(puntosRivalA>puntosRivalB){
            ganador = rivalA;
            rivalA.sumarVictoria();
            rivalB.sumarDerrota();
        }else if(puntosRivalA<puntosRivalB){
            ganador = rivalB;
            rivalA.sumarDerrota();
            rivalB.sumarVictoria();
        }else{
            ganador = null;
            rivalA.sumarEmpate();
            rivalB.sumarEmpate();
        }
    }

    public Participante getRivalA() {
        return rivalA;
    }

    public Participante getRivalB() {
        return rivalB;
    }
    
    public int getPuntosRivalA() {
        return puntosRivalA;
    }

    public int getPuntosRivalB() {
        return puntosRivalB;
    }

    public Participante getGanador() {
        return ganador;
    }   
    
}
