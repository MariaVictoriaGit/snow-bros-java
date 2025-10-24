package SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class ParedDestructible extends Obstaculo{

    public ParedDestructible(int x, int y, Sprite sprite ){
        super(x,y,sprite);
    }
    public void destruirse(){
        //TODO implementar
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}