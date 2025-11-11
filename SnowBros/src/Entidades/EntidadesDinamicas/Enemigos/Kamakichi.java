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
                if (dirAnterior == ConstantesEntidades.ARRIBA){
                    estado = EstadosDePatron.MOVERABAJO;
                    activarDisparo=true;
                    dispararBomba();
                }
                else{
                    estado = EstadosDePatron.MOVERARRIBA;
                    activarDisparo=true;
                    dispararBomba();
                }
            }
        }
    }
    public void dispararBomba(){
        boolean patronDisparo=true;
        if(activarDisparo){
            agregarBomba(posX+30, posY, ConstantesEntidades.DERECHA);
            agregarBomba(posX-30, posY, ConstantesEntidades.IZQUIERDA);
            if(patronDisparo){
                agregarBomba(posX+20, posY-15, ConstantesEntidades.ARRIBA_DERECHA);
                agregarBomba(posX-20, posY-15, ConstantesEntidades.ARRIBA_IZQUIERDA);
            }
            else{
                agregarBomba(posX+20, posY-25, ConstantesEntidades.ARRIBA_IZQUIERDA);
                agregarBomba(posX-20, posY-25, ConstantesEntidades.ARRIBA_DERECHA);
            }
            activarDisparo=false;

        }
    }
    public void agregarBomba(int posXInicial,int posYInicial,int direccionDisparo){
        BolaDeFuego bola;
        bola=nivel.getJuego().getFabricaEntidades().getBolaDeFuego(posXInicial, this.posY);
        bola.setDireccion(direccionDisparo);
        bola.setNivel(nivel);
        nivel.agregarBolaDeFuego(bola);
        nivel.getJuego().registrarObserverEntidad(bola);
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
    public int getSalud(){
        salud=ConstantesDificultad.SALUD_KAMAKICHI;
        return salud;
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