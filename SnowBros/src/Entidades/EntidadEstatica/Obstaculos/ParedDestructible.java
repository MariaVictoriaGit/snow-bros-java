package SnowBros.src.Entidades.EntidadEstatica.Obstaculos;

import SnowBros.src.Grafica.Sprite;

public class ParedDestructible extends Obstaculo{
    public static final int puntaje = 150;
    
    public ParedDestructible(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public int destruirse(){
        eliminar();
        return puntaje;
        //TODO revisar 
    }
}