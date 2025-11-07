package SnowBros.src.Juego;

public class Temporizador {
    private int tiempoActual;
    private int tope;
    private Thread hilo;
    private boolean enMarcha;
    private Runnable accionAlFinalizar;
    
    public Temporizador(){
        tope=Integer.MAX_VALUE;
        tiempoActual=0;
        enMarcha=false;
    }
    public Temporizador(int tMaximo){
        tope=tMaximo;
        tiempoActual=0;
        enMarcha=false;
    }
    public void iniciar(){
        if(enMarcha) return;
        
        hilo=new Thread(()->{//implementa Runnable
            enMarcha=true;
            while (enMarcha && !llegoAlTope()){
                try{
                    Thread.sleep(1000);//1s
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
                incrementar();
            }
            enMarcha=false;
            if(accionAlFinalizar != null){
                accionAlFinalizar.run();
            }
        });
        hilo.start();
    }
    public void setAccionAlFinalizar(Runnable accion){
        accionAlFinalizar = accion;
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
    public void resetear(){
        tiempoActual=0;
    }
    public void incrementar(){
        if(tiempoActual<tope){
            tiempoActual++;
        }

    }
    public boolean llegoAlTope(){
        return tiempoActual==tope;
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
    /*
    public void setTiempoActual(int tiempo){
        tiempoActual=tiempo;
    }*/
    public String getStringDeTiempo(){
        int minutos=tiempoActual/60;
        int segundos=tiempoActual%60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
