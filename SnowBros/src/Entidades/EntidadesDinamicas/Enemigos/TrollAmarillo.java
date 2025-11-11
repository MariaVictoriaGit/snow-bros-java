package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class TrollAmarillo extends Enemigo{
    protected static final int puntajeCongelado = 300;
    protected static final int puntajeEmpujado = 500;
    private long tiempoEstado = 0;
    private int estado = EstadosDePatron.MOVERDERECHA;

    public TrollAmarillo(int x, int y, Sprite spritesTrollAmarillo){
        super(x,y,spritesTrollAmarillo);
        salud=ConstantesDificultad.SALUD_TROLL_AMARILLO;
        velocidad=ConstantesDificultad.VELOCIDAD_TROLL_AMARILLO;
    }
    public void mover(){
        if(!detenido)
            switch (direccion) {
                case ConstantesEntidades.QUIETO:
                    if (dirAnterior == ConstantesEntidades.DERECHA){
                        direccion = ConstantesEntidades.IZQUIERDA;
                    }
                    else{
                        direccion = ConstantesEntidades.DERECHA;
                    }
                    break;
                case ConstantesEntidades.IZQUIERDA:
                    dirAnterior=ConstantesEntidades.IZQUIERDA;
                    if(posX-velocidad>0){
                        posX-=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);

                    }
                    break;
                case ConstantesEntidades.DERECHA:
                    dirAnterior=ConstantesEntidades.DERECHA;
                    if(posX+velocidad<ConstantesVistas.PANEL_JUEGO_ANCHO){
                        posX+=1;
                        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                    }
                    break;
            }  
    }
    public void patronDeMovimiento() {
        long ahora = System.currentTimeMillis();
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                direccion = ConstantesEntidades.DERECHA;
                mover();
                if (posX > ConstantesVistas.PANEL_JUEGO_ANCHO - 100) {
                    estado = EstadosDePatron.ESPERAR;
                    tiempoEstado = ahora;
                }
                break;
        case EstadosDePatron.MOVERIZQUIERDA:
            direccion = ConstantesEntidades.IZQUIERDA;
            mover();
            if (posX < 100) {
                estado = EstadosDePatron.ESPERAR;
                tiempoEstado = ahora;
            }
            break;
        case EstadosDePatron.ESPERAR:
                direccion=ConstantesEntidades.QUIETO;
                if (ahora - tiempoEstado > 2000) { // espera 2 segundos
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        estado = EstadosDePatron.MOVERIZQUIERDA;
                    else
                        estado = EstadosDePatron.MOVERDERECHA;
                }
            break;
        }
    }
    public void saltar(){
        //TODO implementar
    }
    public String generarClaveEstado(){
        String estado=null;
        if(direccion==ConstantesEntidades.QUIETO){
            if(dirAnterior==ConstantesEntidades.IZQUIERDA)
                estado="idleIzquierda";
            else
                estado="idleDerecha";
        }else{
            if(direccion==ConstantesEntidades.IZQUIERDA)
                estado="moverIzquierda";
            else
                estado="moverDerecha";
        }
        return estado;
    }
    public int getPuntajeEmpujado(){
        return puntajeEmpujado;
    }
    public int getPuntajeCongelado(){
        return puntajeCongelado;
    }
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_TROLL_AMARILLO;
        return salud;
    }
    public void aceptar(Visitador visitador) {
        visitador.visitar(this);
    }
}