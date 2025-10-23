package SnowBros.src.Juego;

import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Silueta extends EntidadEstatica {
    public Silueta(Sprite sprite){
        super(0,0,sprite);
        
    }

    @Override
    public void aceptar(Visitador v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aceptar'");
    }
}
