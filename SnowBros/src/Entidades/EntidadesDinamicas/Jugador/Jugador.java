package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.ObserverJugador;
import SnowBros.src.Grafica.Sprite;
//import SnowBros.src.Juego.HitBox;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Visitor.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;
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
        dirAnterior=direccion;
        switch (direccionAMover){
            case ConstantesEntidades.QUIETO:
                direccion=direccionAMover;
                break;
            case ConstantesEntidades.DERECHA:
                direccion=direccionAMover;
                if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                posX+=velocidad;
                getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.IZQUIERDA:
                direccion=direccionAMover;
                if(posX-velocidad>0){
                posX-=velocidad;
                getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                break;
            case ConstantesEntidades.ARRIBA_IZQUIERDA:
                direccion=direccionAMover;
                if(tocandoSuelo==ConstantesEntidades.SUELO){
                    this.iniciarSalto();
                    tocandoSuelo=ConstantesEntidades.AIRE;
                }
                break;
            case ConstantesEntidades.ARRIBA_DERECHA:
                direccion=direccionAMover;
                if(tocandoSuelo==ConstantesEntidades.SUELO){
                    this.iniciarSalto();
                    tocandoSuelo=ConstantesEntidades.AIRE;
                }
                break;
        }   
    }

    public DisparoJugador disparar(){
        DisparoJugador disparo=miJuego.getFabricaEntidades().getDisparoJugador(posX, posY);
        disparo.setDireccion(this.direccion, this.dirAnterior);
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
        if (vidas > 0)
            miJuego.actualizarVidas();
        System.out.print(vidas);
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
    public void efectoFruta(Fruta f){
 
    }
    //Metodos de la interfaz Visitador
    public void visitar(Enemigo e){
        if (!invencible){
        perderVida();
        posX = posXoriginal;
        posY = posYoriginal;
        this.getObserver().actualizar(direccion, getTocandoSuelo());
        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
        miJuego.setReinicio(true);
        if(vidas<=0)
            miJuego.reiniciarNivel();
        //TODO reiniciar al personaje en la posicion original
        }
    }
    public void visitar(BotellaAzul b){
        actualizarPuntaje(b.getPuntaje());
        miJuego.eliminarPowerUp(b);
        efectoBotellaAzul(b);
    }
    public void visitar(BotellaRoja b){
        actualizarPuntaje(b.getPuntaje());
        miJuego.eliminarPowerUp(b);
        efectoBotellaRoja(b);
    }
    public void visitar(BotellaVerde b){
        actualizarPuntaje(b.getPuntaje());
        miJuego.eliminarPowerUp(b);
        efectoBotellaVerde(b);
    }
    public void visitar(Fruta f){
        actualizarPuntaje(f.getPuntaje());
        miJuego.eliminarPowerUp(f);
        efectoFruta(f);
    }
    public void visitar(VidaExtra v){
        if(vidas < vidaDeInicio){
            vidas++;
            System.out.print(vidas);
            miJuego.efectoVidaExtra();
        }
        actualizarPuntaje(v.getPuntaje());        
        miJuego.eliminarPowerUp(v);
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
