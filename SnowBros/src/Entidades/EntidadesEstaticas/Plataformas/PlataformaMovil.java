package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.EstadosDePatron;
import SnowBros.src.Grafica.ObserverPlataformaMovil;
import SnowBros.src.Grafica.Sprite;

public class PlataformaMovil extends Plataforma{
    private static final int puntaje=200;
    protected boolean horizontal;
    protected int velocidad;
    protected ObserverPlataformaMovil observer;
    protected int distanciaRecorrida;
    private int estado=EstadosDePatron.MOVERDERECHA;
    private int distaciaMaxRecorrida;

    public PlataformaMovil(int x, int y, Sprite sprite){
        super(x,y,sprite);
        observer= new ObserverPlataformaMovil(this);
        velocidad=ConstantesDificultad.VELOCIDAD_PLATAFORMA_MOVIL;
        distaciaMaxRecorrida=40;
        distanciaRecorrida=0;
    }
    public void mover(){
        switch (estado) {
            case EstadosDePatron.MOVERDERECHA:
                    if(distaciaMaxRecorrida>distanciaRecorrida){
                        distanciaRecorrida=velocidad+distanciaRecorrida;
                        posX=posX+velocidad;
                        this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                    }
                    else{
                        distanciaRecorrida=0;
                        estado=EstadosDePatron.MOVERIZQUIERDA;
                    }
                    break;
            case EstadosDePatron.MOVERIZQUIERDA:
                if(distaciaMaxRecorrida>distanciaRecorrida){
                    distanciaRecorrida=velocidad+distanciaRecorrida;
                    posX=posX-velocidad;
                    this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                }
                else{
                    distanciaRecorrida=0;
                    estado=EstadosDePatron.MOVERDERECHA;
                }
                break;
        }
    }
    public int getPuntaje(){
        return puntaje;
    }
    public ObserverPlataformaMovil getObserver() {
        return observer;
    }
    public void registrarObserver(ObserverPlataformaMovil obs){
        observer=obs;
    }
}
