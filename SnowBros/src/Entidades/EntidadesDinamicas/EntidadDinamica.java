package SnowBros.src.Entidades.EntidadesDinamicas;
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
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Visitor.Visitador;

public abstract class EntidadDinamica extends Entidad implements Visitador{
  protected int direccion;
  protected int dirAnterior;
  protected int velocidad;
  protected int velocidadY;
  protected boolean saltando;
  protected boolean enAire;
  private int alturaSalto;
  protected int alturaMax;
  protected int tocandoSuelo;
  protected int velocidadSalto;
  protected long ultimoSalto;
  protected ObserverEntidad observer;//ObserverEntidad
  public EntidadDinamica(int posX, int posY, Sprite mSprite){
    super(posX, posY, mSprite);
    observer=new ObserverEntidad(this);
    velocidad=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_X;
    velocidadY=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_Y;
    alturaMax=ConstantesDificultad.ALTURA_MAX_SALTO;
    alturaSalto=0;
    ultimoSalto=0;
    enAire=false;
    saltando=false;
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
  public void setTocandoSuelo(int estado){
    tocandoSuelo=estado;
  }
  public String generarClaveEstado(){
    String estado=null;
    switch (direccion) {
      case ConstantesEntidades.IZQUIERDA:
        estado="normalIzquierda";
        break;
      case ConstantesEntidades.DERECHA:
        estado="normalDerecha";
        break;
      case ConstantesEntidades.QUIETO:
        estado="normalIzquierda";
        break;
    }
    return estado;
  }
  public int getTocandoSuelo(){
    return tocandoSuelo;
  }
  public ObserverEntidad getObserver(){
    return observer;
  }
  public void notificarObserver(){
    observer.actualizar();
  }
  public void verificarSuelo(){
    boolean enSuelo=false;
    for(Plataforma p:this.nivel.getPlataformas()){
      if(nivel.getJugador().getJuego().estaCerca(this,p,40)){
        if(hitbox.getBottomHitBox().intersects(p.getHitbox().getTopHitBox())){
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
  //protected abstract void verificarPared(Pared p);
  private void actualizarCaida(){
    if(tocandoSuelo==ConstantesEntidades.AIRE){
      velocidadY=ConstantesDificultad.VELOCIDAD_ENTIDAD_DINAMICA_Y;
      posY+=velocidadY;
      this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
      for(Plataforma p:nivel.getPlataformas())
        if(nivel.getJugador().getJuego().estaCerca(this,p,40))
          p.aceptar(this);
    }
  }
  public void iniciarSalto(){
    long tiempoHilo=System.currentTimeMillis();
    if(tiempoHilo-ultimoSalto>500 && tocandoSuelo==ConstantesEntidades.SUELO && posY<ConstantesVistas.PANEL_JUEGO_ALTO){
      Sonido.getInstancia().reproducirEfectoDeSonido("salto.wav");
      saltando=true;
      enAire=true;
      tocandoSuelo=ConstantesEntidades.AIRE;
      posY-=ConstantesDificultad.VELOCIDAD_SALTO;
    }
  }
  public void actualizarSalto() {
    if (saltando) {
        if (alturaSalto < alturaMax) {
            posY -= ConstantesDificultad.VELOCIDAD_SALTO;
            alturaSalto+=ConstantesDificultad.VELOCIDAD_SALTO;
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
   // this.verificarPared(e);
  }
  public void visitar(ParedDestructible e) {
   // verificarPared(e);
  }
  public void visitar(Escalera e) {
      if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
            this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
            this.setPosY(e.getPosY() - this.getHitbox().getAlto());
            tocandoSuelo=ConstantesEntidades.SUELO;
            saltando=false;
            enAire=false;
        }
  }   
  public void visitar(SueloResbaladizo e) {
      if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
            this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
            this.setPosY(e.getPosY() - this.getHitbox().getAlto());
            tocandoSuelo=ConstantesEntidades.SUELO;
            saltando=false;
            enAire=false;
        }
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
