package SnowBros.src.Fabricas;

import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Calabaza;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.DemonioRojo;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Fantasma;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Kamakichi;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.Moghera;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.RanaDeFuego;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.TrollAmarillo;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Escalera;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.Pared;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.SueloResbaladizo;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.TrampaPinchos;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaQuebradiza;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaAzul;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaRoja;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.BotellaVerde;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.Fruta;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.VidaExtra;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Silueta;


public class FabricaEntidades {
    private FabricaSprites fabrica;
    
    public FabricaEntidades(FabricaSprites fabricaSprite){
        fabrica=fabricaSprite;
    }
    public Silueta getSilueta(){
        Sprite spriteSilueta= fabrica.get_silueta();
        Silueta silueta=new Silueta(spriteSilueta);
        return silueta;
    }
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
        Sprite RanaDeFuego= fabrica.getSpriteRanaDeFuego();
        RanaDeFuego rana= new RanaDeFuego(x, y, RanaDeFuego);
        return rana;
    }
    public Calabaza getCalabaza(int x, int y){
        Sprite spriteCalabaza= fabrica.getSpriteCalabaza();
        Calabaza calabaza= new Calabaza(x, y, spriteCalabaza);
        return calabaza;
    }
    public Fantasma getFantasma(int x, int y){
        Sprite spriteFantasma= fabrica.getSpriteFantasma();
        Fantasma fantasma= new Fantasma(x, y, spriteFantasma);
        return fantasma;
    }
    public Kamakichi getKamakichi(int x, int y){
        Sprite spriteKamakichi= fabrica.getSpriteKamakichi();
        Kamakichi kamakichi= new Kamakichi(x, y, spriteKamakichi);
        return kamakichi;
    }
    public Moghera getMoghera(int x, int y){
        Sprite spriteMoghera= fabrica.getSpriteMoghera();
        Moghera moghera=new Moghera(x, y, spriteMoghera);
        return moghera;
    }
    public BotellaAzul getBotellaAzul(int x, int y){
        Sprite botellaAzul= fabrica.getSpriteBotellaAzul();
        BotellaAzul azul= new BotellaAzul(x, y, botellaAzul);
        return azul;
    }
    public BotellaRoja getBotellaRoja(int x, int y){
        Sprite botellaRoja= fabrica.getSpriteBotellaRoja();
        BotellaRoja roja= new BotellaRoja(x, y, botellaRoja);
        return roja;
    }
    public BotellaVerde getBotellaVerde(int x, int y){
        Sprite botellaVerde= fabrica.getSpriteBotellaVerde();
        BotellaVerde verde= new BotellaVerde(x, y, botellaVerde);
        return verde;
    }
    public Fruta getFruta(int x, int y){
        Sprite spriteFruta= fabrica.getSpriteFruta();
        Fruta fruta= new Fruta(x, y, spriteFruta);
        return fruta;
    }
    public VidaExtra getVidaExtra(int x, int y){
        Sprite vidaExtra= fabrica.getSpriteVidaExtra();
        VidaExtra vida= new VidaExtra(x, y, vidaExtra);
        return vida;
    }
    public Escalera getEscalera(int x, int y){
        Sprite spriteEscalera= fabrica.getSpriteEscalera();
        Escalera escalera= new Escalera(x,y,spriteEscalera);
        return escalera;
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
