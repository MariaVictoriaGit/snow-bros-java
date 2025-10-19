package SnowBros.src.Jugador;
import SnowBros.src.Juego.EntidadDinamica;
public class DisparoJugador extends EntidadDinamica {
    protected int potenciaDisparo;
    
    public DisparoJugador(Jugador jugador, boolean efecto){
        //TODO: ajustar posicion inicial del disparo segun direccion del jugador
        posX= jugador.getPosX(); //ajustar pos inicial para que no salga desde el pj
        posY= jugador.getPosY();
        direccion= jugador.getDireccion();
        velocidad= 15; // a definir
        potenciaDisparo=10; //a definir
        if(efecto){
            potenciaDisparo= potenciaDisparo*2;
        }
    }
}
