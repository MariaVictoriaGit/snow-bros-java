package SnowBros.src.Juego;

public abstract class Entidad {
    protected Sprite sprite;
    protected int posX;
    protected int posY;
    protected HitBox hitbox;

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
}
