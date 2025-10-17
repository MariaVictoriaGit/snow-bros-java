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
        //TODO implementar
    } 
    public int actualizarPuntaje(){
        //TODO implementar
    }
    public String generarClaveEstado(){
        //TODO implementar
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
        //TODO implementar
    }
    public void efectoBotellaRoja(){
        //TODO implementar
    }
    public void efectoBotellaVerde(){
        //TODO implementar
    }





}
