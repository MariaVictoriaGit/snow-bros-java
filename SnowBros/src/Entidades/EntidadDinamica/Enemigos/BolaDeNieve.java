package SnowBros.src.Entidades.EntidadDinamica.Enemigos;

import SnowBros.src.Entidades.EntidadDinamica.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public boolean getEstado(){
        return estaActiva;
        //TODO implementar, return  predefinido por el compilador
    }
}
