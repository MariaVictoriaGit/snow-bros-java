package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.FabricaSprites;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Silueta;
import SnowBros.src.Juego.Sonido;
import SnowBros.src.Datos.ConstantesNiveles;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.*;

public class ParserNivel{
    private FabricaEntidades fabrica;
    private boolean superoNivelAnterior;

    public ParserNivel(FabricaSprites fabricaSprites){
        //que desde la llamada para crear el parser se defina el dominio de aplicacion
        this.fabrica = new FabricaEntidades(fabricaSprites);
        superoNivelAnterior=false;
    }
    public Nivel generarNivel(String modoDeJuego){
        return cargarNivel(obtenerArchivoNivel(modoDeJuego));
    }
    private Nivel cargarNivel(String archivo){
        Silueta silueta= fabrica.getSilueta();
        Nivel nivel = new Nivel(silueta);
        try (BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=br.readLine())!=null){
                String[] partes=linea.split("\\s+"); // divide por espacios
                String tipo=partes[0];
                switch (tipo){
                    case "Jugador":
                        int xJugador=Integer.parseInt(partes[1]);
                        int yArchivo=Integer.parseInt(partes[2]);
                        int yJugador=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Jugador jugador=fabrica.getJugador(xJugador, yJugador);
                        jugador.setNivel(nivel);
                        nivel.agregarJugador(jugador);
                        break;
                    case "Plataforma":
                        int xPlataforma=Integer.parseInt(partes[1]);
                        int yPlataforma=ConstantesVistas.PANEL_ALTO-Integer.parseInt(partes[2]);
                        Plataforma plataforma = fabrica.getPlataforma(xPlataforma, yPlataforma);
                        nivel.agregarPlataforma(plataforma);
                        break;
                    case "PlataformaMovil":
                        int xPlataformaMovil=Integer.parseInt(partes[1]);
                        int yPlataformaMovil=ConstantesVistas.PANEL_ALTO-Integer.parseInt(partes[2]);
                        PlataformaMovil plataformaMovil = fabrica.getPlataformaMovil(xPlataformaMovil, yPlataformaMovil);
                        plataformaMovil.setNivel(nivel);
                        nivel.agregarPlataformaMovil(plataformaMovil);
                        break;
                    case "PlataformaQuebradiza":
                        int xPlataformaQuebradiza=Integer.parseInt(partes[1]);
                        int yPlataformaQuebradiza=ConstantesVistas.PANEL_ALTO-Integer.parseInt(partes[2]);
                        PlataformaQuebradiza plataformaQuebradiza= fabrica.getPlataformaQuebradiza(xPlataformaQuebradiza, yPlataformaQuebradiza);
                        nivel.agregarPlataforma(plataformaQuebradiza);
                        break;
                    case "DemonioRojo":
                        int xDemRojo=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yDemRojo=ConstantesVistas.PANEL_ALTO-yArchivo;
                        DemonioRojo demonioRojo=fabrica.getDemonioRojo(xDemRojo, yDemRojo);
                        demonioRojo.setNivel(nivel);
                        nivel.agregarEnemigo(demonioRojo);
                        break;
                    case "TrollAmarillo":
                        int xTrollAmarillo=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yTrollAmarillo=ConstantesVistas.PANEL_ALTO-yArchivo;
                        TrollAmarillo trollAmarillo=fabrica.getTrollAmarillo(xTrollAmarillo, yTrollAmarillo);
                        trollAmarillo.setNivel(nivel);
                        nivel.agregarEnemigo(trollAmarillo);
                        break;
                    case "RanaDeFuego":
                        int xRanaFuego=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yRanafuego=ConstantesVistas.PANEL_ALTO-yArchivo;
                        RanaDeFuego ranaFuego=fabrica.getRanaDeFuego(xRanaFuego, yRanafuego);
                        ranaFuego.setNivel(nivel);
                        nivel.agregarEnemigo(ranaFuego);
                        break;
                    case "Calabaza":
                        int xCalabaza=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yCalabaza=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Calabaza calabaza=fabrica.getCalabaza(xCalabaza, yCalabaza);
                        calabaza.setNivel(nivel);
                        nivel.agregarEnemigo(calabaza);
                        break;
                    case "Fantasma":
                        int xFantasma=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yFantasma=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Fantasma fantasma=fabrica.getFantasma(xFantasma, yFantasma);
                        fantasma.setNivel(nivel);
                        nivel.agregarEnemigo(fantasma);
                        break;
                    case "Kamakichi":
                        int xKamakichi=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yKamakichi=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Kamakichi kamakichi=fabrica.getKamakichi(xKamakichi, yKamakichi);
                        kamakichi.setNivel(nivel);
                        nivel.agregarEnemigo(kamakichi);
                        break;
                    case "Moghera":
                        int xMoghera=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yMoghera=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Moghera moghera=fabrica.getMoghera(xMoghera, yMoghera);
                        moghera.setNivel(nivel);
                        nivel.agregarEnemigo(moghera);
                        break;
                    case "BotellaAzul":
                        int xBotellaAzul=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaAzul=ConstantesVistas.PANEL_ALTO-yArchivo;
                        BotellaAzul botellaAzul=fabrica.getBotellaAzul(xBotellaAzul, yBotellaAzul);
                        nivel.agregarPowerUp(botellaAzul);
                        break;
                    case "BotellaRoja":
                        int xBotellaRoja=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaRoja=ConstantesVistas.PANEL_ALTO-yArchivo;
                        BotellaRoja botellaRoja=fabrica.getBotellaRoja(xBotellaRoja, yBotellaRoja);
                        nivel.agregarPowerUp(botellaRoja);
                        break;
                    case "BotellaVerde":
                        int xBotellaVerde=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaVerde=ConstantesVistas.PANEL_ALTO-yArchivo;
                        BotellaVerde botellaVerde=fabrica.getBotellaVerde(xBotellaVerde, yBotellaVerde);
                        nivel.agregarPowerUp(botellaVerde);
                        break;
                    case "Fruta":
                        int xFruta=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yFruta=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Fruta fruta=fabrica.getFruta(xFruta, yFruta);
                        nivel.agregarPowerUp(fruta);
                        break;                
                    case "VidaExtra":
                        int xVidaExtra=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yVidaExtra=ConstantesVistas.PANEL_ALTO-yArchivo;
                        VidaExtra vidaExtra=fabrica.getVidaExtra(xVidaExtra, yVidaExtra);
                        nivel.agregarPowerUp(vidaExtra);
                        break;
                    case "Escalera":
                        int xEscalera=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yEscalera=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Escalera escalera=fabrica.getEscalera(xEscalera, yEscalera);
                        nivel.agregarObstaculo(escalera);
                        break;
                    case "TrampaPinchos":
                        int xTrampaPinchos=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yTrampaPinchos=ConstantesVistas.PANEL_ALTO-yArchivo;
                        TrampaPinchos trampaPinchos=fabrica.getTrampaPinchos(xTrampaPinchos, yTrampaPinchos);
                        nivel.agregarObstaculo(trampaPinchos);
                        break;                
                    case "Pared":
                        int xPared=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yPared=ConstantesVistas.PANEL_ALTO-yArchivo;
                        Pared pared=fabrica.getPared(xPared, yPared);
                        nivel.agregarObstaculo(pared);
                        nivel.agregarPared(pared);
                        break;
                    case "ParedDestructible":
                        int xParedDestructible=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yParedDestructible=ConstantesVistas.PANEL_ALTO-yArchivo;
                        ParedDestructible paredDestructible=fabrica.getParedDestructible(xParedDestructible, yParedDestructible);
                        nivel.agregarObstaculo(paredDestructible);
                        nivel.agregarPared(paredDestructible);
                        break;
                    case "SueloResbaladizo":
                        int xSueloResbaladizo=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int ySueloResbaladizo=ConstantesVistas.PANEL_ALTO-yArchivo;
                        SueloResbaladizo sueloResbaladizo=fabrica.getSueloResbaladizo(xSueloResbaladizo, ySueloResbaladizo);
                        nivel.agregarObstaculo(sueloResbaladizo);
                        break;                
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nivel;
    }
    public String obtenerArchivoNivel(String modo) {
        String nivelACargar=null;
        if(modo.equals("Clasico"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel1.wav");
                nivelACargar = ConstantesNiveles.NIVEL1;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel2.wav");
                nivelACargar = ConstantesNiveles.NIVEL2;
            }
        else if(modo.equals("Supervivencia"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel3.wav");
                nivelACargar = ConstantesNiveles.NIVEL2;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel4.wav");
                nivelACargar = ConstantesNiveles.NIVEL3;
            }
        else if(modo.equals("Contrarreloj"))
            if(!superoNivelAnterior){
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel5.wav");
                nivelACargar = ConstantesNiveles.NIVEL3;
            }else{
                Sonido.getInstancia().reproducirMusica("temaMusicalNivel6.wav");
                nivelACargar = ConstantesNiveles.NIVEL4;
            }
        return nivelACargar;
       }
    public void setSuperoNivelAnterior(boolean b){
        superoNivelAnterior=b;
    }
    public boolean superoNivelAnterior(){
        return superoNivelAnterior;
    }
}
