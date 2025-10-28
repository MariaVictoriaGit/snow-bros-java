package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;
import SnowBros.src.Visitor.Visitador;

public class DemonioRojo extends Enemigo{
    protected static final int puntajeCongelado = 150;
    protected static final int puntajeEmpujado = 300;
    protected int velocidad;
    protected int direccion;
    protected int contadorAnimacion=0;

    public DemonioRojo(int posX, int posY, Sprite miSprite){
        super(posX, posY, miSprite);
        salud=ConstantesDificultad.SALUD_DEMONIO_ROJO;
        velocidad=ConstantesDificultad.VELOCIDAD_DEMONIO_ROJO;
    }
    public void mover(){
        accionMovimientoEnemigo.iniciar();
        while(accionMovimientoEnemigo.getTiempoActual()!=0){
            Temporizador caminar= new Temporizador(100);
            if(dirAnterior==ConstantesEntidades.IZQUIERDA){
                while(caminar.getTiempoActual()!=0){
                    posX-=32;
                }
            }else if(dirAnterior==ConstantesEntidades.DERECHA){
                while(caminar.getTiempoActual()!=0){
                    posX+=32;
                }
            }  
        }
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
    public String generarClaveEstado(){
        String clave=null;
        if(contadorAnimacion==3)
            contadorAnimacion=0;
        if(direccion==ConstantesEntidades.QUIETO){
            contadorAnimacion=0;
            switch (dirAnterior) {
                case ConstantesEntidades.DERECHA:
                    clave="idleDerecha";
                    break;
            
                case ConstantesEntidades.IZQUIERDA:
                    clave="idleIzquierda";
                    break;
            }
        }else{
            contadorAnimacion++;
            switch (contadorAnimacion) {
                case 1:
                    if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda1";
                    else
                        clave="moverDerecha1";
                    break;
            
                case 2:
                    if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda2";
                    else
                        clave="moverDerecha2";
                    break;
                case 3:
                     if(direccion==ConstantesEntidades.IZQUIERDA)
                        clave="moverIzquierda3";
                    else
                        clave="moverDerecha3";
                    break;
            }
          
        }
        return clave;
    }
}