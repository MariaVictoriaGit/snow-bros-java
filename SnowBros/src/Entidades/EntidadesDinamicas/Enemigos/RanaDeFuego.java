package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected boolean lanzandoBolas;
    protected int direccionDisparo;
    private long tiempoEstado = 0;
    private int estado = EstadosDePatron.MOVERDERECHA;

    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
        lanzandoBolas=false;
        salud=ConstantesDificultad.SALUD_RANA_DE_FUEGO;
        velocidad=ConstantesDificultad.VELOCIDAD_RANA_DE_FUEGO;
    }
      public void mover(){
        switch (direccion) {
            case ConstantesEntidades.QUIETO:
                lanzarFuego(posX); //como accedemos a la pos del jugador que pide el metodo?
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
    public BolaDeFuego lanzarFuego(int posXJugador){
        BolaDeFuego bola=null;

        if(direccion==ConstantesEntidades.QUIETO){
            lanzandoBolas=true;
            int posicionXBola;
            if(posXJugador>this.getPosX())
                direccionDisparo=ConstantesEntidades.DERECHA;
            else
                direccionDisparo=ConstantesEntidades.IZQUIERDA;
            direccion=direccionDisparo;
            if (direccionDisparo==ConstantesEntidades.DERECHA) 
                posicionXBola= this.getPosX()+20;
            else
                posicionXBola=this.getPosX()-20;
            bola= new BolaDeFuego(posicionXBola,this.getPosY(), sprite);
            bola.setDireccion(direccionDisparo);
        }

        return bola;
    }
    public int getPuntaje(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPuntaje'");
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void agregarPowerUp(Boolean b){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
    }
    public String generarClaveEstado(){
        String clave=null;
        if(direccion==ConstantesEntidades.QUIETO){
            if (lanzandoBolas) {
                switch (dirAnterior) {
                    case ConstantesEntidades.IZQUIERDA:
                        clave="disparoIzquierda";
                        break;
                    case ConstantesEntidades.DERECHA:
                        clave="disparoDerecha";
                        break;
                }
            }else{
                switch (dirAnterior) {
                    case ConstantesEntidades.IZQUIERDA:
                        clave="idleIzquierda";
                        break;
                    case ConstantesEntidades.DERECHA:
                        clave="idleDerecha";
                        break;
                }
            }
        } else{
            if(direccion==ConstantesEntidades.IZQUIERDA)
                clave="moverIzquierda";
            else
                clave="moverDerecha";
        }
        return clave;
    }
  
    public void aceptar(Visitador visitador){
        visitador.visitar(this);
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
}
