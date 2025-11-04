package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;

import javax.swing.JLabel;
import SnowBros.src.Grafica.Sprite;

public class PlataformaQuebradiza extends Plataforma {
    private static final int puntaje = 300;
    protected JLabel label;
    protected boolean horizontal;

    public PlataformaQuebradiza(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
    public void eliminar(){
        nivel.getJuego().eliminarPlataformaQuebradiza(this);
    }
    public JLabel getLabel(){
        return label;
    }
    public int getPuntaje(){
        return puntaje;
    }
}