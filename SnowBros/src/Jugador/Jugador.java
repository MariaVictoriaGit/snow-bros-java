package SnowBros.src.Jugador;
import SnowBros.src.Juego.EntidadDinamica;
import SnowBros.src.Juego.Juego;

public class Jugador extends EntidadDinamica {
    private static final int vidaDeInicio = 3;
    protected Juego miJuego;
    protected int vidas;
    protected int tiempo;
    protected int puntaje;
    protected boolean tocandoSuelo;
    protected String nombre;
    protected int dirAnterior;
    protected boolean disparoAumentado;

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
        return vidas;
    }
    public int getTiempo(){
        return tiempo;
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
        return puntaje;
    }
    public void setEnElAire(Boolean b){
        // TODO preguntar borrar
    }
    public void setTocandoSuelo(Boolean b){
        tocandoSuelo=b;
    }
    public void setTiempo(int t){
        if(t>=0){
            tiempo=t;
        }
    }
    public void perderVida(){
        vidas--;
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
