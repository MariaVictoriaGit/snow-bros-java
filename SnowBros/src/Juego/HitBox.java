package SnowBros.src.Juego;
import java.awt.Rectangle;

public class HitBox {
    private Rectangle hitbox;
    private int ancho;
    private int alto;

    public HitBox(int ancho, int altura){
        this.ancho = ancho;
        this.alto = altura;
        hitbox = new Rectangle(ancho/2, altura/2, ancho, altura);
    }
    public void actualizarHitbox(int x, int y, int ancho, int alto){
        hitbox = new Rectangle(x, y, ancho, alto);
    }
    
    public Rectangle getRectangle(){
        return hitbox;
    }
    public Rectangle getTopHitBox(){
        return hitbox;
        //TODO implementar
    }
    public Rectangle getBottomHitBox(){
        return hitbox;
        //TODO implementar
    }
    public Rectangle getLeftHitBox(){
        return hitbox;
        //TODO implementar
    }
    public Rectangle getRightHitBox(){
        return hitbox;
        //TODO implementar
    }
}
