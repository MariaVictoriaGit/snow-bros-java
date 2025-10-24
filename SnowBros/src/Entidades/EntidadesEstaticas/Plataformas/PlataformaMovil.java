package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;

import SnowBros.src.Grafica.Sprite;

public class PlataformaMovil extends Plataforma{
    public static final int puntaje=200;
    public boolean horizontal;

    public PlataformaMovil(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public void mover(){
        //TODO implementar
    }
    public int getPuntaje(){
        return puntaje;
    }
}
