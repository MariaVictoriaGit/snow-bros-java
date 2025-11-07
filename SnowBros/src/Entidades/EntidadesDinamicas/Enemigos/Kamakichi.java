package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Kamakichi extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    protected boolean activarDisparo;

    public Kamakichi(int x, int y, Sprite sprite){
        super(x, y, sprite);  
        salud=ConstantesDificultad.SALUD_KAMAKICHI;
        velocidad=ConstantesDificultad.VELOCIDAD_KAMAKICHI;
        estado=EstadosDePatron.MOVERARRIBA;
    }
    public void mover(){
        switch (direccion) {
            case ConstantesEntidades.ARRIBA:
                dirAnterior=ConstantesEntidades.ARRIBA;
                posY-=1;
                this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
        
            case ConstantesEntidades.ABAJO:
                dirAnterior=ConstantesEntidades.ABAJO;
                posY+=1;
                this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
                break;
            case ConstantesEntidades.QUIETO:
                dispararBomba();
                    if (dirAnterior == ConstantesEntidades.DERECHA){
                        direccion = ConstantesEntidades.IZQUIERDA;
                    }
                    else{
                        direccion = ConstantesEntidades.DERECHA;
                    }
                break;
        }

    }
    public void verificarSuelo(){
        return;
    }
     public void patronDeMovimiento(){
        long ahora = System.currentTimeMillis();

        if(estado==EstadosDePatron.MOVERARRIBA){
            direccion=ConstantesEntidades.ARRIBA;
            mover();
            if(posY<100){
                estado=EstadosDePatron.ESPERAR;
                tiempoEstado=ahora;
            }
        }
        else if(estado==EstadosDePatron.MOVERABAJO){
            direccion=ConstantesEntidades.ABAJO;
            mover();
            if(posY>ConstantesVistas.PANEL_ALTO-100){
                estado=EstadosDePatron.ESPERAR;
                tiempoEstado=ahora;
            }
        }
        else{
            direccion=ConstantesEntidades.QUIETO;
            mover();
            if (ahora - tiempoEstado > 2000) { // espera 4 seg
                mover();
                if (dirAnterior == ConstantesEntidades.ARRIBA)
                    estado = EstadosDePatron.MOVERABAJO;
                else
                    estado = EstadosDePatron.MOVERARRIBA;
                }
        }
    }
    public void dispararBomba(){
        activarDisparo=true;
    }
    public void convertirEnBola(){
        congelado=true;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public String generarClaveEstado(){
        String clave="normal"; //estado por defecto
        if(congelado)
            clave="congelado";
        else
            if(activarDisparo)
                clave="disparo";
        return clave;
    }
}