package SnowBros.src.Entidades.EntidadEstatica.PowerUps;
import SnowBros.src.Entidades.EntidadEstatica.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;
public abstract class PowerUp extends EntidadEstatica {
    protected static final int puntos = 300;
    protected boolean activo;
    protected Timer tiempoDeVida;
    protected Timer tiempoDeEfecto;

    public PowerUp(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public void eliminar(){
        //TODO implementar
    }
    public void afectarJugador(){
        //TODO implementar
    }
}
