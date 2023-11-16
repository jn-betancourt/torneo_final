package co.edu.uniquindio.poo.torneodeportivo;

public class Resultado {
    private Participante rivalA;
    private Participante rivalB;
    private int puntosRivalA;
    private int puntosRivalB;
    public Resultado(Participante rivalA, Participante rivalB) {
        this.rivalA = rivalA;
        this.rivalB = rivalB;
    }

    public void sumarPunto(Participante participante, int cantidad){
        if(participante.equals(rivalA)){
            puntosRivalA += cantidad;
        }else{
            puntosRivalB += cantidad;
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
    
}
