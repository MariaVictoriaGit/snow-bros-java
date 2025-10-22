package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Grafica.Sprite;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
    }
    public int getPuntaje(){//desp definir metodos para empujado y congelado   
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPuntaje'");
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void patronMovimiento(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patronMovimiento'");
    }
    public void agregarPowerUp(Boolean b){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
    }
}
