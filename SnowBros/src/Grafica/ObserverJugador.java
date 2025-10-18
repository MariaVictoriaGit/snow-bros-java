package Grafica;

import Juego.EntidadJugador;

public class ObserverJugador extends ObserverGrafico{

    private EntidadJugador jugadorObservado;

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
                case IZQUIERDA:
                    estado=SPRITECAMINAIZQUIERDA;
                case QUIETO:
                    if (jugadorObservado.getDirAnterior() == IZQUIERDA)
                        estado=SPRITEIZQUIERDA;
                    else 
                        estado=SPRITEDERECHA;
                case DERECHA:
                    estado= SPRITECAMINADERECHA;
            }
        
		String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
        
}