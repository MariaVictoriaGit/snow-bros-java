package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;
import java.util.HashMap;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
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
    switch (direccionAMover) {
        case ConstantesEntidades.QUIETO:
            direccion = direccionAMover;
            break;
        case ConstantesEntidades.DERECHA:
            direccion = direccionAMover;
            if(posX+velocidad<ConstantesVistas.PANEL_ANCHO){
               posX += velocidad;
            }
            break;
        case ConstantesEntidades.IZQUIERDA:
            direccion=direccionAMover;
            if (posX-velocidad>0){
                posX-=velocidad;
            }
            break;
        }
    }
    public void actualizarSalto(){
        //TODO implementar
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
    public String generarClaveEstado(int direccion){
        String claveADevolver=null;
        if (!estadoAereo)
            switch(direccion){
                case ConstantesEntidades.IZQUIERDA:
                    claveADevolver="moverIzquierda";
                    break;
                case ConstantesEntidades.QUIETO:
                    if (dirAnterior == ConstantesEntidades.IZQUIERDA){
                        claveADevolver="idleIzquierda";
                    }
                    else if (dirAnterior == ConstantesEntidades.DERECHA){
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
        dirAnterior=this.direccion;
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
