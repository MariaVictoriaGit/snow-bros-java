package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class VidaExtra extends PowerUp {
    public VidaExtra(int x, int y, Sprite sprite){
        super(x, y,sprite);
        hitbox.actualizarHitbox(x-16,y,ancho*2,alto*2);
    }
    public void aceptar(Visitador v){
        v.visitar(this);
        eliminar();
    }
}
