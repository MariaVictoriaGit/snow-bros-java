package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEnemigos;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.*;
import SnowBros.src.Entidades.EntidadesEstaticas.Plataformas.PlataformaQuebradiza;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Kamakichi extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;

    public Kamakichi(int x, int y, Sprite sprite){
        super(x, y, sprite);  
        salud=ConstantesEnemigos.VIDA_KAMAKICHI;
    }
    public void dispararBomba(){
        //TODO implementar
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void patronMovimiento(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patronMovimiento'");
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