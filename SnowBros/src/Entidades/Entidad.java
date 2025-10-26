package SnowBros.src.Entidades;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.HitBox;

public abstract class Entidad {
    protected Sprite sprite;
    protected int posX;
    protected int posY;
    protected int alto,ancho;
    protected HitBox hitbox;
    public Entidad(int posX, int posY, Sprite mISprite){
        this.posX=posX;
        this.posY=posY;
        sprite=mISprite;
        alto=32;  //valores momentaneos
        ancho=32;
        hitbox=new HitBox(posX,posY,ancho,alto);
       // alto=sprite.getAltura();

    }
    public void setPosX(int x){
        this.posX = x;
    }
    public void setPosY(int y){
        this.posY = y;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public HitBox getHitbox(){
        return hitbox;
    }
    public Sprite getSprite(){
        return sprite;
    }
    public abstract String generarClaveEstado();
    public void eliminar(){
        //TODO implementar
    }
}
