package SnowBros.src.Juego;

public class Timer {
    private int tiempoActual;
    private int tope;
    private Thread hilo;
    private boolean enMarcha;
    
    public Timer(){
        tope=Integer.MAX_VALUE;
        tiempoActual=0;
        enMarcha=false;
    }
    public Timer(int tMaximo){
        tope=tMaximo;
        tiempoActual=0;
        enMarcha=false;
    }
    public void iniciar(){
        if(enMarcha) return;
        enMarcha=true;
        hilo=new Thread(() ->{
            while (enMarcha && tiempoActual<tope){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                incrementar();
            }
            enMarcha=false;
        });
        hilo.start();
    }
    public void detener(){
        enMarcha=false;
        if(hilo!=null){
            hilo.interrupt();
            hilo=null;
        }
    }
    public void reiniciar(){
        detener();
        tiempoActual=0;
        iniciar();
    }
    public void incrementar(){
        if(tiempoActual<tope){
            tiempoActual++;
        }
    }
    public boolean llegoAlTope(){
        return tiempoActual>=tope;
    }
    public boolean estaCorriendo(){
        return enMarcha;
    }
    public int getTope(){
        return tope;
    }
    public int getTiempoActual(){
        return tiempoActual;
    }
    public void setTiempoActual(int tiempo){
        tiempoActual=tiempo;
    }
}
