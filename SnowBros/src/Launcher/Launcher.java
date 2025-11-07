package SnowBros.src.Launcher;
import java.awt.EventQueue;
import SnowBros.src.Grafica.GUI;
import SnowBros.src.Juego.Juego;

public class Launcher {	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego juego = new Juego();
					GUI miControlador = new GUI(juego);
					juego.setControladorGrafica(miControlador);
					miControlador.accionarInicioJuego();
					miControlador.mostrarPantallaJuego();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

