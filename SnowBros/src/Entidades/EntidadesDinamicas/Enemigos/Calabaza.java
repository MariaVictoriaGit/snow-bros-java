package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesDificultad;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Visitor.Visitador;

public class Calabaza extends Enemigo{
    public Calabaza(int x, int y, Sprite sprite){
        super(x, y, sprite);
        velocidad=ConstantesDificultad.VELOCIDAD_CALABAZA;
    }
    public void mover(){
        //TODO mover
    }
    public void invocarFantasma(){
        //TODO implementar
    }
    public void convertirEnBola(){
        throw new UnsupportedOperationException("Unimplemented method 'convertirEnBola'");
    }
    public void agregarPowerUp(Boolean agregar){
        throw new UnsupportedOperationException("Unimplemented method 'agregarPowerUp'");
    }
    public int getPuntajeCongelado() {//redefinirlo pq son indestructibles
        return 0;
    }
    public int getPuntajeEmpujado() {//redefinirlo pq son indestructibles
        return 0;
    }
    public void recibirDaño(){
        //es indestructible, no tiene salud
        throw new UnsupportedOperationException("Es indestructible");
    }
    public void aceptar(Visitador v){
        v.visitar(this);
    }
}