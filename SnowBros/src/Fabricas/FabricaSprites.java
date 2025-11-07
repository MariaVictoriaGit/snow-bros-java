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
        mapeo.put("normal", rutaACarpeta+ "/Pantallas/FondoNivel1.jpg");
        return new Sprite(mapeo);	     	
    }
    public Sprite getSpriteJugador(){//podemos reducir sprites de jugadores
        HashMap<String, String> mapeo = new HashMap<String,String>();
        mapeo.put("idleIzquierda", rutaACarpeta+ "/Jugador/nickIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta+ "/Jugador/nickDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta+ "/Jugador/nickAvanzarIzquierda.png");
        mapeo.put("moverDerecha", rutaACarpeta+ "/Jugador/nickAvanzarDerecha.png");
        mapeo.put("saltandoDerecha", rutaACarpeta+ "/Jugador/JugadorSaltandoDerecha.png");
        mapeo.put("saltandoIzquierda", rutaACarpeta+ "/Jugador/JugadorSaltandoIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataforma(){  //TODO lo hacemos abstracto o lo dejamos implementado?
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/Plataforma/plataforma.jpg"); 
        mapeo.put("esquinaDerecha", rutaACarpeta+ "/Plataforma/plataformaEstaticaDerecha.jng");
        mapeo.put("esquinaIzquierda", rutaACarpeta+ "/Plataforma/plataformaEstaticaIzquierda.jpg");
         return new Sprite(mapeo);
    }
    public Sprite getSpriteDemonioRojo(){
        HashMap<String, String> mapeo= new HashMap<>();//dejamos tanta animacion o la reducimos a uno en cada direccion para caminar y estar quieto?
        mapeo.put("idleIzquierda", rutaACarpeta + "/DemonioRojo/quietoIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/DemonioRojo/quietoDerecha.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminarDerecha1.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminarDerecha2.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminarDerecha3.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminarIzquierda1.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminarIzquierda2.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminarIzquierda3.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/TrollAmarillo/quietoIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/TrollAmarillo/quietoDerecha.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/caminarDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/caminarIzquierda.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
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
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieve.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleDerecha", rutaACarpeta + "/Calabaza/calabazaDerecha.png");
        mapeo.put("idleIzquierda", rutaACarpeta + "/Calabaza/calabazaIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
            mapeo.put("idleDerecha", rutaACarpeta + "/Fantasma/fantasmaDerecha.png");
            mapeo.put("idleIzquierda", rutaACarpeta + "/Fantasma/fantasmaIzquierda.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteMoghera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/Moghera/mogera.png");//32x40
        mapeo.put("idleDerecha", rutaACarpeta + "/Moghera/mogera.png");        
        mapeo.put("saltandoIzquierda", rutaACarpeta + "/Moghera/mogheraIzquierda.png");
        mapeo.put("saltandoDerecha", rutaACarpeta + "/Moghera/mogheraDerecha.png");
        mapeo.put("congeladoIzquierda", rutaACarpeta + "/Moghera/mogheraCongeladoIzquierda.png");
        mapeo.put("congeladoDerecha", rutaACarpeta + "/Moghera/mogheraCongeladoDerecha.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteKamakichi(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/Kamakichi/kamakichiIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/Kamakichi/kamakichiDerecha.png");
        mapeo.put("disparo", rutaACarpeta + "/Kamakichi/kamakichiDisparando.png");//44 alto
        mapeo.put("congelado", rutaACarpeta + "/Kamakichi/kamakichiCongelado.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrampaPinchos(){    
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchos.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteSueloResbaladizo(){
        HashMap<String, String> mapeo= new HashMap<>(); 
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteParedDestuctible(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructible.jpg");
        return new Sprite(mapeo);   
    }
    public Sprite getSpritePlataformaMovil(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaMovil.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataformaQuebradiza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaQuebradiza.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePared(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/pared.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteEscalera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escalera.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteObstaculo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escalera.png");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchos.png");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizo.jpg");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructible.jpg");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/pared.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePowerUp(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaAzul.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaRoja.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaVerde.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/fruta.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/VidaJugador.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaAzul(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaAzul.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaRoja(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaRoja.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaVerde(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaVerde.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFruta(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/fruta.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteVidaExtra(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/vidaJugador.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaNieve(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/BolaDeNieve/bolaDeNieve.png");
        mapeo.put("derretida", rutaACarpeta + "/BolaDeNieve/bolaDeNieveDerretida.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoIzquierda.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoDerecha.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalDerecha1.png");
        mapeo.put("normalDerecha2", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalDerecha2.png");
        mapeo.put("idleIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalIzquierda1.png");
        mapeo.put("normalIzquierda2", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalIzquierda2.png");
        mapeo.put("aumentadoDerecha1", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoDerecha1.png");
        mapeo.put("aumentadoDerecha2", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoDerecha2.png");
        mapeo.put("aumentadoIzquierda1", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoIzquierda1.png");
        mapeo.put("aumentadoIzquierda2", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoIzquierda2.png");
        return new Sprite(mapeo);
    }
} 