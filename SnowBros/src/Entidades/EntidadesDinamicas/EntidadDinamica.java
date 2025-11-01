package SnowBros.src.Entidades.EntidadesDinamicas;

import java.awt.Rectangle;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Grafica.Observer;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
    protected int direccion;
    protected int dirAnterior;
    protected int velocidad;
    protected int velocidadY;
    protected int tocandoSuelo;
    protected Observer observer;//ObserverEntidad
    private Nivel nivel;

    public EntidadDinamica(int posX, int posY, Sprite mSprite){
      super(posX, posY, mSprite);
      observer=new ObserverEntidad(this);
      registrarObserver(observer);
      velocidad=8;
      velocidadY=2;
    }
    public int getDireccion(){
      return direccion;
    }
    public int getDirAnterior(){
      return dirAnterior;
    }
    public void setDireccion(int direccion){
      this.direccion=direccion;
    }
    public abstract void mover();

    public void actualizar(int direccion, int tocandoSuelo){
      this.setTocandoSuelo(tocandoSuelo);
      this.direccion=direccion;
    }
    public void registrarObserver(Observer observer){//ObserverEntidad
      this.observer= observer;
    }
    public void setTocandoSuelo(int estado){
          tocandoSuelo=estado;
          if (observer != null) {
              observer.actualizar(direccion,estado);
          }
    }
    public void setNivel(Nivel n){
      nivel=n;
    }
    public String generarClaveEstado(){
        String estado=null;
        if(direccion!=ConstantesEntidades.QUIETO){
            if(direccion==ConstantesEntidades.IZQUIERDA)
                estado="moverIzquierda";
            else
                estado="moverDerecha";
       }
        return estado;
    }
    public int getTocandoSuelo(){
      return tocandoSuelo;

    }
    public Observer getObserver(){
        return observer;
    }
    public void notificarObserver(){
        observer.actualizar();
    }
    public void verificarSuelo(){
        boolean enSuelo=false;
        for(Entidad e:this.nivel.getPlataformas()){
            Plataforma p=(Plataforma)e;
            if(nivel.estaCercaPlataforma(this,p,40)){
              if(hitbox.getBottomHitBox().intersects(p.getHitbox().getRectangle())){
                enSuelo=true;
              }
            }
        }
        if(enSuelo){
          tocandoSuelo=ConstantesEntidades.SUELO;
          velocidadY=0;
        }
        else{
          tocandoSuelo=ConstantesEntidades.AIRE;
          actualizarCaida();
        }
    }
    private void actualizarCaida(){
        if(tocandoSuelo==ConstantesEntidades.AIRE){
            velocidadY=2;
            posY+=velocidadY;
            this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
            this.getObserver().actualizar(direccion, getTocandoSuelo());
            for(Entidad p:nivel.getPlataformas()){
                Plataforma plataforma= (Plataforma) p;
                if(nivel.estaCercaPlataforma(this, p, 40)){
                    plataforma.aceptar((Visitador) this);
                    tocandoSuelo=ConstantesEntidades.SUELO;
                }
            }
        }
    }
    public void iniciarSalto(){
        if(tocandoSuelo==ConstantesEntidades.SUELO && posY<ConstantesVistas.PANEL_JUEGO_ALTO){
           posY-=ConstantesDificultad.VELOCIDAD_SALTO_JUGADOR;
           tocandoSuelo=ConstantesEntidades.AIRE;
           observer.actualizar(direccion, getTocandoSuelo());
           actualizarCaida();
        }
    }
      public void visitar(Enemigo e) {
        //se ignoran
    }
    public void visitar(Plataforma e) {
      HitBox hitboxPlataforma=e.getHitbox();
      HitBox hitboxEntidad=this.getHitbox();
      if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
            this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
        }
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
