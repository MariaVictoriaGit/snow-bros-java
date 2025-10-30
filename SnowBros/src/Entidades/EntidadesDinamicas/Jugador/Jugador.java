package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;

import java.awt.Rectangle;
import javax.swing.Timer;
import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Grafica.Sprite;
//import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Visitor.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;

public class Jugador extends EntidadDinamica implements Visitador, Visitable{
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected int tocandoSuelo;
    protected int puntajeJugador;
    protected int vidas;
    protected String nombre;
    protected boolean disparoAumentado;
    protected int posXoriginal;
    protected int posYoriginal;
    protected Timer timerCaida;
    protected boolean invencible;

    public Jugador(int x,int y, Sprite mISprite){//no se usa observerJugador y en entidadDinamica se le asigna un observer a cada uno
        super(x,y,mISprite);
        vidas=vidaDeInicio;
        tocandoSuelo=ConstantesEntidades.SUELO;
        observer=new ObserverJugador(this);
        registrarObserver(observer);
        velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
        posXoriginal = x;
        posYoriginal = y;
        timerCaida = new Timer(16, e -> actualizarCaida());
        timerCaida.setRepeats(true);
    }
    public void mover(){
        //TODO ver como manejamos la herencia pq es de entidad dinamica
    }
    public void mover(int direccionAMover){
        dirAnterior=direccion;
        switch (direccionAMover){
            case ConstantesEntidades.QUIETO:
                direccion=direccionAMover;
                verificarSuelo();
                break;
            case ConstantesEntidades.DERECHA:
                direccion=direccionAMover;
                if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                posX+=velocidad;
                getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                verificarSuelo();
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                direccion=direccionAMover;
                if(posX-velocidad>0){
                posX-=velocidad;
                getHitbox().actualizarHitbox(posX,posY-8,ancho,alto-32);
                verificarSuelo();
                }
                break;
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
            case ConstantesEntidades.ARRIBA_DERECHA:
                direccion=direccionAMover;
                if(tocandoSuelo==ConstantesEntidades.SUELO)
                    this.iniciarSalto();
                // tocandoSuelo=ConstantesEntidades.AIRE;

                break;
        }   
    }
    private void verificarSuelo(){
        for(Entidad e:miJuego.getNivelActual().getPlataformas()){
            Plataforma p=(Plataforma)e;
            if(hitbox.getBottomHitBox().intersects(p.getHitbox().getTopHitBox())){
                tocandoSuelo=ConstantesEntidades.SUELO;
                break;
            }
        }
        if(tocandoSuelo==ConstantesEntidades.SUELO){
            tocandoSuelo=ConstantesEntidades.AIRE;
            iniciarCaida();
        }
    }
    public void iniciarCaida(){//gestiona la caida
        /*timerCaida = new Timer(ConstantesTiempos.TIEMPO_SALTO, e -> actualizarCaida());
        if(tocandoSuelo==ConstantesEntidades.AIRE){
            timerCaida.start();
        }
        else{
            timerCaida.stop();
        }*/
        if (tocandoSuelo == ConstantesEntidades.AIRE && !timerCaida.isRunning()) {
            timerCaida.start();
        } else if (tocandoSuelo != ConstantesEntidades.AIRE && timerCaida.isRunning()) {
            timerCaida.stop();
        }
    }
    private void actualizarCaida(){
        if(tocandoSuelo==ConstantesEntidades.AIRE){
            int velocidadY=2;
            int ajuste=36;
            posY+=velocidadY;
            this.getObserver().actualizar(direccion, getEstadoAereo());
            this.getHitbox().actualizarHitbox(posX,posY+ajuste,ancho,alto);
            for(Entidad p:miJuego.getNivelActual().getPlataformas()){
                Plataforma plataforma= (Plataforma) p;
                if(miJuego.estaCercaPlataforma(this, p, 50)){
                    plataforma.aceptar((Visitador) this);
                }
            }
        }
        else{
            timerCaida.stop();
        }
    }

