package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Moghera extends Enemigo{
    protected static final int puntajeCongelado = 1000;
    protected static final int puntajeEmpujado = 5000;
    protected boolean congelado;

    public Moghera(int x, int y, Sprite sprite){
        super(x, y, sprite);       
        salud=ConstantesDificultad.SALUD_MOGHERA;
        velocidad=ConstantesDificultad.VELOCIDAD_MOGHERA;
    }
    public void mover(){
        //TODO implementar mover
    }
     public void patronDeMovimiento(){
        
    }
    public void lanzarFuego(){
        //TODO implementar
    }
    public void esDerrotado(){
        //TODO implementar
    }
    public void convertirEnBola(){
       congelado=true;
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
    public String generarClaveEstado(){
        String clave=null;
        switch (direccion) {
            case ConstantesEntidades.IZQUIERDA:
                if(congelado)
                    clave="congeladoIzquierda";
                else
                    clave="idleIzquierda";
                break;
        
            case ConstantesEntidades.DERECHA:
                if(congelado)
                    clave="congeladoDerecha";
                else
                    clave="idleDerecha";
                break;
        }
        return clave;
    }
    public int getPuntajeCongelado() {
        return puntajeCongelado;
    }
    public int getPuntajeEmpujado() {
        return puntajeEmpujado;
    }
}