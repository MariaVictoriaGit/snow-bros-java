package SnowBros.src.Grafica;

import javax.swing.ImageIcon;

//import Juego.EntidadJugador;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;

public class ObserverJugador extends ObserverGrafico{
    //protected EntidadJugador jugadorObservado;
    protected Jugador jugadorObservado;
    public ObserverJugador(Jugador j){
        super(j);
        jugadorObservado= j;
        actualizar();
    }
    public void actualizar() {
        super.actualizar();
    }
    public void actualizar(int direccion, int estadoAereo){
        actualizarImagen(jugadorObservado.getDireccion(), jugadorObservado.getEstadoAereo()); 
        super.actualizarPosicionTamano();

    }
    protected void actualizarImagen(int direccion, int estadoAereo) {
        jugadorObservado.setDireccion(direccion);
        String estado= jugadorObservado.generarClaveEstado(direccion);
		String rutaImagen = jugadorObservado.getSprite().getRutaImagen(estado);
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
        
}