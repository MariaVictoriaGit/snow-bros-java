package SnowBros.src.Entidades.EntidadesDinamicas;
import java.awt.Rectangle;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.BolaDeNieve;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Calabaza;
import SnowBros.src.Grafica.ObserverEntidad;
import SnowBros.src.Grafica.ObserverGrafico;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
  protected int direccion;
  protected int dirAnterior;
  protected int velocidad;
  protected int velocidadY;
  protected boolean saltando=false;
  protected boolean enAire=false;
  private int alturaSalto = 0;
  private final int ALTURAMAXSALTO = 64;
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
  public boolean getSaltando(){
    return saltando;
  }
  public boolean getEnAire(){
    return enAire;
  }
  public void setDireccion(int direccion){
    this.direccion=direccion;
  }
  public void setSaltando(boolean salto){
    saltando=salto;
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
    for(Plataforma p:this.nivel.getPlataformas()){
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
      for(Plataforma p:nivel.getPlataformas()){
        if(nivel.getJugador().getJuego().estaCerca(this,p,40)){
          p.aceptar(this);
        }
      }
    }
  }
  public void iniciarSalto(){
    if(tocandoSuelo==ConstantesEntidades.SUELO && posY<ConstantesVistas.PANEL_JUEGO_ALTO){
      Sonido.getInstancia().reproducirEfectoDeSonido("salto.wav");
      saltando=true;
      enAire=true;
      tocandoSuelo=ConstantesEntidades.AIRE;
      posY-=ConstantesDificultad.VELOCIDAD_SALTO_JUGADOR;
    }
  }
  public void actualizarSalto() {
    if (saltando) {
        if (alturaSalto < ALTURAMAXSALTO) {
            posY -= ConstantesDificultad.VELOCIDAD_SALTO_JUGADOR;
            alturaSalto+=ConstantesDificultad.VELOCIDAD_SALTO_JUGADOR;
            this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
        } else {
              saltando = false;
              alturaSalto=0;
         }
    }
}
  public void visitar(Enemigo e) {}
  public void visitar(Plataforma e) {
    velocidad=6;                
    visitarPlataforma(e);
  }
  public void visitar(PlataformaMovil e) {
    visitarPlataforma(e);
          this.setPosX(this.getPosX() + e.getDeltaX());
          tocandoSuelo=ConstantesEntidades.SUELO;
  } 
  public void visitar(PlataformaQuebradiza e) {
    visitarPlataforma(e);
  }
  protected void visitarPlataforma(Plataforma e){
    if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
      this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
      this.setPosY(e.getPosY() - this.getHitbox().getAlto());
      tocandoSuelo=ConstantesEntidades.SUELO;
      saltando=false;
      enAire=false;


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
  public void visitar(Escalera e) {
    //lo ignora
    throw new UnsupportedOperationException("Unimplemented method 'visitar'");
  }   
  public void visitar(SueloResbaladizo e) {

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
