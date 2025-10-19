import java.awt.EventQueue;

import SnowBros.src.Grafica.ControladorGrafica;
import SnowBros.src.Juego.Juego;

public class Launcher {
    public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControladorGrafica controlador_grafica = new ControladorGrafica();
					Juego juego = new Juego(controlador_grafica);
					controlador_grafica.registrar_controlador_juego(juego);
					juego.configurar();
					controlador_grafica.mostrarPantallaInicial();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

