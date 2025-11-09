package SnowBros.src.Grafica;

import java.awt.Container;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

public class ObserverGrafico extends JLabel implements Observer{
    protected EntidadDinamica entidadObservada;
	protected PlataformaMovil plataformaObservada;

    public ObserverGrafico(EntidadDinamica entidad) {
		entidadObservada = entidad;
		actualizar();
	}
	public ObserverGrafico(PlataformaMovil entidad) {
		plataformaObservada = entidad;
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
		int margenVisual=4;
        int x = entidadObservada.getPosX();
		int y = entidadObservada.getHitbox().getY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y-margenVisual, ancho, alto);
    }
	protected void actualizarPosicionTamanoP(){
		int margenVisual=4;
        int x = plataformaObservada.getPosX();
		int y = plataformaObservada.getHitbox().getY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y-margenVisual, ancho, alto);
    }
	public void actualizar(int direccion, int tocandoSuelo) {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("idleDerecha");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
	@Override
	public void actualizarBolaDeNieve() {
		
		throw new UnsupportedOperationException("Unimplemented method 'actualizarBolaDeNieve'");
	}
	public void borrarObserver(){
		entidadObservada=null;
		Container padre= getParent();
		getParent().remove(this);
		padre.revalidate();
		padre.repaint();
	}
}