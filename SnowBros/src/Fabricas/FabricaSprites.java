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
    public Sprite getSpriteDemonioRojo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/DemonioRojo/quieto.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/DemonioRojo/quieto.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminando-der1.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminando-der2.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminando-der3.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminando-izq1.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminando-izq2.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminando-izq3.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/TrollAmarillo/quieto.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/TrollAmarillo/quieto.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/trollcaminandoder.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/trollcaminandoizq.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteRanaDeFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/RanaDeFuego/ranaquietamirandoizq.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/RanaDeFuego/ranaquietamirandoder.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/RanaDeFuego/rabacaminandoder.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/RanaDeFuego/ranacaminandoizq.png");
        mapeo.put("disparoDerecha", rutaACarpeta + "/RanaDeFuego/ranadisparandoder.png");
        mapeo.put("disparoIzquierda", rutaACarpeta + "/RanaDeFuego/ranadisparandoizq.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idle", rutaACarpeta + "/Calabaza/calabaza.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverDerecha", rutaACarpeta + "/Fantasma/fantasmader - copia.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/Fantasma/fantasmaizq - copia.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteMoghera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/Moghera/mogheraIzquierda.png");
        mapeo.put("congeladoIzquierda", rutaACarpeta + "/Moghera/mogheraCongeladoIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteKamakichi(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idle", rutaACarpeta + "/Kamakichi/kamakichiEspera.png");
        mapeo.put("disparo", rutaACarpeta + "/Kamakichi/kamakichiDisparando.png");
        mapeo.put("congelado", rutaACarpeta + "/Kamakichi/kamakichiCongelado.png");
        return new Sprite(mapeo);
    }
    abstract public Sprite getSpriteTrampaPinchos();
    abstract public Sprite getSpriteSueloResbaladizo();
    abstract public Sprite getSpriteParedDestuctible();
    abstract public Sprite getSpritePlataformaMovil();
    abstract public Sprite getSpritePlataformaQuebradiza();
    abstract public Sprite getSpritePared();
    abstract public Sprite getSpriteEscalera();
    public Sprite getSpritePowerUp(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("botellaAzul", rutaACarpeta + "/PowerUps/botellaAzul.png");
        mapeo.put("botellaRoja", rutaACarpeta + "/PowerUps/botellaRoja.png");
        mapeo.put("botellaVerde", rutaACarpeta + "/PowerUps/botellaVerde.png");
        mapeo.put("fruta", rutaACarpeta + "/PowerUps/fruta.png");
        mapeo.put("vidaExtra", rutaACarpeta + "/PowerUps/VidaJugador.png");
        return new Sprite(mapeo);
    }
    abstract public Sprite getSpriteBotellaAzul();
    abstract public Sprite getSpriteBotellaRoja();
    abstract public Sprite getSpriteBotellaVerde();
    abstract public Sprite getSpriteFruta();
    abstract public Sprite getSpriteVidaExtra();
    abstract public Sprite getSpriteBolaNieve();
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("izquierda", rutaACarpeta + "/BolaDeFuego/boladefuegoizq.png");
        mapeo.put("derecha", rutaACarpeta + "/BolaDeFuego/boladefuegoder.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("disparoNormal1", rutaACarpeta + "/DisparoJugador/disparoJugador1.png");
        mapeo.put("disparoNormal2", rutaACarpeta + "/DisparoJugador/disparoJugador3.png");
        mapeo.put("disparoAumentado1", rutaACarpeta + "/DisparoJugador/disparoJugador2.png");
        mapeo.put("disparoAumentado2", rutaACarpeta + "/DisparoJugador/disparoJugador4.png");
        return new Sprite(mapeo);
    }
}