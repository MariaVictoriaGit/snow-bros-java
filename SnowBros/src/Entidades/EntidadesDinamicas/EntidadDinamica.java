package SnowBros.src.Entidades.EntidadesDinamicas;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
    protected int direccion;
    protected int dirAnterior;
    protected Observer observer;
    protected boolean estadoAereo;
    protected int velocidad;

    public EntidadDinamica(int posX, int posY, Sprite mSprite){
      super(posX, posY, mSprite);
      registrarObserver(new ObserverEntidad(this));
      velocidad=8;
    }
    public int getDireccion(){
      return direccion;
    }
    public void setDireccion(int direccion){
      dirAnterior=direccion;
      this.direccion=direccion;
    }
    public abstract void mover();//antes no era abstract pero no tenia implemntacion 
    public void actualizar(int direccion, int estadoAereo){
      this.establecerEstadoAereo(estadoAereo);
      this.direccion=direccion;
    }
    public void registrarObserver(Observer observer){
      this.observer= observer;
    }
    public void establecerEstadoAereo(int estado){
      boolean estadoNuevo= (estado==ConstantesEntidades.AIRE);
      if (this.estadoAereo !=estadoNuevo) {
          estadoAereo= estadoNuevo;
          if (observer != null) {
              observer.actualizar(direccion,estado);
          }
      }
    }
    public int getEstadoAereo(){
        int state;

        if(estadoAereo==true){
            state=ConstantesEntidades.AIRE;
        }
        else{
            state=ConstantesEntidades.SUELO;
        }
        return state;
    }
    public Observer getObserver(){
        return observer;
    }
    public void notificarObserver(){
        observer.actualizar();
    }
    public void visitar(Enemigo e) {
        //se ignoran
    }
    public void visitar(Plataforma e) {
        //TODO esta arriba, todas estan arriba
    }
    public void visitar(BotellaAzul b) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(BotellaRoja b) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(BotellaVerde b) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(Fruta f) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(VidaExtra v) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(Pared e) {
      //TODO cambio de direccion
        throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
    public void visitar(ParedDestructible e) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
    }
   public void visitar(Escalera e) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
   }
   public void visitar(SueloResbaladizo e) {
      //lo ignora
    throw new UnsupportedOperationException("Unimplemented method 'visitar'");
   }
   public void visitar(TrampaPinchos e) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
   }
   public void visitar(Jugador e) {
      //lo ignora
      throw new UnsupportedOperationException("Unimplemented method 'visitar'");
   }
}
