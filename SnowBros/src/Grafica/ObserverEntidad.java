package SnowBros.src.Grafica;

//import javax.swing.ImageIcon;
import SnowBros.src.Entidades.Entidad;
//import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;

public class ObserverEntidad extends ObserverGrafico{
    public ObserverEntidad(Entidad entidad) {
        super(entidad);
        //TODO Auto-generated constructor stub
    }
    public void actualizar(int direccion, int estadoAereo) {
        //TODO implementar
    }
}    
    /*protected EntidadDinamica entidadObservada;

    public ObserverEntidad(EntidadDinamica entidad){
        super(entidad);
        entidadObservada=entidad;
        actualizar();
    }
    public void actualizar(int direccion, int estadoAereo) {
        actualizarImagen(entidadObservada.getDireccion(),entidadObservada.getEstadoAereo()); 
        super.actualizarPosicionTamano();
    }
    protected void actualizarImagen(int direccion, int estadoAereo) {
        entidadObservada.setDireccion(direccion);
        String estado= entidadObservada.generarClaveEstado(entidadObservada.getDirAnterior());
		String rutaImagen = entidadObservada.getSprite().getRutaImagen(estado);
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
}
/*TODO pq un observer especifico para jugador cuando TODAS las entidades dinamicas 
van a moverse y cambiar de direccion(cambiar de sprite)
En si todo lo que necesita un observer son las dinamicas y plataforma movil
*/