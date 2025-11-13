package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import SnowBros.src.Fabricas.FabricaEntidades;
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

    public ParserNivel(FabricaEntidades f){
        //que desde la llamada para crear el parser se defina el dominio de aplicacion
        this.fabrica = f;
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
                linea = linea.trim();
                if (linea.isEmpty()) continue; 
                String[] partes = linea.split(" ");
                if (partes.length < 3) continue; 
                String tipo=partes[0];
                int xEntidad=Integer.parseInt(partes[1]);
                int yArchivo=Integer.parseInt(partes[2]);
                int yEntidad=ConstantesVistas.PANEL_ALTO-yArchivo;                
                switch (tipo){
                    case "Jugador":
                        Jugador jugador=fabrica.getJugador(xEntidad, yEntidad);
                        jugador.setNivel(nivel);
                        nivel.agregarJugador(jugador);
                        break;
                    case "Plataforma":
                        Plataforma plataforma = fabrica.getPlataforma(xEntidad, yEntidad);
                        nivel.agregarPlataforma(plataforma);
                        break;
                    case "PlataformaMovil":
                        PlataformaMovil plataformaMovil = fabrica.getPlataformaMovil(xEntidad, yEntidad);
                        plataformaMovil.setNivel(nivel);
                        nivel.agregarPlataformaMovil(plataformaMovil);
                        break;
                    case "PlataformaQuebradiza":
                        PlataformaQuebradiza plataformaQuebradiza= fabrica.getPlataformaQuebradiza(xEntidad, yEntidad);
                        nivel.agregarPlataforma(plataformaQuebradiza);
                        break;
                    case "DemonioRojo":
                        DemonioRojo demonioRojo=fabrica.getDemonioRojo(xEntidad, yEntidad);
                        demonioRojo.setNivel(nivel);
                        nivel.agregarEnemigo(demonioRojo);
                        break;
                    case "TrollAmarillo":
                        TrollAmarillo trollAmarillo=fabrica.getTrollAmarillo(xEntidad, yEntidad);
                        trollAmarillo.setNivel(nivel);
                        nivel.agregarEnemigo(trollAmarillo);
                        break;
                    case "RanaDeFuego":
                        RanaDeFuego ranaFuego=fabrica.getRanaDeFuego(xEntidad, yEntidad);
                        ranaFuego.setNivel(nivel);
                        nivel.agregarEnemigo(ranaFuego);
                        break;
                    case "Calabaza":
                        Calabaza calabaza=fabrica.getCalabaza(xEntidad, yEntidad);
                        calabaza.setNivel(nivel);
                        nivel.agregarEnemigo(calabaza);
                        break;
                    case "Fantasma":
                        Fantasma fantasma=fabrica.getFantasma(xEntidad, yEntidad);
                        fantasma.setNivel(nivel);
                        nivel.agregarEnemigo(fantasma);
                        break;
                    case "Kamakichi":
                        Kamakichi kamakichi=fabrica.getKamakichi(xEntidad, yEntidad);
                        kamakichi.setNivel(nivel);
                        nivel.agregarEnemigo(kamakichi);
                        break;
                    case "Moghera":
                        Moghera moghera=fabrica.getMoghera(xEntidad, yEntidad);
                        moghera.setNivel(nivel);
                        nivel.agregarEnemigo(moghera);
                        break;
                    case "BotellaAzul":
                        BotellaAzul botellaAzul=fabrica.getBotellaAzul(xEntidad, yEntidad);
                        nivel.agregarPowerUp(botellaAzul);
                        break;
                    case "BotellaRoja":
                        BotellaRoja botellaRoja=fabrica.getBotellaRoja(xEntidad, yEntidad);
                        nivel.agregarPowerUp(botellaRoja);
                        break;
                    case "BotellaVerde":
                        BotellaVerde botellaVerde=fabrica.getBotellaVerde(xEntidad, yEntidad);
                        nivel.agregarPowerUp(botellaVerde);
                        break;
                    case "Fruta":
                        Fruta fruta=fabrica.getFruta(xEntidad, yEntidad);
                        nivel.agregarPowerUp(fruta);
                        break;                
                    case "VidaExtra":
                        VidaExtra vidaExtra=fabrica.getVidaExtra(xEntidad, yEntidad);
                        nivel.agregarPowerUp(vidaExtra);
                        break;
                    case "Escalera":
                        Escalera escalera=fabrica.getEscalera(xEntidad, yEntidad);
                        nivel.agregarObstaculo(escalera);
                        break;
                    case "TrampaPinchos":
                        TrampaPinchos trampaPinchos=fabrica.getTrampaPinchos(xEntidad, yEntidad);
                        nivel.agregarObstaculo(trampaPinchos);
                        break;                
                    case "Pared":
                        Pared pared=fabrica.getPared(xEntidad, yEntidad);
                        nivel.agregarObstaculo(pared);
                        nivel.agregarPared(pared);
                        break;
                    case "ParedDestructible":
                        ParedDestructible paredDestructible=fabrica.getParedDestructible(xEntidad, yEntidad);
                        nivel.agregarObstaculo(paredDestructible);
                        nivel.agregarPared(paredDestructible);
                        break;
                    case "SueloResbaladizo":
                        SueloResbaladizo sueloResbaladizo=fabrica.getSueloResbaladizo(xEntidad, yEntidad);
                        nivel.agregarResbaladizo(sueloResbaladizo);
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
                nivelACargar = ConstantesNiveles.NIVEL1;
            }
        return nivelACargar;
       }
    public void setSuperoNivelAnterior(boolean b){
        superoNivelAnterior=b;
    }
    public boolean getSuperoNivelAnterior(){
        return superoNivelAnterior;
    }
}
