package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Temporizador tiempo;
    private int velocidad=6;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_NIEVE);
        estaActiva=true;
    }
    public boolean getEstado(){
        return estaActiva;
    }
    public Temporizador getTimer(){
        return tiempo;
    }
    public void mover(){
        switch(direccion){
            case ConstantesEntidades.DERECHA:
               posX=posX+5;
               this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
            break;
            case ConstantesEntidades.IZQUIERDA:
                posX=posX-5;
                this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
            break;
        }
    }
    private void rebotar(){
        this.eliminar(); 
    }
    public void visitar(Enemigo e){
        e.eliminar();
        //TDOO a los indestructibles se les aturde no elimina(calabaza), al fantasma ni lo toca
        rebotar();
    }
    public void visitar(ParedDestructible p){
        //TODO creo q las paredes se destruian con bolas de nieve
        p.destruirse();
    }
    public void eliminar(){
        nivel.eliminarBolaDeNieve(this);
    }
}
