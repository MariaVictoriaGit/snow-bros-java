package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected boolean lanzandoBolas;
    protected int direccionDisparo;

    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
        lanzandoBolas=false;
        salud=ConstantesDificultad.SALUD_RANA_DE_FUEGO;
        velocidad=ConstantesDificultad.VELOCIDAD_RANA_DE_FUEGO;
    }
    public void mover(){
        // TODO Auto-generated method stub
        
    }
    public void patronDeMovimiento(){
        
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
            //ver al crear la bola de fuego, estoy pasando el sprite de la rana
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
