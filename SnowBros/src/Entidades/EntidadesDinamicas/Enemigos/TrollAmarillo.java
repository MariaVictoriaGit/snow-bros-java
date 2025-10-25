package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class TrollAmarillo extends Enemigo{
    protected static final int puntajeCongelado = 300;
    protected static final int puntajeEmpujado = 500;

    public TrollAmarillo(int x, int y, Sprite spritesTrollAmarillo){
        super(x,y,spritesTrollAmarillo);
        salud=ConstantesDificultad.SALUD_TROLL_AMARILLO;
        velocidad=ConstantesDificultad.VELOCIDAD_TROLL_AMARILLO;
    }
    public void mover(){
        //TODO mover        
    }
    public void saltar(){
        //TODO implementar
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
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
}