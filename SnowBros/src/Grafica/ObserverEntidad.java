package SnowBros.src.Grafica;
import java.net.URL;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;

public class ObserverEntidad extends ObserverGrafico{
    public ObserverEntidad(EntidadDinamica entidad) {
        super(entidad);
    }
    public void actualizar(int direccion, int tocandoSuelo) {
        if(entidadObservada!=null){
            actualizarImagen(entidadObservada.getDireccion(),entidadObservada.getTocandoSuelo()); 
            super.actualizarPosicionTamano();
        }
    }
    protected void actualizarImagen(int direccion, int tocandoSuelo) {   
        SwingUtilities.invokeLater(() -> {
            if(entidadObservada!=null){
                try{
                    entidadObservada.setDireccion(direccion);
                    String estado= entidadObservada.generarClaveEstado();
                    String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
                    if (rutaImagen == null || rutaImagen.isBlank())
                        return;
                    URL url= getClass().getClassLoader().getResource(rutaImagen);
                    if (url == null) {
                        System.err.println("1No se encontró recurso: " + rutaImagen);
                        return;
                    }
                    ImageIcon icono = new ImageIcon(url);
                    setIcon(icono);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });   
    }
}
