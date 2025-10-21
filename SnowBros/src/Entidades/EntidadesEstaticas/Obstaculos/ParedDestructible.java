package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;

import SnowBros.src.Grafica.Sprite;

public class ParedDestructible extends Obstaculo{
    public static final int puntaje = 150;
    
    public ParedDestructible(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public void destruirse(){
        //TODO implementar
    }
}