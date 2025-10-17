public abstract class FabricaSprites {

    private Sprite rutaACarpeta;
    protected FabricaSprites(String ruta) {
        		this.rutaACarpeta = ruta;
                	}
    }

    public Sprite get_silueta() {
        		HashMap<String, String> mapeo = new HashMap<>();
                		mapeo.put("normal", rutaACarpeta+ "/Fondo.png");
                        		return new Sprite(mapeo);	
                                	}
    }
    public Sprite getSpriteJugador(){
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("idleIzquierda", ruta_a_carpeta+ "/Jugador/JugadorIzquierda.png");
        mapeo.put("idleDerecha", ruta_a_carpeta+ "/Jugador/JugadorDerecha.png");
        mapeo.put("moverIzquierda", ruta_a_carpeta+ "/Jugador/JugadorMoverIzquierda.png");
        mapeo.put("moverDerecha", ruta_a_carpeta+ "/Jugador/JugadorMoverDerecha.png");
        mapeo.put("saltandoDerecha", ruta_a_carpeta+ "/Jugador/JugadorSaltandoDerecha.png");
        mapeo.put("saltandoIzquierda", ruta_a_carpeta+ "/Jugador/JugadorSaltandoIzquierda.png");
        return new Sprite(mapeo);
    	}
    }
    abstract public Sprite getSpriteDemonioRojo();
    abstract public Sprite getSpriteTrollAmarillo();
    abstract public Sprite getSpriteRana();
    abstract public Sprite getSpriteCalabaza();
    abstract public Sprite getSpriteFantasma();
    abstract public Sprite getSpriteMoghera();
    abstract public Sprite getSpriteKamakiprite();
    abstract public Sprite getSpritePlataforma();
    abstract public Sprite getSpriteTrampaPinchos();
    abstract public Sprite getSpriteSueloResbaladizo();
    abstract public Sprite getSpriteParedDestuctible();
    abstract public Sprite getSpritePlataformaDestructible();
    abstract public Sprite getSpriteEscaleras();
    abstract public Sprite getSpriteBotellaAzul();
    abstract public Sprite getSpriteFruta();
    abstract public Sprite getSpriteBotellaRoja();
    abstract public Sprite getSpriteBotellaVerde();
    abstract public Sprite getSpriteVidaExtra();
    abstract public Sprite getSpriteBolaNieve();
    abstract public Sprite getSpriteBolaFuego();
    abstract public Sprite getSpriteDisparoJugador(); 



}