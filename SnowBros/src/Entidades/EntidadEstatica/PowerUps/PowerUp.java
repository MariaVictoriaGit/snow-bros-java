package SnowBros.src.Entidades.EntidadEstatica.PowerUps;
import SnowBros.src.Entidades.EntidadEstatica.EntidadEstatica;
import SnowBros.src.Juego.Timer;
public abstract class PowerUp extends EntidadEstatica {
    protected static final int puntos = 300;
    protected boolean activo;
    protected Timer tiempoDeVida;
    protected Timer tiempoDeEfecto;

    public void eliminar(){
        //TODO implementar
    }
    public void afectarJugador(){
        //TODO implementar
    }
}
