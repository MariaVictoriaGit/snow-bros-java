package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
        salud=ConstantesDificultad.SALUD_RANA_DE_FUEGO;
        velocidad=ConstantesDificultad.VELOCIDAD_RANA_DE_FUEGO;
    }
    public void mover(){
        // TODO Auto-generated method stub
        
    }
    public BolaDeFuego lanzarFuego(){
        return null;
        //TODO implementar, return predefinido por el compilador
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void agregarPowerUp(Boolean b){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
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