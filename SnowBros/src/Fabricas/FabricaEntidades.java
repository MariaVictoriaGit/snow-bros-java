package SnowBros.src.Fabricas;

import SnowBros.src.Enemigos.Calabaza;
import SnowBros.src.Enemigos.DemonioRojo;
import SnowBros.src.Enemigos.Fantasma;
import SnowBros.src.Enemigos.Kamakichi;
import SnowBros.src.Enemigos.Moghera;
import SnowBros.src.Enemigos.RanaDeFuego;
import SnowBros.src.Enemigos.TrollAmarillo;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Jugador.Jugador;
import SnowBros.src.Obstaculos.Escalera;
import SnowBros.src.Obstaculos.Pared;
import SnowBros.src.Obstaculos.ParedDestructible;
import SnowBros.src.Obstaculos.SueloResbaladizo;
import SnowBros.src.Obstaculos.TrampaPinchos;
import SnowBros.src.Plataformas.Plataforma;
import SnowBros.src.Plataformas.PlataformaMovil;
import SnowBros.src.Plataformas.PlataformaQuebradiza;
import SnowBros.src.PowerUps.BotellaAzul;
import SnowBros.src.PowerUps.BotellaRoja;
import SnowBros.src.PowerUps.BotellaVerde;
import SnowBros.src.PowerUps.Fruta;
import SnowBros.src.PowerUps.VidaExtra;

public class FabricaEntidades {
    private FabricaSprites fabrica;
    
    public FabricaEntidades(FabricaSprites fabricaSprite){
        fabrica=fabricaSprite;
    }
   /* public Silueta getSilueta(){
        Sprite spriteSilueta= fabrica.get_silueta();
        Silueta silueta=new Silueta(spriteSilueta);
    }*/
    public Jugador getJugador(int x, int y){
        Sprite spritesJugador=fabrica.getSpriteJugador();
        Jugador jugador= new Jugador(x,y,spritesJugador);
        return jugador;
        }
    }
    public DemonioRojo getDemonioRojo(int x, int y){
        Sprite spritesDemonioRojo=fabrica.getSpriteDemonioRojo();
        DemonioRojo demonio= new DemonioRojo(x,y,spritesDemonioRojo);
        return demonio;
    }
    public TrollAmarillo getTrollAmarillo(int x, int y){
        Sprite spritesTrollAmarillo=fabrica.getSpriteTrollAmarillo();
        TrollAmarillo troll= new TrollAmarillo(x,y,spritesTrollAmarillo);
        return troll;
    }
    public RanaDeFuego getRanaDeFuego(int x, int y){
        return null;
        //TODO implementar
    }
    public Calabaza getCalabaza(int x, int y){
        return null;
        //TODO implementar
    }
    public Fantasma getFantasma(int x, int y){
        return null;
        //TODO implementar
    }
    public Kamakichi getKamakichi(int x, int y){
        return null;
        //TODO implementar
    }
    public Moghera getMoghera(int x, int y){
        return null;
        //TODO implementar
    }
    public BotellaAzul getBotellaAzul(int x, int y){
        return null;
        //TODO implementar
    }
    public BotellaRoja getBotellaRoja(int x, int y){
        return null;
        //TODO implementar
    }
    public BotellaVerde getBotellaVerde(int x, int y){
        return null;
        //TODO implementar
    }
    public Fruta getFruta(int x, int y){
        return null;
        //TODO implementar
    }
    public VidaExtra getVidaExtra(int x, int y){
        return null;
        //TODO implementar
    }
    public Escalera getEscalera(int x, int y){
        return null;
        //TODO implementar
    }
    public Pared getPared(int x, int y){
        return null;
        //TODO implementar
    }
    public ParedDestructible getParedDestructible(int x, int y){
        return null;
        //TODO implementar
    }
    public SueloResbaladizo getSueloResbaladizo(int x, int y){
        return null;
        //TODO implementar
    }
    public TrampaPinchos getTrampaPinchos(int x, int y){
        return null;
        //TODO implementar
    }
    public Plataforma getPlataforma(int x, int y){
        return null;
        //TODO implementar
    }
    public PlataformaMovil getPlataformaMovil(int x, int y){
        return null;
        //TODO implementar
    }
    public PlataformaQuebradiza getPlataformaQuebradiza(int x, int y){
        return null;
        //TODO implementar
    }
}
