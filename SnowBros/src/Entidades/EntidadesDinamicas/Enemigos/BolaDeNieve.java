package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Timer tiempo;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Timer(10000);//10s
        estaActiva=false;
    }
    public boolean getEstado(){
        return estaActiva;
    }
    public Timer getTimer(){
        return tiempo;
    }
}
