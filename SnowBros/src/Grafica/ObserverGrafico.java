package SnowBros.src.Grafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import SnowBros.src.Paneles.PanelPantallaJuego;
import SnowBros.src.Datos.*;
import SnowBros.src.Entidades.Entidad;

public class ObserverGrafico extends JLabel implements Observer{
    protected Entidad entidadObservada;
	protected PanelPantallaJuego panelPantallaJuego;

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
        //No todas las entidades tienen mas de un sprite, como hacemos con las estaticas?
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("SPRITESESTATICO");
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
        return ConstantesVistas.PANEL_ALTO - ConstantesVistas.PADDING - y;
    }
	@Override
	public void actualizar(int direccion, int estadoAereo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
	}
}