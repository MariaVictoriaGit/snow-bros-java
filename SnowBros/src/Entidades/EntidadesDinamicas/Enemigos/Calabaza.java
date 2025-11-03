package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitador;


public class Calabaza extends Enemigo{
    protected Temporizador tiempoAntesAparicion;
    private long tiempoEstado = 0;
    private int estado = EstadosDePatron.MOVERIZQUIERDA;

    public Calabaza(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_CALABAZA;
        tiempoAntesAparicion=new Temporizador(ConstantesTiempos.TIEMPO_CALABAZA);
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
                if (ahora - tiempoEstado > 1000) { 
                    if (dirAnterior == ConstantesEntidades.DERECHA)
                        estado = EstadosDePatron.MOVERIZQUIERDA;
                    else
                        estado = EstadosDePatron.MOVERDERECHA;
                }
            break;
        }
    }
    public String generarClaveEstado(){
        String clave=null;
        if(direccion==ConstantesEntidades.IZQUIERDA){
            clave="idleIzquierda";
        }
        else{
            clave="idleDerecha";
        }
        return clave;
    }
    public void invocarFantasma(){
        //TODO implementar
    }
    public void convertirEnBola(){
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public int getPuntajeCongelado() {//redefinirlo pq son indestructibles
        return 0;
    }
    public int getPuntajeEmpujado() {//redefinirlo pq son indestructibles
        return 0;
    }
    public void recibirDaño(){
        //es indestructible, no tiene salud
        throw new UnsupportedOperationException("Es indestructible");
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}