package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Fantasma extends Enemigo{
    
    public Fantasma(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_FANTASMA;
        direccion=0;
    }
    public void mover(){
        //TODO mover
    }
    public void patronDeMovimiento(){
        
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void recibirDaño(){
        //es indestructible
        throw new UnsupportedOperationException("Es indestructible");
    }
    public int getPuntajeCongelado() {
        return 0;//indestructible
    }
    public int getPuntajeEmpujado() {
        return 0;//indestructible
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}