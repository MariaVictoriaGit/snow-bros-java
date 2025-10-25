package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected int velocidad;
    protected int direccion;

    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
        salud=ConstantesDificultad.SALUD_DEMONIO_ROJO;
        velocidad=ConstantesDificultad.VELOCIDAD_DEMONIO_ROJO;
    }
    public void mover(){
        //TODO mover
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
