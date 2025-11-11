package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Sonido;
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
   protected long tiempoEstado = 0;
   protected long inicioCongelado=0;
   protected int estado = EstadosDePatron.MOVERDERECHA;

   public Enemigo(int x, int y, Sprite sprites) {
      //la dificultad va a verse definida por lsa salud
      //de c/u y los tiempos congelado, desp cambiar
      super(x, y, sprites);
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

   public abstract int getSalud();

   public void recibirDaño(int daño){
      salud -= daño;
      if (salud <= 0){
         congelado=true;
         congelar();
         inicioCongelado=System.currentTimeMillis();
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
   public void actualizarTiempoCongelado(){
      long tiempoADescongelar=5000;
         if(detenido && System.currentTimeMillis()>inicioCongelado+tiempoADescongelar){
            descongelar();
         }
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
      Sonido.getInstancia().reproducirEfectoDeSonido("enemigoMuereBolaNieve.wav");
      detenido=true;
      direccion=ConstantesEntidades.QUIETO;
      if(observer!=null)
         observer.actualizarBolaDeNieve();
   }
   public void descongelar(){
      detenido=false;
      congelado=false;
      direccion=this.dirAnterior;
      salud=this.getSalud();
   }
   public void convertirEnBola(){ //metodo llamado al colisionar con la bola
      if(congelado){
         this.eliminar(); 
         nivel.getJuego().crearBolaDeNieve(posX,posY);
      }
   }
   public void agregarPowerUp(Boolean b){

      //nivel.agregarPowerUp(b,posX,posY);
   }
   public abstract int getPuntajeCongelado();
   public abstract int getPuntajeEmpujado();
   public void eliminar(){
      if(observer!=null){
         observer.borrarObserver();
         nivel.eliminarEnemigo(this);
         observer=null;
      }
   }
    public void aceptar(Visitador v){
      v.visitar(this);
   }
}
