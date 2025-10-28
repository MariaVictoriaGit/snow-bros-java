package SnowBros.src.Entidades.EntidadesEstaticas;

import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitable;

public abstract class EntidadEstatica extends Entidad implements Visitable {

    public EntidadEstatica(int i, int j, Sprite sprite) {
        super(i,j,sprite);
    }
    public String generarClaveEstado(){
        return "normal"
        ;
    }
}
