package SnowBros.src.Juego;

import SnowBros.src.Grafica.Observer;

public abstract class EntidadDinamica extends Entidad{
    protected int direccion;
    protected Observer observer;
    protected Boolean estadoAereo;
    protected int velocidad;

    public void mover(){
        //TODO implementar
    }
    public void actualizar(int direccion, int estadoAereo){
        //TODO implementar
    }
    public void registrarObserver(Observer observer){
        //TODO implementar
    }
    public void establecerEstadoAereo(int estado){
        //TODO implementar
    }

}
