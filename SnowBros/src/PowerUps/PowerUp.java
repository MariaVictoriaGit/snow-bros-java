package SnowBros.src.PowerUps;
import SnowBros.src.Juego.Timer;
import SnowBros.src.Juego.EntidadEstatica;
public abstract class PowerUp extends EntidadEstatica {
    protected final int puntos = 300;
    protected boolean activo;
    protected Timer tiempoDeVida;
    protected Timer tiempoDeEfecto;

    public void eliminar(){
        //TODO implementar
    }
}
