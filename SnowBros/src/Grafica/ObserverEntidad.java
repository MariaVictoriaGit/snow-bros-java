package SnowBros.src.Grafica;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

//import javax.swing.ImageIcon;
import SnowBros.src.Entidades.Entidad;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;

public class ObserverEntidad extends ObserverGrafico{
    protected EntidadDinamica entidadObservada;
    public ObserverEntidad(Entidad entidad) {
        super(entidad);
        //TODO Auto-generated constructor stub
    }
    public ObserverEntidad(EntidadDinamica entidad){
        super(entidad);
        entidadObservada=entidad;
        actualizar();
    }
    public void actualizar(int direccion, int estadoAereo) {
        actualizarImagen(direccion,entidadObservada.getEstadoAereo()); 
        super.actualizarPosicionTamano();
    }
    protected void actualizarImagen(int direccion, int estadoAereo) {
        SwingUtilities.invokeLater(() -> {
        try{
            entidadObservada.setDireccion(direccion);
            String estado= entidadObservada.generarClaveEstado();
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
}
