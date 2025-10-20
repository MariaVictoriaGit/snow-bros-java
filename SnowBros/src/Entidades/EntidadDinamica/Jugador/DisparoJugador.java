package SnowBros.src.Entidades.EntidadDinamica.Jugador;
import SnowBros.src.Entidades.EntidadDinamica.EntidadDinamica;
public class DisparoJugador extends EntidadDinamica {
    protected int potenciaDisparo;
    
    public DisparoJugador(Jugador jugador, boolean efectoBotella){
        //TODO: ajustar posicion inicial del disparo segun direccion del jugador
        posX= jugador.getPosX(); //ajustar pos inicial para que no salga desde el pj
        posY= jugador.getPosY();
        direccion= jugador.getDireccion();
        velocidad= 15; // a definir
        potenciaDisparo=10; //a definir
        if(efectoBotella){
            potenciaDisparo= potenciaDisparo*2;
        }
    }
}
