package SnowBros.src.Fabricas;

import java.util.HashMap;

import SnowBros.src.Grafica.Sprite;

public abstract class FabricaSprites {
    protected String rutaACarpeta;
    public FabricaSprites(String ruta) {
        this.rutaACarpeta = ruta;
    }
    public Sprite get_silueta() {
        		HashMap<String, String> mapeo = new HashMap<>();
                		mapeo.put("normal", rutaACarpeta+ "/FondoNivel1.png");
                        		return new Sprite(mapeo);	
                                	
    }
    public Sprite getSpriteJugador(){
        HashMap<String, String> mapeo = new HashMap<String,String>();
        mapeo.put("idleIzquierda", rutaACarpeta+ "/Jugador/izquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta+ "/Jugador/derecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta+ "/Jugador/avanzarIzquierda.png");
        mapeo.put("moverDerecha", rutaACarpeta+ "/Jugador/avanzarDerecha.png");
        mapeo.put("saltandoDerecha", rutaACarpeta+ "/Jugador/JugadorSaltandoDerecha.png");
        mapeo.put("saltandoIzquierda", rutaACarpeta+ "/Jugador/JugadorSaltandoIzquierda.png");
        return new Sprite(mapeo);
    	
    }
    public Sprite getSpritePlataforma(){  //TODO lo hacemos abstracto o lo dejamos implementado?
         HashMap<String, String> mapeo = new HashMap<>();
         mapeo.put("estatico", rutaACarpeta+ "/Plataforma/estatico.png");
         return new Sprite(mapeo);
    }
    abstract public Sprite getSpriteDemonioRojo();
    abstract public Sprite getSpriteTrollAmarillo();
    abstract public Sprite getSpriteRanaDeFuego();
    abstract public Sprite getSpriteCalabaza();
    abstract public Sprite getSpriteFantasma();
    abstract public Sprite getSpriteMoghera();
    abstract public Sprite getSpriteKamakichi();
    abstract public Sprite getSpriteTrampaPinchos();
    abstract public Sprite getSpriteSueloResbaladizo();
    abstract public Sprite getSpriteParedDestuctible();
    abstract public Sprite getSpritePlataformaMovil();
    abstract public Sprite getSpritePlataformaQuebradiza();
    abstract public Sprite getSpritePared();
    abstract public Sprite getSpriteEscalera();
    abstract public Sprite getSpriteBotellaAzul();
    abstract public Sprite getSpriteBotellaRoja();
    abstract public Sprite getSpriteBotellaVerde();
    abstract public Sprite getSpriteFruta();
    abstract public Sprite getSpriteVidaExtra();
    abstract public Sprite getSpriteBolaNieve();
    abstract public Sprite getSpriteBolaFuego();
    abstract public Sprite getSpriteDisparoJugador(); 
}