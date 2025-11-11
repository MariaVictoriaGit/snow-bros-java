package SnowBros.src.Grafica;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;

public class ObserverJugador extends ObserverGrafico{
    //protected EntidadJugador jugadorObservado;
    protected Jugador jugadorObservado;
    public ObserverJugador(Jugador j){
        super(j);
        jugadorObservado= j;
        super.actualizar();
    }
    public void actualizar(int direccion, int tocandoSuelo){
        actualizarImagen(jugadorObservado.getDireccion(), jugadorObservado.getTocandoSuelo()); 
        super.actualizarPosicionTamano();
    }
    protected void actualizarImagen(int direccion, int tocandoSuelo) {
        SwingUtilities.invokeLater(() -> {
            try{
                jugadorObservado.setDireccion(direccion);
                String estado= jugadorObservado.generarClaveEstado(jugadorObservado.getDirAnterior());
                String rutaImagen = jugadorObservado.getSprite().getRutaImagen(estado);
                if (rutaImagen == null || rutaImagen.isBlank()) {
                    return;
                }
                URL url= getClass().getClassLoader().getResource(rutaImagen);
                if (url == null) {
                    System.err.println("3No se encontró recurso:" + rutaImagen);
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