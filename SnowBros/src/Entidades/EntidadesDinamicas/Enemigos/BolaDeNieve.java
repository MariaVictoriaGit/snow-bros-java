package SnowBros.src.Entidades.EntidadesDinamicas.Enemigos;

import SnowBros.src.Datos.ConstantesEntidades;
import SnowBros.src.Datos.ConstantesTiempos;
import SnowBros.src.Datos.ConstantesVistas;
import SnowBros.src.Entidades.EntidadesDinamicas.EntidadDinamica;
import SnowBros.src.Entidades.EntidadesDinamicas.Jugador.Jugador;
import SnowBros.src.Entidades.EntidadesEstaticas.Obstaculos.ParedDestructible;
import SnowBros.src.Grafica.Sprite;
import SnowBros.src.Juego.Temporizador;

public class BolaDeNieve extends EntidadDinamica{
    private boolean estaActiva;
    private Temporizador tiempo;
    private int velocidad=4;
    private boolean reboto;

    public BolaDeNieve(int x, int y, Sprite sprite){
        super(x,y,sprite);
        tiempo=new Temporizador(ConstantesTiempos.TIEMPO_BOLA_DE_NIEVE);
        estaActiva=true;
        reboto=false;
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
               posX=posX+velocidad;
               this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
               observer.actualizar();
               if(!reboto && posX>ConstantesVistas.PANEL_ANCHO-20){
                    direccion=ConstantesEntidades.IZQUIERDA;
                    reboto=true;
               }

            break;
            case ConstantesEntidades.IZQUIERDA:
                posX=posX-velocidad;
                this.getHitbox().actualizarHitbox(posX,posY,ancho,alto);
                observer.actualizar();
                if(!reboto && posX<20){
                   direccion=ConstantesEntidades.DERECHA;
                   reboto=true;
                }
            break;
        }
        terminaRebotar();
    }
    private void terminaRebotar(){
        if(reboto && posX==0 || posX==ConstantesVistas.PANEL_ANCHO)
            this.eliminar(); 
    }
    public void visitar(Enemigo e){
        e.eliminar();
        //TDOO a los indestructibles se les aturde no elimina(calabaza), al fantasma ni lo toca
    }
    public void visitar(ParedDestructible p){
        //TODO creo q las paredes se destruian con bolas de nieve
        p.destruirse();
    }
    public void eliminar(){
        nivel.eliminarBolaDeNieve(this);
    }
    public void visitar(Jugador j){
        direccion=j.getDireccion();
        mover();
    }
}
