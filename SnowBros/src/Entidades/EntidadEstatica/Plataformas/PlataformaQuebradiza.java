package SnowBros.src.Entidades.EntidadEstatica.Plataformas;

import SnowBros.src.Grafica.Sprite;

public class PlataformaQuebradiza extends Plataforma {
    public static final int puntaje = 300;
    public boolean horizontal;

    public PlataformaQuebradiza(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
}
