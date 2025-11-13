package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;
import javax.swing.JLabel;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitador;

public class PlataformaQuebradiza extends Plataforma {
    private static final int puntaje = 300;
    protected JLabel label;
    protected boolean horizontal;
    protected Temporizador tiempoAntesDeRomperse;
    protected boolean eliminado;
    protected boolean usada;

    public PlataformaQuebradiza(int x, int y, Sprite sprite){
        super(x,y,sprite);
        eliminado=false;
        tiempoAntesDeRomperse= new Temporizador(1);
        tiempoAntesDeRomperse.setAccionAlFinalizar(()->eliminar());
        usada=false;
    }
    public void eliminar(){
        if(!eliminado){
            nivel.eliminarPlataforma(this);
            borrarLabel();
           // nivel.getJuego().eliminarPlataformaQuebradiza(this);
            eliminado=true;
        }
    }
    public JLabel getLabel(){
        return label;
    }
    public int getPuntaje(){
        return puntaje;
    }
    public Temporizador getTiempoAntesDeRomperse(){
        return tiempoAntesDeRomperse;
    }
    public void setUsada(){
        usada=true;
    }
    public boolean getUsada(){
        return usada;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}