package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEnemigos;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;

    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
        salud=ConstantesEnemigos.VIDA_DEMONIO_ROJO;
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
    public String generarClaveEstado(){
        String clave=null;
        if(direccion==ConstantesEntidades.QUIETO){
            switch (dirAnterior) {
                case ConstantesEntidades.DERECHA:
                    clave="idleDerecha";
                    break;
            
                case ConstantesEntidades.IZQUIERDA:
                    clave="idleIzquierda";
                    break;
            }
        }else{
            if(direccion==ConstantesEntidades.IZQUIERDA){
                clave="moverIzquierda1";
            }else
                clave="moverDerecha1";
        }
        return clave;

    }
}
