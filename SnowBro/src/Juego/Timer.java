package SnowBro.src.Juego;

public class Timer {
    private int tiempoActual;
    private int tiempoMaximo;

    public void incrementar(){
        tiempoActual++;
    }
    public void decrementar(){
        tiempoActual--;
    }
    public boolean llegoAlTope(){
        return tiempoActual==tiempoMaximo;
    }
    public int getTiempoActual(){
        return tiempoActual;
    }
    public void setTiempoActual(int tiempo){
        tiempoActual = tiempo;
    }
}
