import java.awt.EventQueue;

import SnowBros.src.Grafica.ControladorGrafica;
import SnowBros.src.Juego.Juego;

public class Launcher {	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego juego = new Juego();
					ControladorGrafica miControlador = new ControladorGrafica(juego);
					juego.setControladorGrafica(miControlador);
					miControlador.mostrarPantallaInicial();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

