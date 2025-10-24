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
import SnowBros.src.Entidades.Entidad;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;

public class ParserNivel {
    private FabricaEntidades fabrica;

    public ParserNivel(boolean dominioOriginal) {
        //que desde la llamada para crear el parser se defina el dominio de aplicacion
        this.fabrica = new FabricaEntidades(new Original("SnowBros/src/Imagenes"));
    }
    public Nivel generarNivel(int modo){
        return cargarNivel(obtenerArchivoNivel(modo), modo);
    }
    private Nivel cargarNivel(String archivo, int modo) {
        Silueta silueta= fabrica.getSilueta();

        Nivel nivel = new Nivel(silueta,1);

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\s+"); // divide por espacios
                String tipo = partes[0];

                switch (tipo) {
                    case "Jugador":
                        int xJugador = Integer.parseInt(partes[1]);
                        int yArchivo = Integer.parseInt(partes[2]);
                        int yJugador = ConstantesVistas.PANEL_ALTO - yArchivo;
                        EntidadDinamica jugador = fabrica.getJugador(xJugador, yJugador-500);
                        nivel.agregarJugador(jugador);
                        break;

                    case "Plataforma":
                        int x = Integer.parseInt(partes[1]);
                        int y = ConstantesVistas.PANEL_ALTO - Integer.parseInt(partes[2]);
                        Entidad plataforma = fabrica.getPlataforma(x, y);
                        nivel.agregarPlataforma(plataforma);

                        break;

                    case "DemonioRojo":
                        int xDemRojo = Integer.parseInt(partes[1]);
                        yArchivo = Integer.parseInt(partes[2]);
                        int yDemRojo = ConstantesVistas.PANEL_ALTO - yArchivo;
                        EntidadDinamica demonioRojo = fabrica.getDemonioRojo(xDemRojo, yDemRojo-500);
                        nivel.agregarEnemigo(demonioRojo);
                        break;

                    case "TrollAmarillo":
                        int xTrollAmarillo = Integer.parseInt(partes[1]);
                        yArchivo = Integer.parseInt(partes[2]);
                        int yTrollAmarillo = ConstantesVistas.PANEL_ALTO - yArchivo;
                        EntidadDinamica trollAmarillo = fabrica.getTrollAmarillo(xTrollAmarillo, yTrollAmarillo-500);
                        nivel.agregarEnemigo(trollAmarillo);
                        break;

                    case "RanaDeFuego":
                        int xRanaFuego = Integer.parseInt(partes[1]);
                        yArchivo = Integer.parseInt(partes[2]);
                        int yRanafuego = ConstantesVistas.PANEL_ALTO - yArchivo;
                        EntidadDinamica ranaFuego = fabrica.getRanaDeFuego(xRanaFuego, yRanafuego-500);
                        nivel.agregarEnemigo(ranaFuego);
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
