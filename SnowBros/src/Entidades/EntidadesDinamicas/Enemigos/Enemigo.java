package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.DisparoJugador;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitable;
import SnowBros.src.Visitor.Visitador;

public abstract class Enemigo extends EntidadDinamica implements Visitable {
   protected int salud=1;
   protected int tocandoSuelo;
   protected Temporizador temporizadoCongelado;
   protected Temporizador accionMovimientoEnemigo;
   protected boolean rodando;
   protected boolean muerto;
   protected boolean congelado;
   protected boolean detenido;

   public Enemigo(int x, int y, Sprite sprites) {
      //la dificultad va a verse definida por lsa salud
      //de c/u y los tiempos congelado, desp cambiar
      super(x, y, sprites);
      temporizadoCongelado=new Temporizador(ConstantesTiempos.TIEMPO_CONGELADO_ENEMIGOS);
      accionMovimientoEnemigo=new Temporizador(ConstantesTiempos.TOPE_TIEMPO_JUEGO);
      congelado=false;
      muerto=false;
      rodando=false;
      detenido=false;
      tocandoSuelo=ConstantesEntidades.SUELO;
   }
   public abstract void mover();
   public void detener(){
      detenido=true;
   }
   public void avanzar(){
      detenido=false;
   }
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
   
   public abstract void patronDeMovimiento();

   public void recibirDaño(int daño){
      salud -= daño;
      if (salud <= 0){
         congelado=true;
         muerto=true; 
         congelar();  
      } 
   }
   
   public String generarClaveEstado(){
        String estado=null;
        if(congelado )
            estado="congelado";
        else{
         if(direccion==ConstantesEntidades.IZQUIERDA)
            estado="idleIzquierda";
        else
            estado="idleDerecha";
        }
        return estado;
               
   }
   public void setCongelado(boolean b){
      congelado=b;
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
   public void congelar(){
      detenido=true;
      direccion=ConstantesEntidades.QUIETO;
      observer.actualizarBolaDeNieve();
   }
   //solo va a ser llamado si el jugador colisiona con el enemigo
   public void convertirEnBola(){
      if(congelado){
         nivel.getJuego().crearBolaDeNieve(posX,posY);
         this.eliminar();
      }
         
   }
   public abstract void agregarPowerUp(Boolean b);
   public abstract int getPuntajeCongelado();
   public abstract int getPuntajeEmpujado();
   public void visitar(Jugador e){
      //TODO
   }
   public void eliminar(){
      nivel.eliminarEnemigo(this);
   }

   abstract public void aceptar(Visitador v);

}
