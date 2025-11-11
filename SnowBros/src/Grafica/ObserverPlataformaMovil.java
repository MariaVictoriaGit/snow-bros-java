package SnowBros.src.Grafica;

import javax.swing.ImageIcon;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaMovil;

public class ObserverPlataformaMovil extends ObserverGrafico{
    public ObserverPlataformaMovil(PlataformaMovil p){
        super(p);
        plataformaObservada=p;
        this.actualizar();
    }
    public void actualizar(){
        this.actualizarImagen(); 
        super.actualizarPosicionTamanoP();
    }
    protected void actualizarImagen() {
		String rutaImagen = plataformaObservada.getSprite().getRutaImagen("normal");
		ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
		setIcon(icono);
	}        
}