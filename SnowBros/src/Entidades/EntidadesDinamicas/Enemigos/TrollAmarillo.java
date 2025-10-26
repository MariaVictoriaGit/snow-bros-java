package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class TrollAmarillo extends Enemigo{
    protected static final int puntajeCongelado = 300;
    protected static final int puntajeEmpujado = 500;

    public TrollAmarillo(int x, int y, Sprite spritesTrollAmarillo){
        super(x,y,spritesTrollAmarillo);
    }
    public void saltar(){
        //TODO implementar
    }
    public int getPuntaje(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPuntaje'");
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void patronMovimiento(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patronMovimiento'");
    }
    public void agregarPowerUp(Boolean b){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
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
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public int getPuntajeEmpujado(){
        return puntajeEmpujado;
    }
    public int getPuntajeCongelado(){
        return puntajeCongelado;
    }
}