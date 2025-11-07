package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Grafica.Sprite;
//import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Visitor.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaQuebradiza;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;

public class Jugador extends EntidadDinamica implements Visitable{
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected int puntajeJugador;
    protected int vidas;
    protected String nombre;
    protected boolean disparoAumentado;
    protected int posXoriginal;
    protected int posYoriginal;
    protected boolean habilitadoASubirEscalera;
    boolean invencible;
    
    public Jugador(int x,int y, Sprite mISprite){//no se usa observerJugador y en entidadDinamica se le asigna un observer a cada uno
        super(x,y,mISprite);
        vidas=ConstantesDificultad.VIDAS_INICIO;
        tocandoSuelo=ConstantesEntidades.SUELO;
        observer=new ObserverJugador(this);
        registrarObserver(observer);
        velocidad=ConstantesDificultad.VELOCIDAD_JUGADOR;
        puntajeJugador=0;
        posXoriginal = x;
        posYoriginal = y;
        invencible=false;
    }
    public void mover(){
        //TODO ver como manejamos la herencia pq es de entidad dinamica
    }
    public void mover(int direccionAMover){
        switch (direccionAMover){
            case ConstantesEntidades.QUIETO:
                habilitadoASubirEscalera=false;
                direccion=direccionAMover;
                break;
            case ConstantesEntidades.DERECHA:
                habilitadoASubirEscalera=false;
                dirAnterior=ConstantesEntidades.DERECHA;
                direccion=direccionAMover;
                if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                posX+=velocidad;
                getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                habilitadoASubirEscalera=false;
                dirAnterior=ConstantesEntidades.IZQUIERDA;
                direccion=direccionAMover;
                if(posX-velocidad>0){
                posX-=velocidad;
                getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.ARRIBA:
                if(tocandoSuelo==ConstantesEntidades.SUELO && !saltando){
                    this.iniciarSalto();
                    getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                }
                break;
            case ConstantesEntidades.ABAJO:
                posY+=64;
                getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
        }   
    }
    public DisparoJugador disparar(){
        Sonido.getInstancia().reproducir("disparo.wav");
        int aumento=0;
        if(direccion == ConstantesEntidades.DERECHA){
            aumento=20;
        }
        else if(direccion == ConstantesEntidades.IZQUIERDA){
            aumento=-20;
        }
        DisparoJugador disparo=miJuego.getFabricaEntidades().getDisparoJugador(posX + aumento, posY-7);
        disparo.setDireccion(this.direccion, this.dirAnterior);
        disparo.setPotenciaAumentada(disparoAumentado);
        return disparo;
    }
    public void eliminar(){
        nivel.eliminarJugador(this);
    }
    public void actualizarPuntaje(int puntaje){
        puntajeJugador+=puntaje;
        miJuego.actualizarPuntaje(puntajeJugador);
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
            case ConstantesEntidades.ARRIBA:
                if(direccionAnterior==ConstantesEntidades.IZQUIERDA){
                    claveADevolver="saltandoIzquierda";
                }
                else{
                    claveADevolver="saltandoDerecha";
                }
                    break;
        }
        return claveADevolver;
    }
    public int getVidas(){
        return vidas;
    }
    public Juego getJuego(){
        return miJuego;
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
        if (!invencible){
            Sonido.getInstancia().reproducir("muerte.wav");
            vidas--;
            if (vidas > 0)
                miJuego.actualizarVidas();
            posX = posXoriginal;
            posY = posYoriginal;
            this.getObserver().actualizar(direccion, getTocandoSuelo());
            this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
            miJuego.setReinicio(true);
            if(vidas==0)
                miJuego.reiniciarNivel();
        }    
    }
    public void setVidas(){
        vidas=vidaDeInicio;
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
    public void efectoBotellaRoja(BotellaRoja b){//TODO desp de disparo
        this.disparoAumentado=true; 
        b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
            this.disparoAumentado=false;
            b.getTiempoDeEfecto().resetear();
        });
        b.getTiempoDeEfecto().iniciar();
    }
    public boolean getDisparoAumentado(){
        return disparoAumentado;
    }
    public void efectoBotellaVerde(BotellaVerde b){
        //efecto de botella verde oooooo
        miJuego.getNivelActual().efectoBotellaVerde(true);
        b.getTiempoDeEfecto().setAccionAlFinalizar(() -> {
            miJuego.getNivelActual().efectoBotellaVerde(false);
            b.getTiempoDeEfecto().resetear();
        });
        b.getTiempoDeEfecto().iniciar(); 
    }
    //Metodos de la interfaz Visitador
    public void visitar(Enemigo e){
        if(e.getCongelado())
            e.convertirEnBola();
        else
            this.perderVida();
    }
    public void visitar(BotellaAzul b){
        actualizarPuntaje(b.getPuntaje());
        Sonido.getInstancia().reproducir("agarrarObjeto.wav");
        miJuego.eliminarPowerUp(b);
        efectoBotellaAzul(b);
    }
    public void visitar(BotellaRoja b){
        actualizarPuntaje(b.getPuntaje());
        Sonido.getInstancia().reproducir("agarrarObjeto.wav");
        miJuego.eliminarPowerUp(b);
        efectoBotellaRoja(b);
    }
    public void visitar(BotellaVerde b){
        actualizarPuntaje(b.getPuntaje());
        Sonido.getInstancia().reproducir("agarrarObjeto.wav");
        miJuego.eliminarPowerUp(b);
        efectoBotellaVerde(b);
    }
    public void visitar(Fruta f){
        actualizarPuntaje(f.getPuntaje());
        Sonido.getInstancia().reproducir("agarrarObjeto.wav");
        miJuego.eliminarPowerUp(f);
    }
    public void visitar(VidaExtra v){
        if(vidas < vidaDeInicio){
            vidas++;
            miJuego.efectoVidaExtra();
        }
        actualizarPuntaje(v.getPuntaje());
        Sonido.getInstancia().reproducir("agarrarObjeto.wav");    
        miJuego.eliminarPowerUp(v);
    }
    public void visitar(PlataformaMovil e) {
        super.visitar(e);
        if(!e.getUsada()){
           actualizarPuntaje(e.getPuntaje());
           e.setUsada();
        }
    }
    public void visitar(PlataformaQuebradiza e) {
        if(this.getHitbox().getBottomHitBox().intersects(e.getHitbox().getRectangle())){
           this.hitbox.actualizarHitbox(posX, posY, ancho, alto);
           this.setPosY(e.getPosY() - this.getHitbox().getAlto());
        }
        actualizarPuntaje(e.getPuntaje());
        e.getTiempoAntesDeRomperse().iniciar();
    }
    public void visitar(Escalera e){
        habilitadoASubirEscalera=true;
        getHitbox().actualizarHitbox(posX, posY, ancho, alto);
        observer.actualizar(direccion, tocandoSuelo);
    }
    public boolean estaHabilitadoASubirEscalera(){
        return habilitadoASubirEscalera;
    }
    public void visitar(SueloResbaladizo e){
        velocidad=1;
        
    }
    public void visitar(TrampaPinchos t){
        perderVida();    
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
