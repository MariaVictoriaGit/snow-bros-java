package SnowBros.src.Enemigos;

import SnowBros.src.Juego.EntidadDinamica;
import SnowBros.src.Juego.Timer;

public class Enemigo extends EntidadDinamica {
    protected int salud;
    protected Timer temporizador;

    public void recibirDaño(){
       //TODO implementar
    }
    public boolean estaCongelado(){
      return estadoAereo;
       //TODO implementar, return predefinido por el compilador
    }
    public void convertirEnBola(){
       //TODO implementar
    }
    public void patronMovimiento(){
       //TODO implementar
    }
    public void agregarPowerUp(Boolean b){
       //TODO implementar
    }
}
