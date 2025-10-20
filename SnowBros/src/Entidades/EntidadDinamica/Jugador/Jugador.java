package SnowBros.src.Entidades.EntidadDinamica.Jugador;
import SnowBros.src.Entidades.EntidadDinamica.EntidadDinamica;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Juego;

public class Jugador extends EntidadDinamica {
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected boolean tocandoSuelo;
    protected String nombre;
    protected int dirAnterior;
    protected boolean disparoAumentado;

    public Jugador(int x,int y, Sprite mISprite){
        super(x,y,mISprite);
        tocandoSuelo=true;
    }

    public void mover(){
        //TODO implementar
    }
    public void actualizarSalto(){
        //TODO implementar
    }
    public boolean tocoAire(){
        return !tocandoSuelo;
    }
    public void disparar(){
        DisparoJugador disparo=new DisparoJugador(this,disparoAumentado);
        miJuego.getNivelActual().agregarBolaDeNieve(disparo);

    } 
    public int actualizarPuntaje(){
        return dirAnterior;
        //TODO implementar      es return definido por el compilador
    }
    public String generarClaveEstado(){
        return nombre;
        //TODO implementar      es return definido por el compilador
    }
    public int getVidas(){
        return miJuego.getVidasJugador();
    }
    public int getTiempo(){
        return miJuego.getTiempoActual();
    }
    public int getDireccion(){
        return direccion;
    }
    public String getNombre(){
        return nombre;
    }
    public int getDirAnterior(){
        return dirAnterior;
    }
    public int getPuntaje(){
        return miJuego.getPuntaje();
    }
    public void setEnElAire(Boolean b){
        // TODO preguntar borrar
    }
    public void setTocandoSuelo(Boolean b){
        tocandoSuelo=b;
    }
   /* public void setTiempo(int t){
        int tiempo;
        if(t>=0){
            tiempo=t;
        }
    }*/ //ver si es necesario el set aca y no en juego
    public void perderVida(){
    }
    public void setVidas(){
        //TODO implementar
    }
    public void efectoBotellaAzul(){
        this.velocidad+=5; // suponiendo que ese es el incremento final, cambiar dsp
    }
    public void efectoBotellaRoja(){
        this.disparoAumentado=true;
        
    }
    public void efectoBotellaVerde(){
        //TODO implementar
    }
}
