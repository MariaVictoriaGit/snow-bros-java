package SnowBros.src.Parser;

import java.io.IOException;

import SnowBros.src.Fabricas.FabricaEntidades;
import SnowBros.src.Juego.Nivel;

public class ParserNivel {
    private FabricaEntidades fabrica;

    public ParserNivel() {
        this.fabrica = new FabricaEntidades();
    }
    public Nivel generarNivel(int modo){
        return cargarNivel(obtenerArchivoNivel(modo), modo);
    }
    private Nivel cargarNivel(String archivo, int modo) {
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
                    int yJugador = PANEL_ALTO - yArchivo;
                    Entidad jugador = fabrica.getJugador(xJugador, yJugador);
                    nivel.agregarJugador(jugador);
                    break;

                case "Plataforma":
                    int x = Integer.parseInt(partes[1]);
                    int y = PANEL_ALTO - Integer.parseInt(partes[2]);
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
                return "niveles/clasico1.txt";
            
        }
    }
}
