package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Kamakichi extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    protected boolean activarDisparo;


    public Kamakichi(int x, int y, Sprite sprite){
        super(x, y, sprite);  
        salud=ConstantesDificultad.SALUD_KAMAKICHI;
        velocidad=ConstantesDificultad.VELOCIDAD_KAMAKICHI;
    }
    public void mover(){
        //TODO mover       
    }
    public void dispararBomba(){
        activarDisparo=true;
    }
    public void convertirEnBola(){
        congelado=true;
    }
    public void agregarPowerUp(Boolean b){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
    public String generarClaveEstado(){
        String clave="normal"; //estado por defecto
        if(congelado)
            clave="congelado";
        else
            if(activarDisparo)
                clave="disparo";
        return clave;
    }
    public void visitar(Plataforma e){
        //Kamakichi atraviesa plataformas
        throw new UnsupportedOperationException("Unimplemented method 'getPuntajeEmpujado'");
    }
    public void visitar(PlataformaMovil e){
        //Kamakichi atraviesa plataformas
        throw new UnsupportedOperationException("Unimplemented method 'getPuntajeEmpujado'");
    }
    public void visita(PlataformaQuebradiza e){
        //Kamakichi atraviesa plataformas
        throw new UnsupportedOperationException("Unimplemented method 'getPuntajeEmpujado'");
    }
}