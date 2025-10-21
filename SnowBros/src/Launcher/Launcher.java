import java.awt.EventQueue;

import SnowBros.src.Grafica.GUI;
import SnowBros.src.Juego.Juego;

public class Launcher {	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego juego = new Juego();
					juego.iniciar();
					GUI miControlador = new GUI(juego);
					juego.setControladorGrafica(miControlador);
					miControlador.mostrarPantallaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

