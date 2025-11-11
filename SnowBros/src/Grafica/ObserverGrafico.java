package SnowBros.src.Grafica;
import java.awt.Container;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

public class ObserverGrafico extends JLabel implements Observer{
    protected EntidadDinamica entidadObservada;
	protected PlataformaMovil plataformaObservada;
	private JLabel imagen;
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
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("idleIzquierda");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
	    setIcon(icono);
		imagen=this;
	}
    protected void actualizarPosicionTamano(){
		int margenVisual=3;
        int x = entidadObservada.getPosX();
		int y = entidadObservada.getPosY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y+margenVisual, ancho, alto);
    }
	protected void actualizarPosicionTamanoP(){
		int margenVisual=4;
        int x = plataformaObservada.getPosX();
		int y = plataformaObservada.getPosY();
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		setBounds(x, y-margenVisual, ancho, alto);
    }
	public void actualizar(int direccion, int tocandoSuelo) {
		String rutaImagen = entidadObservada.getSprite().getRutaImagen("idleDerecha");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
	public void actualizarBolaDeNieve(boolean activo) {	
			SwingUtilities.invokeLater(() -> {
				try{
					String estado=null;
					if(activo)
						estado="congeladoDerecha";
					else
						estado="congelado";
					String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
					if (rutaImagen == null || rutaImagen.isBlank()) {
						return;
					}
					URL url= getClass().getClassLoader().getResource(rutaImagen);
            if (url == null) {
                System.err.println("No se encontró recurso: " + rutaImagen);
                return;
            }
            ImageIcon icono = new ImageIcon(url);
            setIcon(icono);
        }catch(Exception ex){
            ex.printStackTrace();
            }
	    });
	}
	public void borrarObserver(){
		entidadObservada=null;
		Container padre= getParent();
		getParent().remove(this);
		padre.revalidate();
		padre.repaint();
	}
	public JLabel getImagen(){
		return imagen;
	}
}