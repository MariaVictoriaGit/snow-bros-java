package SnowBros.src.Enemigos;

import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.EntidadDinamica;
import SnowBros.src.Juego.Timer;

public class BolaDeFuego extends EntidadDinamica{
    public BolaDeFuego(int posX, int posY,Sprite miSprite){
        super(posX, posY, miSprite);
    }
    public Timer duracion(){
        return null;
        //TODO implementar, return predefinido por el compilador
    }
}
