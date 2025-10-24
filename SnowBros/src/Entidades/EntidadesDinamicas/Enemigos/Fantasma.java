package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Fantasma extends Enemigo{
    
    public Fantasma(int x, int y, Sprite sprite){
        super(x, y, sprite);
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
        return 0;//indestructible
    }
    public int getPuntajeEmpujado() {
        return 0;//indestructible
    }
}