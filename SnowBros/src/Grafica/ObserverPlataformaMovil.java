/*package SnowBros.src.Grafica;

import javax.swing.ImageIcon;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

public class ObserverPlataformaMovil extends ObserverGrafico{
    //unica entidad estatica que cambiara de estado...y no es una entidad estatica, ya que 
    //el observen necesitaria 
    protected PlataformaMovil plataformaObservada;

    public ObserverPlataformaMovil(PlataformaMovil p){
        super(p);
        plataformaObservada=p;
        super.actualizar();
    }
    public void actualizar(int direccion, int tocandoSuelo){
        actualizarImagen(plataformaObservada.getDireccion(),ConstantesEntidades.AIRE); 
        super.actualizarPosicionTamano();
    }
    protected void actualizarImagen(int direccion, int tocandoSuelo) {
        plataformaObservada.setDireccion(direccion);
		String rutaImagen = plataformaObservada.getSprite().getRutaImagen("normal");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}
        
}*/