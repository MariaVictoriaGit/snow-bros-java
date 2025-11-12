package SnowBros.src.Fabricas;

import java.util.HashMap;

import SnowBros.src.Grafica.Sprite;

public class Alternativo extends FabricaSprites{

    public Alternativo() {
        super("SnowBros/src/Imagenes/Alternativo");
    }
    public Sprite get_silueta() {
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/FondoNivelAlternativo.jpg");
        return new Sprite(mapeo);	     	
    }
    public Sprite getSpriteJugador(){
        HashMap<String, String> mapeo = new HashMap<String,String>();
        mapeo.put("idleIzquierda", rutaACarpeta+ "/Jugador/nickIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta+ "/Jugador/nickDerechaAlternativo.png");
        mapeo.put("moverIzquierda", rutaACarpeta+ "/Jugador/nickAvanzarIzquierdaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta+ "/Jugador/nickAvanzarDerechaAlternativo.png");
        mapeo.put("saltandoDerecha", rutaACarpeta+ "/Jugador/JugadorSaltandoDerechaAlternativo.png");
        mapeo.put("saltandoIzquierda", rutaACarpeta+ "/Jugador/JugadorSaltandoIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataforma(){
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("normal", rutaACarpeta+ "/Plataforma/plataformaAlternativo.jpg"); 
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDemonioRojo(){
        HashMap<String, String> mapeo= new HashMap<>();//dejamos tanta animacion o la reducimos a uno en cada direccion para caminar y estar quieto?
        mapeo.put("idleIzquierda", rutaACarpeta + "/DemonioRojo/quietoIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/DemonioRojo/quietoDerechaAlternativo.png");
        mapeo.put("moverDerecha1", rutaACarpeta + "/DemonioRojo/caminarDerecha1Alternativo.png");
        mapeo.put("moverDerecha2", rutaACarpeta + "/DemonioRojo/caminarDerecha2Alternativo.png");
        mapeo.put("moverDerecha3", rutaACarpeta + "/DemonioRojo/caminarDerecha3Alternativo.png");
        mapeo.put("moverIzquierda1", rutaACarpeta + "/DemonioRojo/caminarIzquierda1Alternativo.png");
        mapeo.put("moverIzquierda2", rutaACarpeta + "/DemonioRojo/caminarIzquierda2Alternativo.png");
        mapeo.put("moverIzquierda3", rutaACarpeta + "/DemonioRojo/caminarIzquierda3Alternativo.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrollAmarillo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/TrollAmarillo/quietoIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/TrollAmarillo/quietoDerechaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/TrollAmarillo/caminarDerechaAlternativo.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/TrollAmarillo/caminarIzquierdaAlternativo.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteRanaDeFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/RanaDeFuego/quietaIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/RanaDeFuego/quietaDerechaAlternativo.png");
        mapeo.put("moverDerecha", rutaACarpeta + "/RanaDeFuego/AlternativoCaminandoDerecha.png");
        mapeo.put("moverIzquierda", rutaACarpeta + "/RanaDeFuego/AlternativoCaminandoIzquierda.png");
        mapeo.put("disparoDerecha", rutaACarpeta + "/RanaDeFuego/AlternativodisparoDerecha.png");
        mapeo.put("disparoIzquierda", rutaACarpeta + "/RanaDeFuego/AlternativodisparoIzquierda.png");
        mapeo.put("congelado",rutaACarpeta+"/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteCalabaza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleDerecha", rutaACarpeta + "/Calabaza/calabazaDerechaAlternativo.png");
        mapeo.put("idleIzquierda", rutaACarpeta + "/Calabaza/calabazaIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFantasma(){
        HashMap<String, String> mapeo= new HashMap<>();
            mapeo.put("idleDerecha", rutaACarpeta + "/Fantasma/fantasmaDerechaAlternativo.png");
            mapeo.put("idleIzquierda", rutaACarpeta + "/Fantasma/fantasmaIzquierdaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteMoghera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/Moghera/mogheraIAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/Moghera/mogheraDAlternativo.png");        
        mapeo.put("disparo", rutaACarpeta + "/Moghera/mogheraDisparoAlternativo.png");
        mapeo.put("disparo", rutaACarpeta+"/Moghera/mogheraDisparoAlternativo.png");
        mapeo.put("salto1", rutaACarpeta + "/Moghera/mogheraSalto1Alternativo.png");
        mapeo.put("salto2", rutaACarpeta + "/Moghera/mogheraSalto2Alternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteKamakichi(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/Kamakichi/kamakichiIquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/Kamakichi/kamakichiDerechaAlternativo.png");
        mapeo.put("disparo", rutaACarpeta + "/Kamakichi/kamakichiDisparandoAlternativo.png");
        mapeo.put("congelado", rutaACarpeta + "/Kamakichi/kamakichiCongeladoAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteTrampaPinchos(){    
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchosAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteSueloResbaladizo(){
        HashMap<String, String> mapeo= new HashMap<>(); 
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizoAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteParedDestuctible(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructibleAlternativo.jpg");
        return new Sprite(mapeo);   
    }
    public Sprite getSpritePlataformaMovil(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaMovilAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePlataformaQuebradiza(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Plataforma/plataformaQuebradizaAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePared(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteEscalera(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escaleraAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteObstaculo(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/escaleraAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/trampaPinchosAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/sueloResbaladizoAlternativo.jpg");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredDestructibleAlternativo.jpg");
        mapeo.put("normal", rutaACarpeta + "/Obstaculos/paredAlternativo.jpg");
        return new Sprite(mapeo);
    }
    public Sprite getSpritePowerUp(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaAzulAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaRojaAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/botellaVerdeAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/frutaAlternativo.png");
        mapeo.put("normal", rutaACarpeta + "/PowerUps/vidaJugadorAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaAzul(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaAzulAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaRoja(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaRojaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBotellaVerde(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/botellaVerdeAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteFruta(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/frutaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteVidaExtra(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("normal", rutaACarpeta+"/PowerUps/vidaJugadorAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaNieve(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/BolaDeNieve/bolaDeNieveAlternativo.gif");
        mapeo.put("derretida", rutaACarpeta + "/BolaDeNieve/bolaDeNieveAlternativo.gif");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteBolaFuego(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleIzquierda", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoIzquierdaAlternativo.png");
        mapeo.put("idleDerecha", rutaACarpeta + "/BolaDeFuego/bolaDeFuegoDerechaAlternativo.png");
        return new Sprite(mapeo);
    }
    public Sprite getSpriteDisparoJugador(){
        HashMap<String, String> mapeo= new HashMap<>();
        mapeo.put("idleDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalDerechaAlternativo.gif");
        mapeo.put("idleIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorNormalIzquierdaAlternativo.gif");
        mapeo.put("aumentadoDerecha", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoDerechaAlternativo.gif");
        mapeo.put("aumentadoIzquierda", rutaACarpeta + "/DisparoJugador/disparoJugadorAumentadoIzquierdaAlternativo.gif");
        return new Sprite(mapeo);
    }
}