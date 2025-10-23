package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import java.util.HashMap;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Juego;

public class Jugador extends EntidadDinamica {
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected boolean tocandoSuelo;
    protected int puntajeJugador;
    protected int direccion;
    protected int velocidad;
    protected int vidas;
    protected String nombre;
    protected int dirAnterior;
    protected boolean disparoAumentado;

    public Jugador(int x,int y, Sprite mISprite){
        super(x,y,mISprite);
        vidas=vidaDeInicio;
        tocandoSuelo=true;
        velocidad=8;
    }
    public void mover(int direccionAMover){
        dirAnterior=direccion;
        if(direccionAMover==ConstantesEntidades.QUIETO){
            this.direccion=direccionAMover;
        }
        else if(direccionAMover==ConstantesEntidades.DERECHA){
            this.direccion=direccionAMover;
            posX=posX+this.velocidad;
        }
        else if(direccionAMover==ConstantesEntidades.IZQUIERDA){
            this.direccion=direccionAMover; 
            posX=posX-this.velocidad;
        }
        else if(direccionAMover==ConstantesEntidades.ARRIBA){
            this.iniciarSalto();
            
        }
    }
    public void iniciarSalto(){
        tocandoSuelo=false;
        if(!tocandoSuelo){
            int gravedad=-1;
            int rapidezEnY=8;
            rapidezEnY=gravedad+rapidezEnY;
            posY=rapidezEnY+posY;
            observer.actualizar(direccion, getEstadoAereo());
        }
    }
    public boolean tocoAire(){
        return !tocandoSuelo;
    }
    public void disparar(){
        Sprite miDisparo=null;  // ponermos getfabrica()
        //TODO corregir
        DisparoJugador disparo=new DisparoJugador(posX,posY,miDisparo);
        miJuego.getNivelActual().agregarBolaDeNieve(disparo);

    } 
    public void actualizarPuntaje(int puntaje){
        puntajeJugador+=puntaje;
        //TODO implementar      es return definido por el compilador
    }
    public String generarClaveEstado(int direccionAnterior){
        String claveADevolver=null;
        if (!estadoAereo)
            switch(direccion){
                case ConstantesEntidades.IZQUIERDA:
                    claveADevolver="moverIzquierda";
                    break;
                case ConstantesEntidades.QUIETO:
                    if (direccionAnterior == ConstantesEntidades.IZQUIERDA){
                        claveADevolver="idleIzquierda";
                    }
                    else {
                        claveADevolver="idleDerecha";
                    }
                    break;
                case ConstantesEntidades.DERECHA:
                    claveADevolver= "moverDerecha";
                    break;
            }
        
        //TODO faltan estas dos claves "saltandoDerecha" ---- JugadorSaltandoDerecha.png
        //                             "saltandoIzquierda" --- JugadorSaltandoIzquierda.png
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
    public void setEnElAire(Boolean b){
        // TODO preguntar borrar
    }
    public void setTocandoSuelo(Boolean b){
        tocandoSuelo=b;
    }
    public boolean getTocandoSuelo(){
        return tocandoSuelo;
    }   
    public void setDireccion(int direccionActual){
        if(direccionActual!=0){
            dirAnterior=this.direccion;
        }
        this.direccion=direccionActual;
    }
   /* public void setTiempo(int t){ //tendriamos que hacerlo con el timer
        int tiempo;
        if(t>=0){
            tiempo=t;
        }
    }*/ //ver si es necesario el set aca y no en juego
    public void perderVida(){
        vidas--;
    }
    public void setVidas(){
        vidas=vidaDeInicio;
 }
    public void efectoBotellaAzul(){
        this.velocidad+=5; // suponiendo que ese es el incremento final, cambiar dsp
    }
    public void efectoBotellaRoja(){
        this.disparoAumentado=true;
        //TODO implementar
    }
    public void efectoBotellaVerde(){
        //TODO implementar
    }
}
