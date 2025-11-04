package SnowBros.src.Entidades.EntidadesDinamicas;
import java.awt.Rectangle;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.ObserverGrafico;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
  protected int direccion;
  protected int dirAnterior;
  protected int velocidad;
  protected int velocidadY;
  protected int tocandoSuelo;
  protected ObserverGrafico observer;//ObserverEntidad

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
    this.dirAnterior=this.direccion;
    this.setTocandoSuelo(tocandoSuelo);
    this.direccion=direccion;
  }
  public void registrarObserver(ObserverGrafico observer){//ObserverEntidad
    this.observer= observer;
  }
  public void setTocandoSuelo(int estado){
    tocandoSuelo=estado;
    if (observer != null) {
      observer.actualizar(direccion,estado);
    }
  }
  public String generarClaveEstado(){
    String estado=null;
    if(direccion==ConstantesEntidades.IZQUIERDA)
      estado="idleIzquierda";
    else
      estado="idleDerecha";
    return estado;
  }
  public int getTocandoSuelo(){
    return tocandoSuelo;
  }
  public ObserverGrafico getObserver(){
    return observer;
  }
  public void notificarObserver(){
    observer.actualizar();
  }
  public void verificarSuelo(){
    boolean enSuelo=false;
    for(Entidad e:this.nivel.getPlataformas()){
    Plataforma p=(Plataforma)e;
      if(nivel.getJugador().getJuego().estaCerca(this,p,40)){
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
  protected void verificarPared(Pared p){
    Rectangle paredRectangle=p.getHitbox().getRectangle();
    if(this.getHitbox().getRightHitBox().intersects(paredRectangle)) {
        posX-=this.ancho;
    }
    else if(this.getHitbox().getLeftHitBox().intersects(paredRectangle)) {
        posX+=this.ancho;//      if (hitbox.getRectangle().intersects(p.getHitbox().getRectangle())){
    }
    hitbox.actualizarHitbox(posX, posY, ancho, alto);
  }
  private void actualizarCaida(){
    if(tocandoSuelo==ConstantesEntidades.AIRE){
      velocidadY=2;
      posY+=velocidadY;
      this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
      this.getObserver().actualizar(direccion, getTocandoSuelo()); 
      for(Entidad p:nivel.getPlataformas()){
        Plataforma plataforma= (Plataforma) p;
        if(nivel.getJugador().getJuego().estaCerca(this,p,40)){
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
  public void visitar(Enemigo e) {}
  public void visitar(Plataforma e) {
    visitarPlataforma(e);
  }
  public void visitar(PlataformaMovil e) {
    visitarPlataforma(e);
  } 
  public void visitar(PlataformaQuebradiza e) {
    visitarPlataforma(e);
  }
  private void visitarPlataforma(Plataforma e){
    if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
      this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
    }
  }
  public void visitar(BotellaAzul b) {}
  public void visitar(BotellaRoja b) {}
  public void visitar(BotellaVerde b) {}
  public void visitar(Fruta f) {}
  public void visitar(VidaExtra v) {}
  public void visitar(Pared e){
    verificarPared(e);
  }
  public void visitar(ParedDestructible e) {
    verificarPared(e);
  }
  public void visitar(Escalera e) {}   
  public void visitar(SueloResbaladizo e) {}
  public void visitar(TrampaPinchos e) {}
  public void visitar(Jugador e) {}
}
