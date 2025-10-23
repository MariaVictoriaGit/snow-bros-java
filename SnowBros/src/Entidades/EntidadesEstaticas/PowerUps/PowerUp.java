package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;
public abstract class PowerUp extends EntidadEstatica {
    protected static final int puntaje = 300;
    protected boolean activo;
    protected Timer tiempoDeVida;
    protected Timer tiempoDeEfecto;

    public PowerUp(int x, int y, Sprite sprite){
        super(x,y,sprite);
        activo=false;
        tiempoDeVida=new Timer(10000); //10s
        tiempoDeEfecto=new Timer(15000);//15s
    }
    public void eliminar(){
        activo=false;   
        //TODO implementar
    }
    public int getPuntaje(){
        return puntaje;
    }
}