    /*
    public void iniciarSalto(){
        //tal vez necesite un parametro para ver hacia donde salta con teclas w y a o d
        Timer timerSalto= new Timer(30, e-> actualizarSalto());
        timerSalto.start();
        posY-=velocidadY;//realiza la subida del salto
            
            //SI APUNTA A LA IZQ QUE SALTE A LA IZQ
                //QUE SUBA
            if(dirAnterior==ConstantesEntidades.ARRIBA_IZQUIERDA){

            }
            //SI APUNTA A LA DER QUE SALTE A LA DER
                //QUE SUBA
            else if(dirAnterior==ConstantesEntidades.ARRIBA_IZQUIERDA){

            }
            iniciarCaida();
    }  */
    public void iniciarSalto(){//TODO ver colisiones y caida si no encuentra plataforma
        //int velocidadY=ConstantesDificultad.VELOCIDAD_JUGADOR-3;
        if(tocandoSuelo==ConstantesEntidades.SUELO && posY<ConstantesVistas.PANEL_JUEGO_ALTO){
            tocandoSuelo=ConstantesEntidades.AIRE;
            observer.actualizar(direccion, getEstadoAereo());
            posY-=ConstantesDificultad.VELOCIDAD_SALTO_JUGADOR;
            iniciarCaida();
        }
    }
    public void disparar(){
        Sprite miDisparo=null;  // ponermos getfabrica()
        //TODO corregir
        DisparoJugador disparo=new DisparoJugador(posX,posY,miDisparo);
        miJuego.getNivelActual().agregarDisparo(disparo);

    }
    public void eliminar(){
        miNivel.eliminarJugador(this);
    }
    public void actualizarPuntaje(int puntaje){
        puntajeJugador+=puntaje;
    }
    public String generarClaveEstado(int direccionAnterior){
        String claveADevolver=null;
        switch(direccion){
            case ConstantesEntidades.IZQUIERDA:
                claveADevolver="moverIzquierda";
                break;
            case ConstantesEntidades.QUIETO:
                if (direccionAnterior == ConstantesEntidades.IZQUIERDA){
                    claveADevolver="idleIzquierda";
                }
                else if (dirAnterior == ConstantesEntidades.DERECHA){
                    claveADevolver="idleDerecha";
                }
                break;
            case ConstantesEntidades.DERECHA:
                claveADevolver= "moverDerecha";
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                    claveADevolver="saltandoDerecha";
                    break;
                case ConstantesEntidades.ARRIBA_IZQUIERDA:
                    claveADevolver="saltandoIzquierda";
                    break;
        }
        return claveADevolver;
    }
    public int getVidas(){
        return vidas;
    }
    public int getTiempo(){
        return miJuego.getTiempoActual();
    }
    public int getDireccion(){
        return direccion;
    }
    public String getNombre(){
        return nombre;
    }
    public int getDirAnterior(){
        return dirAnterior;
    }
    public int getPuntaje(){
        return puntajeJugador;
    }
    public int getVelocidad(){
        return velocidad;
    }
    public void setDireccion(int direccionActual){
        if(direccionActual!=0){
            dirAnterior=this.direccion;
        }
        this.direccion=direccionActual;
    }
    public void setJuego(Juego j){
        miJuego=j;
    }
    public void perderVida(){
        vidas--;
        if (vidas > -1)
        miJuego.quitarVida();
        if(vidas==0){
            //TODO reiniciar jugador
        }
    }
    public void setAire(){
        tocandoSuelo=ConstantesEntidades.AIRE;
    }
    public void setVidas(){
        vidas=vidaDeInicio;
 }
    public void sumarPuntaje(int p){
        puntajeJugador+=p;
        miJuego.actualizarPuntaje(puntajeJugador);
    }
    public void efectoBotellaAzul(BotellaAzul b){
        this.velocidad=ConstantesDificultad.EFECTO_BOTELLA_AZUL;
        b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
            this.velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
            b.getTiempoDeEfecto().resetear();
        } );
        b.getTiempoDeEfecto().iniciar();
        
        /*        if(afectadoBotellaAzul){
            return;
        }
        afectadoBotellaAzul=true;
        velocidad=ConstantesDificultad.EFECTO_BOTELLA_AZUL;
        hiloBotellaAzul=new Thread(()->{
        try{
            Thread.sleep(ConstantesTiempos.TIEMPO_EFECTO_POWER_UP);
        }catch(InterruptedException e){
            //podria tocar otros power ups tmb pero no del mismo
        }
            velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
            afectadoBotellaAzul=false;
        });
        hiloBotellaAzul.start();  */
    }
    public void efectoBotellaRoja(BotellaRoja b){
        this.disparoAumentado=true;
        b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
            this.disparoAumentado=false;
            b.getTiempoDeEfecto().resetear();
        });
        b.getTiempoDeEfecto().iniciar();
    }
    public void efectoBotellaVerde(){
        //TODO implementar
    }

    //Metodos de la interfaz Visitador
    public void visitar(Enemigo e){
        if (!invencible){
        perderVida();
        posX = posXoriginal;
        posY = posYoriginal;
        this.getObserver().actualizar(direccion, getEstadoAereo());
        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
        //TODO reiniciar al personaje en la posicion original
        }
    }
    public void visitar(Plataforma p){
        Rectangle hitboxJugador=this.getHitbox().getRectangle();
        Rectangle hitboxPlataforma=p.getHitbox().getRectangle();
        if(this.getHitbox().getBottomHitBox().intersects(p.getHitbox().getTopHitBox())){
            this.posY = hitboxPlataforma.y - hitboxJugador.height-24; //40 o 30 es el ajuste de momento
            this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
            tocandoSuelo=ConstantesEntidades.SUELO;
        }
    }
    public void visitar(BotellaAzul b){
        sumarPuntaje(b.getPuntaje());
        miNivel.eliminarPowerUp(b);
        miJuego.eliminarPowerUp(b);
        efectoBotellaAzul(b);
    }
    public void visitar(BotellaRoja b){
        puntajeJugador+=b.getPuntaje();
        miNivel.eliminarPowerUp(b);
        miJuego.eliminarPowerUp(b);
        efectoBotellaRoja(b);
    }
    public void visitar(BotellaVerde b){
        puntajeJugador+=b.getPuntaje();
        miNivel.eliminarPowerUp(b);
        miJuego.eliminarPowerUp(b);
        efectoBotellaVerde();
    }
    public void visitar(Fruta f){
        puntajeJugador+=f.getPuntaje();
    }
    public void visitar(VidaExtra v){
        if(vidas!=vidaDeInicio)
            vidas++;
        puntajeJugador+=v.getPuntaje();
    }
    public void visitar(Pared p){
        if(direccion == ConstantesEntidades.DERECHA){
            posX -= 8;
        }
        else if(direccion == ConstantesEntidades.IZQUIERDA){
            posX += 8;
        }
        //TODO Colision con los costados de la pared
    }
    public void visitar(ParedDestructible p){
        //TODO creo q las paredes se destruian con bolas de nieve
        p.destruirse();
    }
    public void visitar(Escalera e){
        subirEscalera();
    }
    private void subirEscalera(){
        //TODO implementar
    }
    public void visitar(SueloResbaladizo e){
        velocidad-=3;
    }
    public void visitar(TrampaPinchos t){
        perderVida();
        posX = posXoriginal;
        posY = posYoriginal;
        //TODO Reiniciar al personaje a la posicion origianl
    }
    public void visitar(Jugador j){
        //Que hacemos aca? 
        //      Buena pregunta
    }
    //Metodo de la interfaz Visitable
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}
