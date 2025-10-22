package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;

public abstract class Enemigo extends EntidadDinamica {
   protected int salud;
   protected Timer temporizador;

   public Enemigo(int x, int y, Sprite sprites) {
      super(x, y, sprites);
      temporizador = new Timer();
   }
   public void recibirDaño(){
      salud--;
   }
   public boolean estaCongelado(){
      return false; //TODO implementar
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
   public abstract int getPuntaje();
}
