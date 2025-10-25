package SnowBros.src.Juego;
import java.awt.Rectangle;

import SnowBros.src.Datos.ConstantesEntidades;

public class HitBox {
    private static final int margen=ConstantesEntidades.MARGEN_HIT_BOX;
    private Rectangle hitbox;
    private int ancho;
    private int alto;

    public HitBox(int ancho, int altura){
        this.ancho=ancho;
        this.alto=altura;
        hitbox=new Rectangle(ancho/2, altura/2, ancho, altura);
    }
    public void actualizarHitbox(int x, int y, int ancho, int alto){
        this.alto=alto;
        this.ancho=ancho;
        hitbox.setBounds(x, y, this.ancho, this.alto);
    }
    public Rectangle getRectangle(){
        return hitbox;
    }
    public Rectangle getTopHitBox(){
        return new Rectangle(hitbox.x+margen, hitbox.y, hitbox.width-(margen*2), margen);
    }
    public Rectangle getBottomHitBox(){
        return new Rectangle(hitbox.x+margen, hitbox.y+hitbox.height-margen, hitbox.width-(margen*2), margen);
    }
    public Rectangle getLeftHitBox(){
        return new Rectangle(hitbox.x, hitbox.y+margen, margen, hitbox.height-(margen*2));
    }
    public Rectangle getRightHitBox(){
        return new Rectangle(hitbox.x+hitbox.width-margen, hitbox.y+margen, margen, hitbox.height-(margen*2));
    }
}