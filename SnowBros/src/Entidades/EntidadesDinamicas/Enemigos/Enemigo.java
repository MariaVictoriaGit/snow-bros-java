package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;
import SnowBros.src.Visitor.Visitable;

public abstract class Enemigo extends EntidadDinamica implements Visitable {
   private static final int tiempoCongelado=10000;
   protected int salud;
   protected Timer temporizado;
   protected boolean congelado;

   public Enemigo(int x, int y, Sprite sprites) {
      //la dificultad va a verse definida por lsa salud
      //de c/u y los tiempos congelado, desp cambiar
      super(x, y, sprites);
      temporizado=new Timer(tiempoCongelado);
      congelado=false;
   }
   public void recibirDaño(){
      salud--;
   }
   public boolean estaCongelado(){
      return congelado;
   }
   public abstract void convertirEnBola();
   public abstract void patronMovimiento();
   public abstract void agregarPowerUp(Boolean b);
   public abstract int getPuntajeCongelado();
   public abstract int getPuntajeEmpujado();
}
