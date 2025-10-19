package SnowBros.src.Juego;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Observer;

public abstract class EntidadDinamica extends Entidad{
    protected int direccion;
    protected Observer observer;
    protected boolean estadoAereo;
    protected int velocidad;

    public void mover(){
        //TODO implementar o dejar abstract¿
    }
    public void actualizar(int direccion, int estadoAereo){
        this.establecerEstadoAereo(estadoAereo);
        this.direccion=direccion;
    }
    public void registrarObserver(Observer observer){
        this.observer= observer;
    }
    public void establecerEstadoAereo(int estado){
        boolean estadoNuevo= (estado==ConstantesEntidades.AIRE);

        if (this.estadoAereo !=estadoNuevo) {
            estadoAereo= estadoNuevo;
            if (observer != null) {
                observer.actualizar(direccion,estado);
            }
        }
    }
    public int getEstadoAereo(){
        int state;

        if(estadoAereo==true){
            state=ConstantesEntidades.AIRE;
        }
        else{
            state=ConstantesEntidades.SUELO;
        }
        return state;
    }

}
