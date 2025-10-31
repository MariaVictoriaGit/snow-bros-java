package SnowBros.src.Grafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SnowBros.src.Entidades.Entidad;

public class ObserverGrafico extends JLabel implements Observer{
    protected Entidad entidadObservada;

    public ObserverGrafico(Entidad entidad) {
		entidadObservada = entidad;
		actualizar();
	}
    public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamano();
	}
    protected void actualizarImagen() {
        //No todas las entidades tienen mas de un sprite, como hacemos con las estaticas?
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("idleIzquierda");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
	    setIcon(icono);
	}
    protected void actualizarPosicionTamano(){
        int x = entidadObservada.getPosX();
		int y = entidadObservada.getPosY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y, ancho, alto);
    }
	public void actualizar(int direccion, int tocandoSuelo) {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("idleDerecha");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
}