package SnowBros.src.Entidades.EntidadesEstaticas.PowerUps;
import javax.swing.JLabel;

import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesEstaticas.EntidadEstatica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public abstract class PowerUp extends EntidadEstatica {
    protected static final int puntaje = 300;
    protected boolean activo;
    protected Temporizador tiempoDeVida;
    protected Temporizador tiempoDeEfecto;
    protected JLabel label;

    public PowerUp(int x, int y, Sprite sprite){
        super(x,y,sprite);
        activo=false;
        tiempoDeVida=new Temporizador(ConstantesTiempos.TIEMPO_VIDA_POWER_UP);
        tiempoDeEfecto=new Temporizador(ConstantesTiempos.TIEMPO_EFECTO_POWER_UP);
        hitbox.actualizarHitbox(x,y+12,ancho*2,alto*2);
        tiempoDeVida.setAccionAlFinalizar(()->eliminar());
    }
    public void eliminar(){
        nivel.getJuego().eliminarPowerUp(this);
    }
    public int getPuntaje(){
        return puntaje;
    }
    public JLabel getLabel(){
        return label;
    }
    public void setLabel(JLabel l){
        label= l;
    }
    public Temporizador getTiempoDeVida(){
        return tiempoDeVida;
    }
    public Temporizador getTiempoDeEfecto(){
        return tiempoDeEfecto;
    }
}
