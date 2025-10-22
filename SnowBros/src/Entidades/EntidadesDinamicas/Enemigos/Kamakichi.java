package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Grafica.Sprite;

public class Kamakichi extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;

    public Kamakichi(int x, int y, Sprite sprite){
        super(x, y, sprite);  
    }
    public void dispararBomba(){
        //TODO implementar
    }
    @Override
    public int getPuntaje() {//TODO mismo moghera   
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPuntaje'");
    }
}