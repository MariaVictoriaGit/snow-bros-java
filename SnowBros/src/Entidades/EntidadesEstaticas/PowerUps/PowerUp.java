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
    public PowerUp(int x, int y, Sprite sprite){
        super(x,y-6,sprite); // 4 es margen visual
        activo=false;
        tiempoDeVida=new Temporizador(ConstantesTiempos.TIEMPO_VIDA_POWER_UP);
        tiempoDeEfecto=new Temporizador(ConstantesTiempos.TIEMPO_EFECTO_POWER_UP);
        hitbox.actualizarHitbox(x,y,ancho*2,alto*2); // quite un ajuste +12 en y, testear que ande bien
        tiempoDeVida.setAccionAlFinalizar(()->eliminar());
    }
    public void eliminar(){
        nivel.getJuego().eliminarPowerUp(this);
    }
    public int getPuntaje(){
        return puntaje;
    }
    public JLabel getLabel(){
        return miLabel;
    }
    public void setLabel(JLabel l){
        miLabel= l;
    }
    public Temporizador getTiempoDeVida(){
        return tiempoDeVida;
    }
    public Temporizador getTiempoDeEfecto(){
        return tiempoDeEfecto;
    }
}
