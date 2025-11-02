package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class BotellaAzul extends PowerUp{
    public BotellaAzul(int x, int y, Sprite sprite){
        super(x,y,sprite);
        hitbox.actualizarHitbox(x,y+12,ancho*2,alto*2);
    }

    public void aceptar(Visitador v){
        v.visitar(this);
        eliminar();
    }
}
