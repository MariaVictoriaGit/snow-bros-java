package SnowBros.src.Entidades.EntidadDinamica.Enemigos;

import SnowBros.src.Grafica.Sprite;

public class RanaDeFuego extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public RanaDeFuego(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public BolaDeFuego lanzarFuego(){
        return null;
        //TODO implementar, return predefinido por el compilador
    }
}