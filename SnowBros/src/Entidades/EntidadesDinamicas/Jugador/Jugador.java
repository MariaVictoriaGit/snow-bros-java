package SnowBros.src.Entidades.EntidadesDinamicas.Jugador;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Juego;
import SnowBros.src.Visitor.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Enemigo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Escalera;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.TrampaPinchos;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaAzul;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaRoja;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaVerde;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.Fruta;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.VidaExtra;

public class Jugador extends EntidadDinamica implements Visitador, Visitable{
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
        case ConstantesEntidades.ARRIBA_IZQUIERDA:
            direccion=direccionAMover;
            this.iniciarSalto();
            break;
        case ConstantesEntidades.ARRIBA_DERECHA:
            direccion=direccionAMover;
            this.iniciarSalto();
            break;
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

    //Metodos de la interfaz Visitador
    public void visitar(Enemigo e){
        perderVida();
        //reiniciar al personaje en la posicion original
    }
    public void visitar(Plataforma p){
        //Colision con la parte superior de la plataforma
    }
    public void visitar(BotellaAzul b){
        efectoBotellaAzul();
    }
    public void visitar(BotellaRoja b){
        efectoBotellaRoja();
    }
    public void visitar(BotellaVerde b){
        efectoBotellaVerde();
    }
    public void visitar(Fruta f){
        puntajeJugador+= f.getPuntaje();
    }
    public void visitar(VidaExtra v){
        vidas++;
        puntajeJugador+=v.getPuntaje();
    }
    public void visitar(Pared p){
        //Colision con los costados de la pared
    }
    public void visitar(ParedDestructible p){
        p.destruirse();
        puntajeJugador += p.getPuntaje();
    }
    public void visitar(Escalera e){
        //Ver forma de subir
    }
    public void visitar(SueloResbaladizo e){
        velocidad -= 3;
    }
    public void visitar(TrampaPinchos t){
        perderVida();
        //Reiniciar al personaje a la posicion origianl
    }
    public void visitar(Jugador j){
        //Que hacemos aca?
    }
    //Metodo de la interfaz Visitable
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}
