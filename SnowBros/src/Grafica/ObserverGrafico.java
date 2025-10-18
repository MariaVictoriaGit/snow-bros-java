package Grafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Juego.Entidad;

public class ObserverGrafico extends JLabel implements Observer{
    protected Entidad entidadObservada;

    public ObserverGrafico(Entidad entidad) {
		super();
		entidadObservada = entidad;
		actualizar();
	}
    public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamano();
	}
    protected void actualizarImagen() {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen();
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
    protected void actualizarPosicionTamano(){
        int x = entidadObservada.getPosX();
		int y = adaptarY(entidadObservada.getPosY());
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y, ancho, alto);
    }
    private int adaptarY(int y){
        return PANEL_ALTO - PADDING - y;
    }
}