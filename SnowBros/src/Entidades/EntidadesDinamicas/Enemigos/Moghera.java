package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;

    public Moghera(int x, int y, Sprite sprite){
        super(x, y, sprite);       
    }
    public void lanzarFuego(){
        //TODO implementar
    }
    public void esDerrotado(){
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
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
}