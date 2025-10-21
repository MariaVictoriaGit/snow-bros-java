package SnowBros.src.Parser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Juego.Nivel;
import SnowBros.src.Juego.Silueta;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.Entidad;

public class ParserNivel {
    private FabricaEntidades fabrica;

    public ParserNivel() {
        this.fabrica = new FabricaEntidades();
    }
    public Nivel generarNivel(int modo){
        return cargarNivel(obtenerArchivoNivel(modo), modo);
    }
    private Nivel cargarNivel(String archivo, int modo) {
    Silueta silueta= fabrica.getSilueta();
    Nivel nivel = new Nivel();

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
       /* switch (modo) {
            case 1:
                return "niveles/clasico1.txt";
            
        }*/
        return "niveles/clasico1.txt";
    }
}
