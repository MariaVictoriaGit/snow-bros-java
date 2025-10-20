package SnowBros.src.Grafica;

import javax.swing.ImageIcon;

//import Juego.EntidadJugador;
import SnowBros.src.Datos.ConstantesEntidades;
//TODO crear entidad jugador 
import SnowBros.src.Jugador.Jugador;

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
    public void actualizar(int direccion, boolean estadoAereo){
        actualizarImagen(jugadorObservado.getDireccion(), jugadorObservado.tocoAire());
        super.actualizarPosicionTamano();

    }
    protected void actualizarImagen(int direccion, boolean estadoAereo) {
        String estado= null;
        if (!estadoAereo)
            switch(direccion){
                case ConstantesEntidades.IZQUIERDA:
                    estado="SPRITECAMINAIZQUIERDA";
                case ConstantesEntidades.QUIETO:
                    if (jugadorObservado.getDirAnterior() == ConstantesEntidades.IZQUIERDA)
                        estado="SPRITEIZQUIERDA";
                    else 
                        estado="SPRITEDERECHA";
                case ConstantesEntidades.DERECHA:
                    estado= "SPRITECAMINADERECHA";
            }
        
		String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
        
}