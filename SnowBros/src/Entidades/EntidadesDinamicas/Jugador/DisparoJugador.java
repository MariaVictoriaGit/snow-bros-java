package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
public class DisparoJugador extends EntidadDinamica {
    protected int potenciaDisparo;
    protected int velocidad;
    protected boolean efectoBotella;
    
    public DisparoJugador(int x, int y, Sprite sprite){
        super(x,y,sprite);
        //ajustar posicion inicial del disparo segun direccion del jugador
        //ajustar pos inicial para que no salga desde el pj
        //direccion= jugador.getDireccion();--->que tan necesaria es la direcion;
        velocidad= 15; // a definir
        potenciaDisparo=10; //a definir
    }

    public void setEfectoBotellaAzul(boolean efecto){
        efectoBotella=true;
    }

    public void activarDisparo(){ //TODO para?
        if(efectoBotella){
            potenciaDisparo=potenciaDisparo*2;
        }
    }


}
