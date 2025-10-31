package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.Original;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Silueta;
import SnowBros.src.Datos.ConstantesNiveles;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.Enemigos.*;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.Plataforma;
import SnowBros.src.Entidades.EntidadesEstaticas.PowerUps.*;

public class ParserNivel{
    private FabricaEntidades fabrica;

    public ParserNivel(boolean dominioOriginal){
        //que desde la llamada para crear el parser se defina el dominio de aplicacion
        this.fabrica = new FabricaEntidades(new Original("SnowBros/src/Imagenes"));
    }
    public Nivel generarNivel(int modo){
        return cargarNivel(obtenerArchivoNivel(modo), modo);
    }
    private Nivel cargarNivel(String archivo, int modo){
        Silueta silueta= fabrica.getSilueta();
        Nivel nivel = new Nivel(silueta,1);//TODO seleccion de nivel
        try (BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea=br.readLine())!=null){
                String[] partes=linea.split("\\s+"); // divide por espacios
                String tipo=partes[0];
                switch (tipo){
                    case "Jugador":
                        int xJugador=Integer.parseInt(partes[1]);
                        int yArchivo=Integer.parseInt(partes[2]);
                        int yJugador=ConstantesVistas.PANEL_ALTO-Integer.parseInt(partes[2])-ConstantesVistas.PADDING;
                        Jugador jugador=fabrica.getJugador(xJugador, yJugador);
                        nivel.agregarJugador(jugador);
                        break;
                    case "Plataforma":
                        int x=Integer.parseInt(partes[1]);
                        int y=ConstantesVistas.PANEL_ALTO-Integer.parseInt(partes[2]);
                        Plataforma plataforma = fabrica.getPlataforma(x, y);
                        nivel.agregarPlataforma(plataforma);
                        break;
                    case "DemonioRojo":
                        int xDemRojo=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yDemRojo=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        DemonioRojo demonioRojo=fabrica.getDemonioRojo(xDemRojo, yDemRojo);
                        nivel.agregarEnemigo(demonioRojo);
                        break;
                    case "TrollAmarillo":
                        int xTrollAmarillo=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yTrollAmarillo=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        TrollAmarillo trollAmarillo=fabrica.getTrollAmarillo(xTrollAmarillo, yTrollAmarillo);
                        nivel.agregarEnemigo(trollAmarillo);
                        break;
                    case "RanaDeFuego":
                        int xRanaFuego=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yRanafuego=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        RanaDeFuego ranaFuego=fabrica.getRanaDeFuego(xRanaFuego, yRanafuego);
                        nivel.agregarEnemigo(ranaFuego);
                        break;
                    case "Calabaza":
                        int xCalabaza=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yCalabaza=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        Calabaza calbaza=fabrica.getCalabaza(xCalabaza, yCalabaza);
                        nivel.agregarEnemigo(calbaza);
                        break;
                    case "Fantasma":
                        int xFantasma=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yFantasma=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        Fantasma fantasma=fabrica.getFantasma(xFantasma, yFantasma);
                        nivel.agregarEnemigo(fantasma);
                        break;
                    case "Kamakichi":
                        int xKamakichi=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yKamakichi=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        Kamakichi kamakichi=fabrica.getKamakichi(xKamakichi, yKamakichi);
                        nivel.agregarEnemigo(kamakichi);
                        break;
                    case "Moghera":
                        int xMoghera=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yMoghera=ConstantesVistas.PANEL_ALTO-yArchivo-ConstantesVistas.PADDING;
                        Moghera moghera=fabrica.getMoghera(xMoghera, yMoghera);
                        nivel.agregarEnemigo(moghera);
                        break;
                    case "BotellaAzul":
                        int xBotellaAzul=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaAzul=ConstantesVistas.PANEL_ALTO-yArchivo+ConstantesVistas.PADDING/2;
                        BotellaAzul botellaAzul=fabrica.getBotellaAzul(xBotellaAzul, yBotellaAzul);
                        nivel.agregarPowerUp(botellaAzul);
                        break;
                    case "BotellaRoja":
                        int xBotellaRoja=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaRoja=ConstantesVistas.PANEL_ALTO-yArchivo+ConstantesVistas.PADDING/2;
                        BotellaRoja botellaRoja=fabrica.getBotellaRoja(xBotellaRoja, yBotellaRoja);
                        nivel.agregarPowerUp(botellaRoja);
                        break;
                    case "BotellaVerde":
                        int xBotellaVerde=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yBotellaVerde=ConstantesVistas.PANEL_ALTO-yArchivo+ConstantesVistas.PADDING/2;
                        BotellaVerde botellaVerde=fabrica.getBotellaVerde(xBotellaVerde, yBotellaVerde);
                        nivel.agregarPowerUp(botellaVerde);
                        break;
                    case "Fruta":
                        int xFruta=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yFruta=ConstantesVistas.PANEL_ALTO-yArchivo+ConstantesVistas.PADDING/2;
                        Fruta fruta=fabrica.getFruta(xFruta, yFruta);
                        nivel.agregarPowerUp(fruta);
                        break;                
                    case "VidaExtra":
                        int xVidaExtra=Integer.parseInt(partes[1]);
                        yArchivo=Integer.parseInt(partes[2]);
                        int yVidaExtra=ConstantesVistas.PANEL_ALTO-yArchivo+ConstantesVistas.PADDING/2;
                        VidaExtra vidaExtra=fabrica.getVidaExtra(xVidaExtra, yVidaExtra);
                        nivel.agregarPowerUp(vidaExtra);
                        break;
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nivel;
    }
    public String obtenerArchivoNivel(int modo) {
        switch (modo) {
            case 1:
                return ConstantesNiveles.CLASICO1;
            case 2:
                return ConstantesNiveles.CLASICO2;
            case 3:
                return ConstantesNiveles.SUPERVIVENCIA1;
            case 4:
                return ConstantesNiveles.SUPERVIVENCIA2;
            case 5:
                return ConstantesNiveles.CONTRARRELOJ1;
            default:
                return ConstantesNiveles.CONTRARRELOJ2;
        }
       }
    //TODO metodos que segun el nombre del archivo condicione el modo de juego
    //TODO original y alternativo
}
