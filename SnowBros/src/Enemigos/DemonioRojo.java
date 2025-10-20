package SnowBros.src.Enemigos;

import SnowBros.src.Grafica.Sprite;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
    }
}
