package SnowBros.src.Fabricas;
import SnowBros.src.Entidades.EntidadDinamica.Enemigos.*;
import SnowBros.src.Entidades.EntidadDinamica.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadEstatica.Obstaculos.*;
import SnowBros.src.Entidades.EntidadEstatica.Plataformas.*;
import SnowBros.src.Entidades.EntidadEstatica.PowerUps.*;
import SnowBros.src.Grafica.Sprite;


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
