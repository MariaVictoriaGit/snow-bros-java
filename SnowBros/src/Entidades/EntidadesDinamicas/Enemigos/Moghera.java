package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;

    public Moghera(int x, int y, Sprite sprite){
        super(x, y, sprite);       
        salud=ConstantesDificultad.SALUD_MOGHERA;
        velocidad=ConstantesDificultad.VELOCIDAD_MOGHERA;
    }
    public void mover(){
        //TODO implementar mover
    }
    public void lanzarFuego(){
        //TODO implementar
    }
    public void esDerrotado(){
        //TODO implementar
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
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