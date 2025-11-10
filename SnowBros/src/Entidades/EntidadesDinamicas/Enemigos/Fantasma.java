package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Fantasma extends Enemigo{

    private int xJugador;
    private int yJugador;
    
    public Fantasma(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_FANTASMA;
        direccion=0;
    }
    public void mover(){
        if(xJugador> posX)
            posX+= velocidad;
            else posX-=velocidad;
        if(yJugador > posY)
            posY+= velocidad;
            else posY-=velocidad;
        // Aquí podrías actualizar la hitbox o hacer otros cálculos si es necesario
        this.getHitbox().actualizarHitbox(posX, posY, ancho, alto);
    }

    public void setPosicionJugador(int x, int y){
        xJugador=x;
        yJugador=y;

    }
    public void patronDeMovimiento(){
        setPosicionJugador(nivel.getJugador().getPosX(), nivel.getJugador().getPosY());
        mover();
    }
    public void convertirEnBola(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void recibirDaño(){
        //es indestructible
        throw new UnsupportedOperationException("Es indestructible");
    }
    public int getPuntajeCongelado() {
        return 0;//indestructible
    }
    public int getPuntajeEmpujado() {
        return 0;//indestructible
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}