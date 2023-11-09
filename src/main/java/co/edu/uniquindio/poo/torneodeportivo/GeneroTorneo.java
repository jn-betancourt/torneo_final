package co.edu.uniquindio.poo.torneodeportivo;

import java.util.function.Predicate;

public enum GeneroTorneo {
    MASCULINO{
        public boolean validarGenero(Participante participante){
            boolean condicion;
            // Si participante es un equipo valida jugadores
            if(participante instanceof Equipo){
                Predicate<Jugador> generoJugador = j->!j.getGenero().equals(Genero.MASCULINO);
                condicion = ((Equipo)participante).jugadores().stream().anyMatch(generoJugador);
            }else{
                // Valida un jugador
                condicion = ((Jugador)participante).getGenero().equals(Genero.MASCULINO);
            }
            return condicion;
        }
    },
    FEMENINO{
        public boolean validarGenero(Participante participante){
            boolean condicion;
            // Si participante es un equipo valida jugadores
            if(participante instanceof Equipo){
                Predicate<Jugador> generoJugador = j->!j.getGenero().equals(Genero.FEMENINO);
                condicion = ((Equipo)participante).jugadores().stream().anyMatch(generoJugador);
            }else{
                // Valida un jugador
                condicion = ((Jugador)participante).getGenero().equals(Genero.FEMENINO);
            }
            return condicion;
        }
    },
    MIXTO{
        public boolean validarGenero(Participante participante){
            return true;            
        }
    };

    public abstract boolean validarGenero(Participante participante);
}
