package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEnemigos;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
        salud=ConstantesEnemigos.VIDA_RANA_DE_FUEGO;
    }
    public BolaDeFuego lanzarFuego(){
        return null;
        //TODO implementar, return predefinido por el compilador
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