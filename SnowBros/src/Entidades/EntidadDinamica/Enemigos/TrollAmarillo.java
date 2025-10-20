package SnowBros.src.Entidades.EntidadDinamica.Enemigos;

import SnowBros.src.Grafica.Sprite;

public class TrollAmarillo extends Enemigo{
    protected static final int puntajeCongelado = 300;
    protected static final int puntajeEmpujado = 500;

    public TrollAmarillo(int x, int y, Sprite spritesTrollAmarillo){
        super(x,y,spritesTrollAmarillo);
    }
    public void saltar(){
        //TODO implementar
    }
}