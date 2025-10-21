package SnowBros.src.Entidades.EntidadEstatica.Obstaculos;

import SnowBros.src.Entidades.EntidadEstatica.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;

public abstract class Obstaculo extends EntidadEstatica{
    public Obstaculo(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
}
