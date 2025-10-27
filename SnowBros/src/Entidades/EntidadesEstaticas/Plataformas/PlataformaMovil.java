package SnowBros.src.Entidades.EntidadesEstaticas.Plataformas;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;

public class PlataformaMovil extends Plataforma{
    private static final int puntaje=200;
    protected boolean horizontal;
    protected int direccion;
    protected int dirAnterior;
    protected int velocidad;

    public PlataformaMovil(int x, int y, Sprite sprite){
        super(x,y,sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_PLATAFORMA_MOVIL;
    }
    public void mover(){
        //TODO implementar
    }
    public int getPuntaje(){
        return puntaje;
    }
    public void setDireccion(int d){
        direccion=d;
    }
    public int getDireccion(){
        return direccion;
    }
    public int getDirAnterior(){
        return dirAnterior;
    }
}
