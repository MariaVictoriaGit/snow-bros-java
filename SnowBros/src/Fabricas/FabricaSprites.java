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
        mapeo.put("idleIzquierda", rutaACarpeta + "/DemonioRojo/quietoIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/DemonioRojo/quietoDerecha.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminarDerecha1.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminarDerecha2.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminarDerecha3.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminarIzquierda1.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminarIzquierda2.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminarIzquierda3.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/TrollAmarillo/quietoIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/TrollAmarillo/quietoDerecha.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/caminarDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/caminarIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteRanaDeFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/RanaDeFuego/quietaIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/RanaDeFuego/quietaDerecha.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/RanaDeFuego/caminarDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/RanaDeFuego/caminarIzquierda.png");
        mapeo.put("disparoDerecha", rutaACarpeta + "/RanaDeFuego/disparoDerecha.png");
        mapeo.put("disparoIzquierda", rutaACarpeta + "/RanaDeFuego/disparoIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idle", rutaACarpeta + "/Calabaza/calabaza.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("moverDerecha", rutaACarpeta + "/Fantasma/moverDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/Fantasma/moverIzquierda.png");
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
    public Sprite getSpriteObstaculo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("escalera", rutaACarpeta + "/Obstaculos/escalera.png");
        mapeo.put("trampaPinchos", rutaACarpeta + "/Obstaculos/trampaPinchos.png");
        return new Sprite(mapeo);
    }
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
    public Sprite getSpriteBolaNieve(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("completa", rutaACarpeta + "/BolaDeNieve/bolaDeNieve.png");
        mapeo.put("derretida", rutaACarpeta + "/BolaDeNieve/bolaDeNieveDerretida.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("izquierda", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoIzquierda.png");
        mapeo.put("derecha", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoDerecha.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("disparoNormal1", rutaACarpeta + "/DisparoJugador/disparoJugadorNormal1.png");
        mapeo.put("disparoNormal2", rutaACarpeta + "/DisparoJugador/disparoJugadorNormal2.png");
        mapeo.put("disparoAumentado1", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentado1.png");
        mapeo.put("disparoAumentado2", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentado2.png");
        return new Sprite(mapeo);
    }
}