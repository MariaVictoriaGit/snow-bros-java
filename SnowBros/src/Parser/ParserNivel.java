package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Fabricas.Original;
import SnowBros.src.Fabricas.Alternativo;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Silueta;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;

public class ParserNivel {
    private FabricaEntidades fabrica;

    public ParserNivel(boolean dominioOriginal) {
        //que desde la llamada para crear el parser se defina el dominio de aplicacion
        this.fabrica = new FabricaEntidades(new Original("niveles/"));//coregir ruta a fabrica
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
                    Entidad jugador = fabrica.getJugador(xJugador, yJugador);
                    nivel.agregarJugador(jugador);
                    break;

                case "Plataforma":
                    int x = Integer.parseInt(partes[1]);
                    int y = ConstantesVistas.PANEL_ALTO - Integer.parseInt(partes[2]);
                    Entidad plataforma = fabrica.getPlataforma(x, y);
                    nivel.agregarPlataforma(plataforma);
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
                return "niveles/clasico 1.txt";
            case 2:
                return "niveles/clasico 2.txt";
            case 3:
                return "niveles/supervivencia 1.txt";
            case 4:
                return "niveles/supervivencia 2.txt";
            case 5:
                return "niveles/contrarreloj 1.txt";
            default:
                return "niveles/contrarreloj 2.txt";
        }
       }




    //TODO metodos que segun el nombre del archivo condicione el modo de juego
    //TODO original y alternativo
}
