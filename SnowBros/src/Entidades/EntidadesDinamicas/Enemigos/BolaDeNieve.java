package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public boolean getEstado(){
        return estaActiva;
    }
}
