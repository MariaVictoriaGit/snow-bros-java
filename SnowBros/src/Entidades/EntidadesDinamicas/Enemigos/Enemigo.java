package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Timer;
import SnowBros.src.Visitor.Visitable;

public abstract class Enemigo extends EntidadDinamica implements Visitable {
   private static final int tiempoCongelado=10000;
   protected int velocidad;
   protected int direccion;
   protected int dirAnterior;
   protected int salud;
   protected Timer temporizado;
   protected boolean rodando;
   protected boolean muerto;
   protected boolean congelado;

   public Enemigo(int x, int y, Sprite sprites) {
      //la dificultad va a verse definida por lsa salud
      //de c/u y los tiempos congelado, desp cambiar
      super(x, y, sprites);
      temporizado=new Timer(tiempoCongelado);
      congelado=false;
      rodando=false;
      rodando=false;
   }
   public void mover(){
      //TODO mover
      /*
      if (muerto || congelado) return;
         if (direccion==ConstantesEntidades.DERECHA) {
               posX+=velocidad;
               if (posX>=ConstantesEntidades.DERECHA){
                  direccion=ConstantesEntidades.IZQUIERDA;
               }
         } else {
               posX-=velocidad;
               if (posX<=ConstantesEntidades.IZQUIERDA){
                  direccion=ConstantesEntidades.DERECHA;
               }
         }*/
   }
   public void recibirDaño(int daño){
      if (congelado || muerto) return;
      salud -= daño;
      if (salud <= 0) 
         convertirEnBola();   
   }
   public boolean getCongelado(){
      return congelado;
   }
   public boolean getRodando(){ 
      return rodando;
   }
   public boolean getMuerto(){ 
      return muerto; 
   }
   public abstract void convertirEnBola();
   public abstract void agregarPowerUp(Boolean b);
   public abstract int getPuntajeCongelado();
   public abstract int getPuntajeEmpujado();
   public void visitar(Jugador e) {
      e.perderVida();
   }
}
